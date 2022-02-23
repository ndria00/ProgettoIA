package application.model;

import java.util.List;

public abstract class PlayerState {
	
	private Player player;
	//checks that the player can do a legal play
	//In this specific case the correct ASP program will be called and then the play is made 
	public abstract boolean canPlay(HandOfCards cards, List<Play> availablePlays);
	
	public abstract void pickCard(boolean pickFromDeck);
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
