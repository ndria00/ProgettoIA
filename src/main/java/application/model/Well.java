package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Well {
	private Stack<Card> wellCards;

	public Well() {
		super();
	}
	
	public Card pick() {
		return wellCards.pop();
	}
	
	public void put(Card card) {
		wellCards.push(card);
	}
	
	//empties the well and gives back the cards that will form the new deck.
	//Puts back in the well the first card of the stack (the one that was alreadyVisible)
	public List<Card> shuffleNewDeck(){
		Card lastCard = wellCards.pop();
		List<Card> cards = new ArrayList<Card>();
		
		while(!wellCards.empty()) {
			cards.add(wellCards.pop());
		}
		
		wellCards.clear();
		wellCards.push(lastCard);
		return cards;
	}
	
}
