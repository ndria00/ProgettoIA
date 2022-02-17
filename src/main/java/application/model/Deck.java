package application.model;

import java.util.ArrayList;

import application.Settings;

public class Deck extends ArrayList<Card>{
	private static final long serialVersionUID = -4836853131744333795L;

	public Deck(){
		super();
	}
	
	public Card pick() {
		return this.get(Settings.RANDOM.nextInt(this.size()));
	}
	
	public void insert(Card c) {
		this.add(c);
	}

}
