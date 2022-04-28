package application.model;

import java.util.List;

import application.asp.ASPManager;

public class PlayerOpenedState extends PlayerState{
	
	public boolean play(HandOfCards cards, List<Play> availablePlays) {
		boolean played = ASPManager.getInstance().canPlay(cards, getPlayer());
		
		return played;
	}

	public void pickCard(boolean pickFromDeck) {
		Game.getInstance().playerPick(getPlayer(), pickFromDeck);
		this.getPlayer().setPicked(true);
	}

}
