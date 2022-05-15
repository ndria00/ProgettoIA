package application;

import java.util.Random;

public class Settings {
	public static final Random RANDOM= new Random();
	public static final int MAX_POINTS = 110; 
	public static final int JOKER_NUMBER = 0;
	public static final int ACE_NUMBER = 1;
	public static final int JOKER_VALUE_ON_LOOSE = 25;
	public static final int PLAYER_NULL = -1;
	public static final int DIFFICULTY_BEGINNER = 0;
	public static final int DIFFICULTY_INTERMEDIATE = 1;
	public static final int DIFFICULTY_MASTER = 2;
	public static final int  GAME_PLAYING_STATE = 1;
	public static final int  GAME_FINISHED_STATE = 2;
	
	public static final int ONE_VERSUS_ONE = 0;
	public static final int ONE_VERSUS_THREE = 0;
	
	public static final int SUITE_HEARTHS = 2;
	public static final int SUITE_SPADES = 3;
	public static final int SUITE_DIAMONDS = 1;
	public static final int SUITE_CLUBS = 0;
	
	public static final boolean NO_BORDER = false;
	public static final boolean BORDER = true;
	
	public static final int JOKER_SUITE = 5;
	public static final int KING_NUMBER = 13;
	public static final int TWO_NUMBER = 2;
}
