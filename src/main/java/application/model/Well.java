package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Well extends Stack<Card>{
	private static final long serialVersionUID = 1717163881700262083L;
	

	public Well() {
		super();
	}
	
	public Card pick() {
		return this.pop();
	}
	
	public void put(Card card) {
		this.push(card);
	}
	
	//empties the well and gives back the cards that will form the new deck.
	//Puts back in the well the first card of the stack (the one that was alreadyVisible)
	public List<Card> shuffleNewDeck(){
		Card lastCard = this.pop();
		List<Card> cards = new ArrayList<Card>();
		
		while(!this.empty()) {
			cards.add(this.pop());
		}
		
		this.clear();
		this.push(lastCard);
		return cards;
	}
	
}
