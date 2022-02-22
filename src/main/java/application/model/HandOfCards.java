package application.model;

import java.util.ArrayList;

import application.Settings;

public class HandOfCards extends ArrayList<Card>{
	private static final long serialVersionUID = 9134621157755929545L;
	
	
	// checks if the player that owns this hand of cards
	//is able to open the game  . I.e. he can play a game of 40 or more points
	public boolean canOpen() {
		return false;
	}
	
	public void discard(Card c) {
		this.remove(c);
	}
	
	public void addCard(Card c) {
		this.add(c);
	}
	
	public boolean isEmpty() {
		return super.isEmpty();
	}
	
	public int computeTotalPoints() {
		int totalPoints = 0;
		for(Card c: this) {
			//if the player owns a joker he must pay 25 points 
			if(c.getNumber() == Settings.JOKER_NUMBER)
				totalPoints += Settings.JOKER_VALUE_ON_LOOSE;
			else
				totalPoints += c.getValue();
		}
		return totalPoints;
	}
}
