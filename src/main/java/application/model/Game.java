package application.model;

import java.util.List;

public class Game {
	private List<Player> players;
	private List<Card> deck;
	private List<Card> well;
	

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Card> getDeck() {
		return deck;
	}

	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}

	public List<Card> getWell() {
		return well;
	}

	public void setWell(List<Card> well) {
		this.well = well;
	}
	
	
}
