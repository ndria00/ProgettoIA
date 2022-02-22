package application.model;

import java.util.List;

public class BotPlayer extends Player{

	@Override
	public boolean canPlay(List<Play> availablePlays){
		return getState().canPlay(getCards(), availablePlays);
	}

	@Override
	public void play(List<Play> plays) {
		Game.getInstance().playerPlayed(null, plays);
		Card c = computeDiscardCard();
		discard(c);	
	}
	
	private Card computeDiscardCard() {
		return null;
	}
}