package application.model;

import java.util.List;

public abstract class Play {
	private List<Card> cards;

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	public boolean containsSuite(int suite) {
		return false;
	}
	
	//checks if a card can be attached to a play
	public abstract boolean canAttach(Card card);
	
	//checks if a play (a ladder ) can be added to another play
	public abstract boolean canAttach(Play p);
}
