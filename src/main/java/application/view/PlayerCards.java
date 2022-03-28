package application.view;

import java.util.ArrayList;

import javax.swing.JPanel;

public class PlayerCards extends JPanel{

	private static final long serialVersionUID = 2729122787048018750L;
	
	public ArrayList<CardPanel> selectedCards = new ArrayList<CardPanel>();
	
	public PlayerCards() {}
	
	
	public void addSelectedCard(CardPanel p) {
		System.out.println(p);
		this.selectedCards.add(p);
	}
	
	public ArrayList<CardPanel> spostaCarte() {
		return selectedCards;
	}
	
	public void removeCards() {
		for (CardPanel cardPanel : selectedCards) {
			this.remove(cardPanel);
		}
	}
	
	public void removeCard(CardPanel p) {
		for (CardPanel cardPanel : selectedCards) {
			System.out.println("Card");
		}
		System.out.println("\n");
		this.remove(p);
		selectedCards.remove(p);
	}
}
