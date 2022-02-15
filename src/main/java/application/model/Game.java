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
