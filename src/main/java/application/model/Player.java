package application.model;

import java.util.List;

import application.Settings;

public class Player {
	
	private HandOfCards cards;
	private PlayerState state;
	private int points;
	
	public Player() {
		setPoints(0);
		setState(new PlayerNotOpenedState());
	}
	
	public HandOfCards getCards() {
		return cards;
	}


	public void setCards(HandOfCards cards) {
		this.cards = cards;
	}
	
	//plays a non negative number of cards
	public void play(List<Play> plays) {
		
	}
	
	//puts a card in the well
	public void discard(Card c) {
		cards.discard(c);
		Game.getInstance().getWell().put(c);
	}
	
	//picks a card from the well and puts it in the hand
	public void pick() {
		Game.getInstance().getWell();
	}

	public boolean hasLost() {
		return points >= Settings.MAX_POINTS;
	}
	
	public boolean canPlay(List<Play> availablePlays) {
		return state.canPlay(cards, availablePlays);
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public void setState(PlayerState state) {
		this.state = state;
	}
}
