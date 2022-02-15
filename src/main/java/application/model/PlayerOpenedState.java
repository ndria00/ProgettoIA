package application.model;

import java.util.List;

public class PlayerOpenedState implements PlayerState{
	private Player player;
	
	public boolean canPlay(HandOfCards cards, List<Play> availablePlays) {
		// TODO Auto-generated method stub
		return false;
	}

	public void pickCard(boolean pickFromDeck) {
		Card c;
		if(pickFromDeck) {
			c = Game.getInstance().getDeck().pick();
			
		}
		else {
			c = Game.getInstance().getWell().pick();
		}
		player.getCards().addCard(c);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
