package application.model;

import java.util.ArrayList;
import java.util.Collection;

import application.Settings;

public class Deck extends ArrayList<Card>{
	private static final long serialVersionUID = -4836853131744333795L;

	public Deck(){
		super();
	}
	
	public Card pick() {
		//System.out.println("DECK SIZE: " + this.size());
		int index = Settings.RANDOM.nextInt(this.size());
		Card c = this.get(index);
		this.remove(c);
		System.out.println("CARD PICKED!: " + c.getId());
	
		//for(int i=0; i< this.size(); ++i) {
		//	System.out.println(this.get(i).getId());
		//}
		
		return c;
	}
	
	public void insert(Card c) {
		this.add(c);
	}

	public void setCards(Collection<Card> cards) {
		this.clear();
		this.addAll(cards);
	}
}
