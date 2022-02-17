package application.model;

import java.util.ArrayList;
import java.util.List;

import it.unical.mat.embasp.languages.Id;

@Id("Play")
public abstract class Play extends ArrayList<Card>{

	
	public void setCards(List<Card> cards) {
		this.clear();
		for(Card c: cards) {
			this.add(c);	
		}
	}
	private static final long serialVersionUID = 6089405279947692778L;

	public boolean containsSuite(int suite) {
		return false;
	}
	
	//checks if a card can be attached to a play
	public abstract boolean canAttach(Card card);
	
	//checks if a play (a ladder ) can be added to another play
	public abstract boolean canAttach(Play p);
	
	//attaches all the cards of play p1 to play p
	public abstract void attach(Play p1);
}
