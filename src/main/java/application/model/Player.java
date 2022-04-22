package application.model;

import java.util.List;

import application.Settings;

public abstract class Player {
	private HandOfCards cards;
	private PlayerState state;
	private boolean playingRound;
	private boolean picked;
	private int points;
	
	public Player() {
		setPlayingRound(false);
		setPicked(false);
		setPoints(0);
		setState(new PlayerNotOpenedState());
		cards = new HandOfCards();
		state.setPlayer(this);
	}
	
	public HandOfCards getCards() {
		return cards;
	}


	public void setCards(HandOfCards cards) {
		this.cards = cards;
	}
	

	
	//puts a card in the well
	public void removeCard(Card c) {
		Game.getInstance().playerDiscard(this, c);
		Game.getInstance().roundFinished(this);
		this.picked = false;
	}

	public boolean hasLost() {
		return points >= Settings.MAX_POINTS;
	}
	
	//public abstract boolean canPlay(List<Play> availablePlays);

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public void setState(PlayerState state) {
		this.state = state;
	}
	public PlayerState getState() {
		return state;
	}

	public boolean isPlayingRound() {
		return playingRound;
	}

	public void setPlayingRound(boolean playingRound) {
		this.playingRound = playingRound;
	}

	public boolean hasPicked() {
		return picked;
	}

	public void setPicked(boolean picked) {
		this.picked = picked;
	}
}
