package application;

import application.model.Game;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world");
		Game.getInstance();
		Game.getInstance().startNewGame(2, Settings.DIFFICULTY_MASTER);
		System.out.println("Players: " + Game.getInstance().getPlayers().size());
		System.out.println("Difficulty: " + Game.getInstance().getDifficulty());
		System.out.println("There are " + Game.getInstance().getDeck().size() + " cards left in the deck");
		System.out.println("There is " + Game.getInstance().getWell().size() + " card in the well");
		System.out.println("Player playing 0: " + Game.getInstance().getPlayers().get(0).isPlayingRound());
		Game.getInstance().getPlayers().get(0).discard(Game.getInstance().getPlayers().get(0).getCards().get(0));
		System.out.println("There are " + Game.getInstance().getDeck().size() + " cards left in the deck");
		System.out.println("There is " + Game.getInstance().getWell().size() + " card in the well");
		System.out.println("Player playing 1: " + Game.getInstance().getPlayers().get(1).isPlayingRound());
	}
}
