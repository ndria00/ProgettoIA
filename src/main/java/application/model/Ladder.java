package application.model;

import java.util.ArrayList;
import java.util.Collections;

public class Ladder  extends Play{
	private static final long serialVersionUID = -6313087748801637144L;
	
	public Ladder(ArrayList<Card> cards){
		super(cards);
		for(int i = 0 ; i < this.size(); ++i) {
			Card c = this.get(i);
			if(differentSuiteFound(c))
				throw new IllegalArgumentException();
			if( i >= 1 && i < cards.size() - 1  && noNextAndPreviousCard(c))
				throw new IllegalArgumentException();
		}
		
	}
	
	private boolean differentSuiteFound(Card c) {
		for(int i = 0 ; i < this.size(); ++i) {
			if(this.get(i).getSuite() != c.getSuite())
			return true;
		}
		return false;
	}
	
	private boolean noNextAndPreviousCard(Card c) {
		boolean found = false;
		
		for(int i = 0 ; i < this.size(); ++i) {
			if(this.get(i).getValue() == c.getValue() + 1 || this.get(i).getValue() == c.getValue() - 1  )
				found = true;
		}
		
		if(!found)
			return true;
		
		return false;		
	}
	
	@Override
	public boolean canAttach(Card card) {
		if(card.getSuite().equals(this.get(0).getSuite())){
			if(this.get(0).getNumber() == card.getNumber() + 1  || this.get(this.size() -1 ).getNumber() == card.getNumber() - 1)
				return true;
		}
		return false;
	}

	@Override
	public boolean canAttach(Play p) {
		if(canAttach(p.get(0)) || canAttach(p.get(p.size()-1))) {
			return true;
		}
		return false;
	}
	@Override
	public void setCards(ArrayList<Card> cards) {
		Collections.sort(cards);
		super.setCards(cards);
	}

	@Override
	public void attach(Play p) {
		for(Card c: p) {
			if(this.get(0).getNumber() == p.get(0).getNumber() + 1)
				this.add(0, c);
			else if(this.get(this.size() -1 ).getNumber() == p.get(p.size() - 1).getNumber() - 1){
				this.add(c);
			}
		}

		
	}
}
