package application.model;

import java.util.List;

public abstract class PlayerState {
	
	private Player player;
	//checks that the player can do a legal play
	//In this specific case the correct ASP program will be called and then the play is made
	//When the method is being called by a RealPlayer a HandOfCards is generated on the spot
	//with the selected cards so to make computation faster
	//If the play is made all the played cards will be removed form the player's HandOfCard
	
	public abstract boolean play(HandOfCards cards, List<Play> availablePlays);
	
	public abstract void pickCard();
	
	public abstract boolean extendPlay(HandOfCards cards, Play p);
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
