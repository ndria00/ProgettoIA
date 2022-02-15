package application.model;

import java.util.List;

public class HandOfCards {
	private List<Card> cards;
	
	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	// checks if the player that owns this hand of cards
	//is able to open the game  . I.e. he can play a game of 40 or more points
	public boolean canOpen() {
		return false;
	}
	
	public void discard(Card c) {
		cards.remove(c);
	}
	
	public void addCard(Card c) {
		cards.add(c);
	}
}
