package application.asp;

import java.util.ArrayList;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("combination")
public class PlayableCombination {


	@Param(0)
	private ArrayList<Integer> cards;

	@Override
	public String toString() {
		return "PlayableCombination [cards=" + cards + "]";
	}
	
	public PlayableCombination() {
		super();
	}

	public ArrayList<Integer> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Integer> cards) {
		this.cards = cards;
	}
}
