package application.model;

import java.util.List;

public class BotPlayer extends Player{
	
	public BotPlayer() {
		super();
	}
	
	public boolean play(List<Play> availablePlays){
		return getState().play(getCards(), availablePlays);
	}

	//@Override
	//public void play(List<Play> plays) {
	//	Game.getInstance().playerPlayed(null, plays);
	//	Card c = computeDiscardCard();
	//	discard(c);	
	//}
	
	//private Card computeDiscardCard() {
	//	return null;
	//}
	
	@Override
	public void setPlayingRound(boolean playingRound) {
		super.setPlayingRound(playingRound);
		if(playingRound) {
			this.getState().pickCard(true);
			System.out.println("Bot playing");
			this.getState().play(this.getCards(), Game.getInstance().getPlays());
		}
	}

}