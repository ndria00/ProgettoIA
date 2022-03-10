package application.model;

import java.util.List;

import application.Settings;

public abstract class Player {
	private HandOfCards cards;
	private PlayerState state;
	private boolean playingRound;
	private int points;
	
	public Player() {
		setPlayingRound(false);
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
	
	//plays a non negative number of cards
	public abstract void play(List<Play> plays);
	
	//puts a card in the well
	public void discard(Card c) {
		Game.getInstance().playerDiscard(this, c);
		Game.getInstance().roundFinished(this);
	}

	public boolean hasLost() {
		return points >= Settings.MAX_POINTS;
	}
	
	public abstract boolean canPlay(List<Play> availablePlays);

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
}
