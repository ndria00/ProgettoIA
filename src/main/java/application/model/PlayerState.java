package application.model;

import java.util.List;

public interface PlayerState {
	//checks that the player can do a legal play
	//In this specific case the correct ASP program will be called and then the play is made 
	public boolean canPlay(HandOfCards cards, List<Play> availablePlays);
	
	public void pickCard(boolean pickFromDeck);
	
}
