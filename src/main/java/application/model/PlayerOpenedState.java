package application.model;

import java.util.List;

public class PlayerOpenedState implements PlayerState{
	private Player player;
	
	public boolean canPlay(HandOfCards cards, List<Play> availablePlays) {
		// TODO Auto-generated method stub
		return false;
	}

	public void pickCard(boolean pickFromDeck) {
		Game.getInstance().playerPick(player, pickFromDeck);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
