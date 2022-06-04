package application.model;

import java.util.List;

import application.asp.ASPManager;

public class PlayerOpenedState extends PlayerState{
	
	public boolean play(HandOfCards cards, List<Play> availablePlays) {
		boolean played = ASPManager.getInstance().canPlay(cards, getPlayer());
		if(played)
			this.getPlayer().deselectAllCards();
		return played;
	}

	public void pickCard() {
		boolean pickFromDeck = ASPManager.getInstance().handlePick(getPlayer());
		Game.getInstance().playerPick(getPlayer(), pickFromDeck);
	}

	public boolean extendPlay(HandOfCards selectedCards, Play p) {
		return ASPManager.getInstance().canExtendGame(selectedCards, getPlayer(), p);
	}
}
