package application.asp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.model.BotPlayer;
import application.model.Card;
import application.model.Combination;
import application.model.Game;
import application.model.HandOfCards;
import application.model.Ladder;
import application.model.Play;
import application.model.Player;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.OptionDescriptor;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

public class ASPManager {
	private Handler handler;
	private InputProgram facts;
	private InputProgram encoding;
	private static ASPManager instance = null;
	
	private ASPManager() {
		try {
			handler =  new DesktopHandler(new DLV2DesktopService("lib/dlv2-linux"));
			//handler =  new DesktopHandler(new DLV2DesktopService("lib/dlv2-macos"));
			ASPMapper.getInstance().registerClass(PlayableCombination.class);
			ASPMapper.getInstance().registerClass(Card.class);
			facts = new ASPInputProgram();
			encoding = new ASPInputProgram();
		} catch (ObjectNotValidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAnnotationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ASPManager getInstance() {
		if(instance == null)
			instance = new ASPManager();
		return instance;
	}
	//called by real player and bot passing all the cards or only the selected
	//ones in the case of the real player
	public boolean canOpen(HandOfCards handOfCards, Player player){
		resetHandler();
		addPlayerCardsAsFacts(handOfCards);
		encoding.addFilesPath("encodings/botCanOpenEncoding");
		handler.addProgram(facts);
		System.out.println("CARDS: " + facts.getPrograms());
		handler.addProgram(encoding);
		//System.out.println("ENCODING: " + encoding.getPrograms());
		Output o = handler.startSync();
		System.out.println("ENDED ASP COMPUTATION");

		//input to a second asp program that will guess and play		
		//check if these two methods effectively clear the handler and the input programs
		List<Play> plays = getPlaysFromASPOpen(o.getOutput());
		StringBuilder possiblePlays = new StringBuilder("");
		for(Play p: plays) {
			String s = p.getListAndValue(p.computeTotalPoints());
			possiblePlays.append(p.getListAndValue(p.computeTotalPoints()));
			System.out.println("NEW FACT: " + s);
		}
		
		resetHandler();
		
		addPlayerCardsAsFacts(handOfCards);
		facts.addProgram(possiblePlays.toString());
		encoding.addFilesPath("encodings/selectPlaysOpenRound");
		handler.addProgram(facts);
		handler.addProgram(encoding);
		//System.out.println("STARTED");
		//System.out.print("FACTS: " + facts.getPrograms() + "\n\nENCODING: " + encoding.getPrograms());
		Output o1 = handler.startSync();
		System.out.println(o1.getOutput() + "END OF ANSWER SETS");
		boolean opened = addPlaysAndRemoveCards(o1.getOutput(), player);
		//System.out.println("ENDED ASP COMPUTATION");
		return opened;
	}
	
	//called by real player or bot after they have already opened
	public boolean canPlay(HandOfCards cards, Player player){
		//ArrayList<Play> plays = new ArrayList<Play>();
		resetHandler();
		//if (player.getClass() == BotPlayer.class) {
		//	handleBotPick(player);
		//	resetHandler();
		//}

		//addPlayerCardsAsFacts(cards);
		//encoding.addFilesPath("encodings/botPlayBeginner");
		//addExistingGamePlaysAsFacts();
		//handler.addProgram(facts);
		//System.out.println("CARDS: " + facts.getPrograms());
		//handler.addProgram(encoding);
		//System.out.println("ENCODING: " + encoding.getPrograms());
		//Output o = handler.startSync();
		//AnswerSets answerSets = (AnswerSets) o;
		//answerSets.getAnswersets();
		//AnswerSet as = answerSets.getAnswersets().get(0);
		/*
		try {
			for(Object obj : as.getAtoms()) {
				if(obj instanceof PlayableCombination) {
					PlayableCombination play = (PlayableCombination) obj;
					System.out.println(play);
					//Play p= new Combination(play.getCards());
					//plays.add(p);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//if (player.getClass() == BotPlayer.class) {
		//	handleBotDiscard(player);
		//	resetHandler();
		//}
		return false;
	}
	
	private void addPlayerCardsAsFacts(HandOfCards cards) {
		facts.clearAll();
		for(Card c: cards) {
			try {
				facts.addObjectInput(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void addExistingGamePlaysAsFacts() {
		List<Play> plays = Game.getInstance().getPlays();
		
		for (Play p: plays){
			try {
				facts.addProgram(p.getList(true));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private List<Play> getPlaysFromASPOpen(String output) {
		Pattern p = Pattern.compile("ladder\\(.*?\\), ");
		Matcher m = p.matcher(output);
		List<Play> plays = new ArrayList<Play>();
		Play play;
		while(m.find()) {	
			String currentLadder = m.group();
			String cardsId = currentLadder.replaceAll("ladder\\(\\[|\\]\\), ", "");
			System.out.println(currentLadder + "--> " + cardsId);
			play = new Ladder();
			for(String t : cardsId.split(",")){	
				play.add(Game.getInstance().getCardById(Integer.parseInt(t)));
			}
			plays.add(play);
		}
		
		Pattern p1 = Pattern.compile("combination\\(.*?\\), ");
		Matcher m1 = p1.matcher(output);
		while(m1.find()) {
			String currentCombination = m1.group();
			String cardsId = currentCombination.replaceAll("combination\\(\\[|\\]\\), ", "");
			System.out.println(currentCombination + "--> " + cardsId);
			play= new Combination();
			for(String t : cardsId.split(",")) {
				play.add(Game.getInstance().getCardById(Integer.parseInt(t)));
			}
			plays.add(play);
		}
		
		//remove all partial ladders and combinations that were constructed during the ASP computation
		return plays;
	}
	
	private boolean addPlaysAndRemoveCards(String output, Player player){
		boolean opened = false;
		System.out.println("Adding plays and removing cards");
		Pattern p = Pattern.compile("playedLadder[(].*?[)]");
		Matcher m = p.matcher(output);
		Play play;
		while(m.find()) {
			System.out.println("MATCHED LADDER");
			String currentLadder = m.group();
			String cardsId = currentLadder.replaceAll("playedLadder\\(\\[|\\]\\)", "");
			System.out.println(currentLadder + "--> " + cardsId);
			play = new Ladder();
			for(String t : cardsId.split(",")){	
				play.add(Game.getInstance().getCardById(Integer.parseInt(t)));
				player.getCards().removeCard(Game.getInstance().getCardById(Integer.parseInt(t)));
				
			}
			Game.getInstance().getPlays().add(play);
			opened = true;
			System.out.println("Added ladder and removed cards");
		}
		
		Pattern p1 = Pattern.compile("playedCombination[(].*?[)]");
		Matcher m1 = p1.matcher(output);
		while(m1.find()) {
			//System.out.println("MATCHED COMBINATION");
			String currentCombination = m1.group();
			String cardsId = currentCombination.replaceAll("playedCombination\\(\\[|\\]\\)", "");
			//System.out.println(currentCombination + "--> " + cardsId);
			play= new Combination();
			for(String t : cardsId.split(",")) {
				play.add(Game.getInstance().getCardById(Integer.parseInt(t)));
				player.getCards().removeCard(Game.getInstance().getCardById(Integer.parseInt(t)));
			}
			Game.getInstance().getPlays().add(play);
			opened = true;
			//System.out.println("Added combination and remove cards");
		}
		//System.out.println("Ended Adding");
		return opened;
	}
	
	public boolean handlePick(Player player){
		boolean pickFromDeck = true;
		resetHandler();
		addPlayerCardsAsFacts(player.getCards());
		handler.addProgram(facts);
		encoding.addFilesPath("encodings/botPick");
		handler.addProgram(encoding);
		Output o = handler.startSync();
		
		if(o.getOutput().contains("pickFromWell")) {
			pickFromDeck = false;
			System.out.println("Picked from well");
		}
		else {
			System.out.println("Picked from deck");
		}
		
		return pickFromDeck;
	}
	
	public Card handleBotDiscard(Player player) {
		Card c = null;
		resetHandler();
		addPlayerCardsAsFacts(player.getCards());
		handler.addProgram(facts);
		encoding.addFilesPath("encodings/botDiscard");
		handler.addProgram(encoding);
		Output o = handler.startSync();
		Pattern p = Pattern.compile("discard[(]\\d+[)]");
		Matcher m = p.matcher(o.getOutput());
		System.out.println("Bot discarding");
		System.out.println(o.getOutput());
		if(m.find()) {
			int id = Integer.parseInt(m.group().replaceAll("\\D", ""));
			System.out.println("Bot discarded " + id);
			c = Game.getInstance().getCardById(id);

		}
		return c;
	}
	
	private void resetHandler() {
		handler.removeAll();
		handler.addOption(new OptionDescriptor("-n 1"));
		encoding.clearAll();
		facts.clearAll();
	}
}