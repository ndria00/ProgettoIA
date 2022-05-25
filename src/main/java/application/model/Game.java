package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.Settings;
import application.view.PlayView;

public class Game {
	private List<Player> players;
	private Deck deck;
	private Well well;
	private List<Play> plays;
	private Map<Integer, Card> allCards;
	private int gameRoundNumber;
	
	private int playerPlaying = Settings.PLAYER_NULL;
	private int difficulty = Settings.DIFFICULTY_BEGINNER;
	private int gameState = Settings.GAME_FINISHED_STATE;
	
	private static Game instance;
	
	public static Game getInstance() {
		if(instance == null)
			instance = new Game();
		return instance;
	}
	
	private Game() {
		players= new ArrayList<Player>();
		deck = new Deck();
		well = new Well();
		plays = new ArrayList<Play>();
		allCards = new HashMap<Integer, Card>();
		File f;
		try {
			f = new File(getClass().getResource("/application/resources/cards.txt").toURI());
			BufferedReader reader = new BufferedReader(new FileReader(f));
			while(reader.ready()) {
				String line = reader.readLine();
				String [] v = line.split("\t");
				Card c = new Card();
				c.setId(Integer.parseInt(v[0]));
				c.setNumber(Integer.parseInt(v[2]));
				c.setSuite(Integer.parseInt(v[1]));
				c.setValue(Integer.parseInt(v[3]));
				//System.out.println(c.toString());
				allCards.put(c.getId(), c);
				//deck.insert(c);
			}
			reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startNewGame(int numberOfPlayers, int difficulty) {
		this.setDifficulty(difficulty);
		playerPlaying = 0;
		Player p;
		p = new RealPlayer();
		p.setPlayingRound(true);
		players.clear();
		players.add(p);
		
		for(int i = 1; i < numberOfPlayers; ++i) {
			p = new BotPlayer();
			players.add(p);
		}
		deck.addAll(allCards.values());
		
		//for(Card c3: deck) {
		//	System.out.println(c3);
		//}
		
		distributeCards();
		well.clear();
		plays.clear();
		Card c = deck.pick();
		while(c.getNumber() == Settings.JOKER_NUMBER) {
			deck.add(c);
			c = deck.pick();
		}
		well.push(c);
		gameState = Settings.GAME_PLAYING_STATE;
		gameRoundNumber = 0;
		//for(Card c1: deck) {
		//	System.out.println(c1);
		//}
	}

	public void nextMatch(Player winnerPlayer) {
		gameRoundNumber++;
		//remove all the players that have lost the match
		for(int i = 0; i < players.size(); ++i) {
			if( players.get(i).getPoints() > Settings.MAX_POINTS) {
				System.out.println(players.get(i) + "LOST THIS MATCH");
				players.remove(i);
			}
			
			//Player must be in no-opened state for the next match
			PlayerState notOpened= new PlayerNotOpenedState();
			notOpened.setPlayer(players.get(i));
			players.get(i).setState(notOpened);
			players.get(i).getCards().clear();
			players.get(i).deselectAllCards();
		}
		//there is only one player left and he is the winner
		if(players.size() == 1) {
			System.out.println("GAME HAS JUST BEEN FINISHED... WE HAVE A WINNER!");
			gameState = Settings.GAME_FINISHED_STATE;
			//show winner modal and let him choose what to do
			//AS SOON AS IT READY
		}
		else {
			deck.clear();
			deck.setCards(allCards.values());
			well.clear();
			Card c = deck.pick();
			while(c.getNumber() == Settings.JOKER_NUMBER) {
				deck.add(c);
				c = deck.pick();
			}
			well.push(c);
			distributeCards();
			plays.clear();
			PlayView.getInstance().updateView();
			playerPlaying = players.indexOf(winnerPlayer);
			if(winnerPlayer.getClass() == BotPlayer.class) {
				System.out.println("SEEMS THAT BOT WON");
				winnerPlayer.play(Game.getInstance().getPlays());
			}

		}
	}
	
	public void distributeCards() {
		Card c;
		for(Player p: players) {
			for(int i = 0 ; i < 13; ++i) {
				c = deck.pick();
				p.getCards().addCard(c);
			}
		}
		
	}
	
	public void roundFinished(Player player) {
		//the player has won the game
		if(player.getCards().isEmpty()) {
			for(Player p : players) {
				p.setPoints(p.getPoints() + p.getCards().computeTotalPoints());
			}
			//set the game in finished state
			if(player != getRealPlayer()) {
				System.out.println("BOT WON THE GAME");	
			}
			else {
				System.out.println("YOU WON THE GAME!");	
			}
			System.out.println("GOING TO NEXT MATCH");
			nextMatch(player);
			return;
			
		}
		//the deck is empty and must be refilled
		else if(deck.isEmpty()) {
			List<Card> cards = well.shuffleNewDeck();
			for(Card c: cards) {
				deck.insert(c);
			}
		}
		
		players.get(playerPlaying).setPlayingRound(false);
		playerPlaying++;
		playerPlaying = playerPlaying % players.size();
		players.get(playerPlaying).setPlayingRound(true);
	}
	
	public void playerPick(Player p, boolean pickFromDeck) {
		Card c;
		if(pickFromDeck) {
			c = deck.pick();
		}
		else {
			c = well.pick();
		}
		p.pickedCard(c);
	}
	
	public void playerDiscard(Player p, Card c) {
		p.getCards().removeCard(c);
		p.deselectAllCards();
		p.setPicked(false);
		well.put(c);
		roundFinished(p);
	}
	
	//the playerPlays passed are all admissible
	//public void playerPlayed(Player p, List<Play> playerPlays) {
	//	for(Play pl: playerPlays) {
	//		boolean newPlayCreated = true;
	//		for(Play gamePlay: plays) {
	//			if(gamePlay.canAttach(pl)) {
	//				//they play does not create a new stack of cards
	//				// so the only thing to do is to add cards to the play
	//				//to which it can be attached
	//				gamePlay.attach(pl);
	//				newPlayCreated = false;
	//				break;
	//				
	//			}
	//		}
	//		
	//		if(newPlayCreated) {
	//			plays.add(pl);
	//			
	//		}
	//		//finally remove cards from the player hand
	//		for(Card c: pl) {
	//			p.getCards().remove(c);
	//		}
	//	}
	//}
	
	public Player getPlayingPlayer() {
		Player player = null;
		for(Player p: this.getPlayers()) {
			if(p.isPlayingRound()){
				player = p;
				break;
			}
		}
		return player;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public Well getWell() {
		return well;
	}

	public void setWell(Well well) {
		this.well = well;
	}

	public List<Play> getPlays() {
		return plays;
	}

	public void setPlays(List<Play> plays) {
		this.plays = plays;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getGameState() {
		return gameState;
	}
	
	public RealPlayer getRealPlayer() {
		return (RealPlayer) this.getPlayers().get(0);
	}
	
	public Card getCardById(int id) {
		return this.allCards.get(id);
	}
	
	public void removeDuplicatePlays() {
		for(int i = 0; i < plays.size(); ++i) {
			for(int j = 0; j<plays.size(); ++j) {
				if(i != j && plays.get(i).equals(plays.get(j)))
					plays.remove(j);
			}
		}
	}

	public int getGameRoundNumber() {
		return gameRoundNumber;
	}

	public void setGameRoundNumber(int gameRoundNumber) {
		this.gameRoundNumber = gameRoundNumber;
	}
}
