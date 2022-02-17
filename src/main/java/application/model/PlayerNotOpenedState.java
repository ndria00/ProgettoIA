package application.model;

import java.util.List;

public class PlayerNotOpenedState implements PlayerState{
	private Player player;
	
	public boolean canPlay(HandOfCards cards, List<Play> availablePlays) {
		return false;
	}

	public void pickCard(boolean pickFromDeck) {
		if(pickFromDeck) {
			Game.getInstance().playerPick(player, pickFromDeck);
		}
		else {
			//c = Game.getInstance().getWell().pick();
			throw new IllegalArgumentException();
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
