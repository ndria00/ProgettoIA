package application.model;

import java.util.ArrayList;

import application.Settings;

public class Combination extends Play{
	private static final long serialVersionUID = -1237617730228735896L;
	
	public Combination() {
		super();
	}
	
	public Combination(ArrayList<Card> cards) {
		super(cards);
		int value = cards.get(0).getValue();
		for(int i = 1; i < cards.size(); ++i) {
			if(cards.get(i).getValue() != value)
				throw new IllegalArgumentException();
		}
	}
	
	@Override
	public boolean canAttach(Card card) {
		//cards are of the same suite
		if(card.getNumber() == this.get(0).getNumber() && this.size() < 4 && ! this.contains(card)) {
			return true;
		}
		if(card.getNumber() == Settings.JOKER_NUMBER && this.size() < 4)
			return true;
		return false;
	}
	@Override
	public void attach(Card c) {
		this.add(c);
	}
	
	public String getListAndValue(int value) {
		StringBuilder builder = new StringBuilder();
		builder.append("combination([");
		for(int i = 0; i < this.size()-1; ++i) {
			builder.append(this.get(i).getId()+",");
		}
		builder.append(this.get(this.size() - 1).getId()+"]," + value+ ").");
		return builder.toString();
	}

	@Override
	public int computeTotalPoints() {
		int points;
		if(this.get(0).getNumber() == Settings.ACE_NUMBER) {
			points = this.get(0).getValue() * this.size();
		}else if (this.get(0).getNumber() == Settings.JOKER_NUMBER) {
			points = this.get(1).getValue() * this.size();
		}
		else {
			points = this.get(0).getValue() * this.size();
		}
		return points;
	}

	@Override
	public String getList(boolean existing) {
		StringBuilder builder = new StringBuilder();
		if(existing) {
			builder.append("existingCombination([");
		}
		else {
			builder.append("combination([");
		}
		for(int i = 0; i < this.size()-1; ++i) {
			builder.append(this.get(i).getId()+",");
		}
		builder.append(this.get(this.size() - 1).getId()+"]).");
		return builder.toString();
	}
	
}