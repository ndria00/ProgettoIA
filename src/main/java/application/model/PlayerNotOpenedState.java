package application.model;

import java.util.List;

import application.asp.ASPManager;

public class PlayerNotOpenedState extends PlayerState{
	
	public boolean canPlay(HandOfCards cards, List<Play> availablePlays) {
		//check with ASP that within the cards there is a combination of plays that
		//exceed the 40 points
		ASPManager.getInstance().canOpen(getPlayer());
		return false;
	}

	public void pickCard(boolean pickFromDeck) {
		if(pickFromDeck) {
			Game.getInstance().playerPick(getPlayer(), pickFromDeck);
		}
		else {
			//c = Game.getInstance().getWell().pick();
			throw new IllegalArgumentException();
		}
	}

}
