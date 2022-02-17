package application.model;

import java.util.Collections;
import java.util.List;

public class Ladder  extends Play{
	private static final long serialVersionUID = -6313087748801637144L;
	
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
	public void setCards(List<Card> cards) {
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
