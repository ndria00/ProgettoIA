package application.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Play extends ArrayList<Card>{
	private static final long serialVersionUID = 6089405279947692778L;
	
	public Play() {
		super();
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Play other = (Play) obj;
		for(int i = 0; i < this.size(); ++i) {
			boolean found = false;
			for(int j = 0; j < other.size(); ++j) {
				if(this.get(i).equals(this.get(j))) {
					found = true;
				}
			}
			if(!found)
				return false;
		}
		return true;
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
	
	//attaches all the cards of play p1 to play p
	public abstract void attach(Card card);
	
	//computes points of the play
	public abstract int computeTotalPoints();
	
	//get the atom composed by play and value
	public abstract String getListAndValue(int value);
	
	//get existing play on table
	public abstract String getList(boolean existing);
}
