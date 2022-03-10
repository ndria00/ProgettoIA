package application.model;

import java.util.List;

public class PlayerOpenedState extends PlayerState{
	
	public boolean canPlay(HandOfCards cards, List<Play> availablePlays) {
		//check that there are possible plays for the player given the plays and its cards
		return false;
	}

	public void pickCard(boolean pickFromDeck) {
		Game.getInstance().playerPick(getPlayer(), pickFromDeck);
	}

}
