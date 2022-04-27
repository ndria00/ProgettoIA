package application.model;

import java.util.List;

import application.asp.ASPManager;

public class PlayerNotOpenedState extends PlayerState{
	
	public boolean play(HandOfCards cards, List<Play> availablePlays) {
		//check with ASP that within the cards there is a combination of plays that
		//exceed the 40 points
		if(ASPManager.getInstance().canOpen(cards, getPlayer())) {
			this.getPlayer().setState(new PlayerOpenedState());
			System.out.println("Switched bot state to opened state");
		}
		return false;
	}

	public void pickCard(boolean pickFromDeck) {
		if(pickFromDeck) {
			Game.getInstance().playerPick(getPlayer(), pickFromDeck);

			this.getPlayer().setPicked(true);
		}//Player can't pick from well before opening
		else {
			//c = Game.getInstance().getWell().pick();
			throw new IllegalArgumentException();
		}
	}

}
