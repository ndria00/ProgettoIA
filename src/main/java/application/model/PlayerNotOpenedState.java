package application.model;

import java.util.List;

import application.asp.ASPManager;

public class PlayerNotOpenedState extends PlayerState{
	
	public boolean play(HandOfCards cards, List<Play> availablePlays) {
		//check with ASP that within the cards there is a combination of plays that
		//exceed the 40 points
		//this.getPlayer().getState().pickCard(true);
		boolean opened = ASPManager.getInstance().canOpen(cards, getPlayer());
		if(opened) {
			this.getPlayer().setState(new PlayerOpenedState());
			this.getPlayer().getState().setPlayer(this.getPlayer());
			this.getPlayer().deselectAllCards();
			System.out.println("Switched bot state to opened state");
		}
			
		return opened;
	}

	public void pickCard() {
		//always pick from deck when not opened
		Game.getInstance().playerPick(getPlayer(), true);
	}

}
