package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<Player> players;
	private Deck deck;
	private Well well;
	private List<Play> plays;
	List<Card> allCards;
	
	
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
		
		File f;
		try {
			f = new File(getClass().getResource("/application/resources/cards.txt").toURI());
			BufferedReader reader = new BufferedReader(new FileReader(f));
			while(reader.ready()) {
				String line = reader.readLine();
				String [] v = line.split("\t");
				Card c = new Card();
				c.setNumber(Integer.parseInt(v[0]));
				c.setSuite(v[1]);
				c.setValue(Integer.parseInt(v[2]));
				System.out.println(c.toString());
				deck.insert(c);
			}
			reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void distributeCards() {
		Card c;
		for(Player p: players) {
			for(int i = 0 ; i < 12; ++i) {
				c = deck.pick();
				deck.remove(c);
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
		}
		//the deck is emply and must be refilled
		else if(deck.isEmpty()) {
			List<Card> cards = well.shuffleNewDeck();
			for(Card c: cards) {
				deck.insert(c);
			}
		}
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
	
	//they playerPlays passed are all admissible
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
	
	
}
