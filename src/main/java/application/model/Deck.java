package application.model;

import java.util.ArrayList;
import java.util.List;

import application.Settings;

public class Deck {
	
	private List<Card> cards;

	public Deck(){
		cards = new ArrayList<Card>();
	}
	
	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	public Card pick() {
		return cards.get(Settings.RANDOM.nextInt(cards.size()));
	}
	
	public void insert(Card c) {
		cards.add(c);
	}

}
