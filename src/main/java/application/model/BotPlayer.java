package application.model;

import java.util.List;

import application.asp.ASPManager;
import application.view.PlayView;

public class BotPlayer extends Player{
	
	public BotPlayer() {
		super();
	}
	
	@Override
	public boolean play(List<Play> availablePlays){
		this.getState().pickCard();
		PlayView.getInstance().getDeckAndWellPanel().getWellPanel().updateWellPanel();
		PlayView.getInstance().getBotCardsPanel().update();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean played =  getState().play(getCards(), availablePlays);
		if(played)
			PlayView.getInstance().updateGameSpots();
			PlayView.getInstance().getBotCardsPanel().update();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Card c = ASPManager.getInstance().handleBotDiscard(this);		
		Game.getInstance().playerDiscard(this, c);
		
		PlayView.getInstance().getDeckAndWellPanel().getWellPanel().updateWellPanel();
		PlayView.getInstance().getBotCardsPanel().update();
//		PlayView.getInstance().getBotCardsPanel().revalidate();
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
	}
	
	//bot cannot select cards for now
	@Override
	public void deselectAllCards() {}
	@Override
	public boolean extendPlay(Play p) {
		return false;
	}
}