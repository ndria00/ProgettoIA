package application.model;

import java.util.ArrayList;
import java.util.List;

import application.view.PlayView;

public class RealPlayer extends Player{
	private ArrayList<Card> selectedCards;
	
	public RealPlayer() {
		selectedCards =  new ArrayList<Card>();
	}
	
	//@Override
	//public void play(List<Play> plays) {
	//	Game.getInstance().playerPlayed(null, plays);
	//}
	@Override
	public boolean play(List<Play> availablePlays) {
		HandOfCards handOfSelectedCards = new HandOfCards();
		for (Card c: selectedCards) {
			handOfSelectedCards.add(c);
		}
		//Call asp program that will try to play all the cards
		//if some of the cards cannot be played then it will 
		boolean played = this.getState().play(handOfSelectedCards, null);
		if(played)
			PlayView.getInstance().updateGameSpots();
		// TODO Auto-generated method stub
		return played;
	}
	
	public ArrayList<Card> getSelectedCards(){
		return selectedCards;
	}
	
	public List<Card> getCopiaSelectedCards(){
		List<Card> destination = new ArrayList<Card>(selectedCards);
	    return destination;
	}
	
	@Override
	public void deselectAllCards() {
		this.getSelectedCards().clear();
	}

}
