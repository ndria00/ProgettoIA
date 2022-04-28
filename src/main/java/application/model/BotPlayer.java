package application.model;

import java.util.List;

import application.asp.ASPManager;
import application.view.PlayView;

public class BotPlayer extends Player{
	
	public BotPlayer() {
		super();
	}
	
	public boolean play(List<Play> availablePlays){
		boolean played =  getState().play(getCards(), availablePlays);
		if(played)
			PlayView.getInstance().updateGameSpots();
		return played;
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
			this.play(Game.getInstance().getPlays());
			ASPManager.getInstance().handleBotDiscard(this);
			PlayView.getInstance().getDeckAndWellPanel().getWellPanel().updateWellPanel();
			PlayView.getInstance().getBotCardsPanel().update();
		}
	}
	
	//bot cannot select cards for now
	@Override
	public void deselectAllCards() {}
}