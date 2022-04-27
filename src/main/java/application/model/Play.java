package application.model;

import java.util.ArrayList;

public abstract class Play extends ArrayList<Card>{
	private static final long serialVersionUID = 6089405279947692778L;
	
	public Play() {
		super();
	}
	
	public Play(ArrayList<Card> cards) {
		setCards(cards);
	}
	
	public void setCards(ArrayList<Card> cards) {
		this.clear();
		this.addAll(cards);
	}

	public boolean containsSuite(int suite) {
		return false;
	}
	
	//checks if a card can be attached to a play
	public abstract boolean canAttach(Card card);
	
	//checks if a play (a ladder ) can be added to another play
	public abstract boolean canAttach(Play p);
	
	//attaches all the cards of play p1 to play p
	public abstract void attach(Play p1);
	
	//computes points of the play
	public abstract int computeTotalPoints();
	
	//get the atom composed by play and value
	public abstract String getListAndValue(int value);
	
	//get existing play on table
	public abstract String getList(boolean existing);
}
