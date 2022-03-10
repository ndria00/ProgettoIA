package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.Settings;

public class Game {
	private List<Player> players;
	private Deck deck;
	private Well well;
	private List<Play> plays;
	Map<Integer, Card> allCards;
	
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
				c.setNumber(Integer.parseInt(v[1]));
				c.setSuite(v[2]);
				c.setValue(Integer.parseInt(v[3]));
				//System.out.println(c.toString());
				allCards.put(c.getId(), c);
				deck.insert(c);
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
		players.add(p);
		
		for(int i = 1; i<numberOfPlayers; ++i) {
			p = new BotPlayer();
			players.add(p);
		}
		distributeCards();
		well.push(deck.pick());
		gameState = Settings.GAME_PLAYING_STATE;
	}

	public void nextMatch(Player winnerPlayer) {
		//remove all the players that have lost the match
		for(int i = 0; i < players.size(); ++i) {
			if( players.get(i).getPoints() > Settings.MAX_POINTS)
				players.remove(i);
		}
		//there is only one player left and he is the winner
		if(players.size() == 1) {
			gameState = Settings.GAME_FINISHED_STATE;
			//show winner modal and let him choose what to do
			//AS SOON AS IT READY
		}
		else {
			deck.setCards(allCards.values());
			well.clear();
			well.push(deck.pick());
			distributeCards();
			playerPlaying = players.indexOf(winnerPlayer);
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
			return;
			
		}
		//the deck is emply and must be refilled
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
		p.getCards().add(c);
	}
	
	public void playerDiscard(Player p, Card c) {
		p.getCards().discard(c);
		well.put(c);
	}
	
	//the playerPlays passed are all admissible
	public void playerPlayed(Player p, List<Play> playerPlays) {
		for(Play pl: playerPlays) {
			boolean newPlayCreated = true;
			for(Play gamePlay: plays) {
				if(gamePlay.canAttach(pl)) {
					//they play does not create a new stack of cards
					// so the only thing to do is to add cards to the play
					//to which it can be attached
					gamePlay.attach(pl);
					newPlayCreated = false;
					break;
					
				}
			}
			
			if(newPlayCreated) {
				plays.add(pl);
				
			}
			//finally remove cards from the player hand
			for(Card c: pl) {
				p.getCards().remove(c);
			}
		}
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
	
	
}
