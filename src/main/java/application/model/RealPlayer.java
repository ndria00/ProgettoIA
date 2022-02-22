package application.model;

import java.util.List;

public class RealPlayer extends Player{

	@Override
	public void play(List<Play> plays) {
		Game.getInstance().playerPlayed(null, plays);
	}

	@Override
	public boolean canPlay(List<Play> availablePlays) {
		// TODO Auto-generated method stub
		return false;
	}

}
