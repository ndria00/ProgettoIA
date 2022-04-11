package application.asp;

import java.util.ArrayList;
import java.util.List;

import application.model.Card;
import application.model.Game;
import application.model.HandOfCards;
import application.model.Play;
import application.model.Player;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
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

	public boolean canOpen(HandOfCards handOfCards, Player player){
		//ArrayList<Play> plays = new ArrayList<Play>();
		
		addPlayerCardsAsFacts(handOfCards);
		encoding.addFilesPath("encodings/botCanOpenEncoding");
		
		handler.addProgram(facts);
		System.out.println("CARDS: " + facts.getPrograms());
		handler.addProgram(encoding);
		//System.out.println("ENCODING: " + encoding.getPrograms());
		Output o = handler.startSync();
		//AnswerSets answerSets = (AnswerSets) o;
		System.out.println(o.getOutput());
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
		return false;
	}
	
	public boolean canPlay(HandOfCards cards, Player player){
		//ArrayList<Play> plays = new ArrayList<Play>();
		
		addPlayerCardsAsFacts(cards);
		addGamePlaysAsFacts();
		encoding.addFilesPath("encodings/botCanOpenEncoding");
		
		handler.addProgram(facts);
		System.out.println("CARDS: " + facts.getPrograms());
		handler.addProgram(encoding);
		//System.out.println("ENCODING: " + encoding.getPrograms());
		Output o = handler.startSync();
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
		return false;
	}
	
	private void addPlayerCardsAsFacts(HandOfCards cards) {
		for(Card c: cards) {
			try {
				facts.addObjectInput(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void addGamePlaysAsFacts() {
		List<Play> plays = Game.getInstance().getPlays();
		
		for (Play p: plays){
			try {
				facts.addObjectInput(p);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}