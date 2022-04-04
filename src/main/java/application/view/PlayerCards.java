package application.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PlayerCards extends JPanel{

	private static final long serialVersionUID = 2729122787048018750L;
	
	public ArrayList<CardPanel> selectedCards = new ArrayList<CardPanel>();
	
	private static PlayerCards instance = null;
	
	private PlayerCards() {
		GridLayout gridLayout = new GridLayout(1,13,10,10);
		this.setLayout(gridLayout);
		this.setBackground(new Color(185, 251, 192));
	}
	
	public static PlayerCards getInstance() {
		if(instance == null)
			instance = new PlayerCards();
		return instance;
	}
	
	
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
//		for (CardPanel cardPanel : selectedCards) {
//			System.out.println("Card");
//		}
//		System.out.println("\n");
		this.remove(p);
		selectedCards.remove(p);
	}
}
