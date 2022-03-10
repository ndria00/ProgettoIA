package application.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GameSpot extends JPanel{
	private static final long serialVersionUID = 6535477337343318403L;
	
	private List<CardPanel> cards = null;
	private boolean taken = false;
	
	public GameSpot() {
		GridLayout layout = new GridLayout(1,0,-25,0);
		this.setLayout(layout);
		cards = new ArrayList<CardPanel>();
		this.setBackground(Color.GRAY);
		//this.setMinimumSize(new Dimension(400,120));
		//this.setPreferredSize(new Dimension(400,120));
		//this.setSize(new Dimension(400,120));
	}
	
	public void placeCards(ArrayList<CardPanel> cards) {
		if(!this.taken) {
			this.cards.addAll(cards);
			for (CardPanel cardPanel : cards) {
				this.add(cardPanel);
			}
			this.taken = true;
		}
	}
	
	public void removeAllCards() {
		for (CardPanel cardPanel : this.cards) {
			this.cards.remove(cardPanel);
			this.remove(cardPanel);
		}
		this.taken = false;
	}

}
