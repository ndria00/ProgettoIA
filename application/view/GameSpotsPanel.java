package application.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GameSpotsPanel extends JPanel{
	private static final long serialVersionUID = 5201702208157612762L;
	
	private ArrayList<GameSpot> gameSpots = new ArrayList<GameSpot>();
	public final static int gameSpotsNum = 20;
	
	
	
	public GameSpotsPanel() {
		this.setLayout(new GridLayout(4,4,4,4));
		this.setBackground(new Color(185, 251, 192));
		
		for(int i = 0; i < gameSpotsNum; ++i) {
			GameSpot gs = new GameSpot(PlayerCardsPanel.getInstance());
			this.addGameSpot(gs);
		}
	}
	
	
	public void addGameSpot(GameSpot gs) {
		this.add(gs);
		this.gameSpots.add(gs);
	}
	
	public ArrayList<GameSpot> getGameSpots(){
		return this.gameSpots;
	}
	
	
	public void update() {
		//Update from model
		// ....
		
		this.revalidate();
	}
	

}
