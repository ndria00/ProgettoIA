package application.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;

public class PlayerCardsPanel extends JPanel{

	private static final long serialVersionUID = 2729122787048018750L;
	
	public ArrayList<CardPanel> selectedCards = new ArrayList<CardPanel>();
	
	private static PlayerCardsPanel instance = null;
	
	private PlayerCardsPanel() {
		GridLayout gridLayout = new GridLayout(1,13,10,10);
		this.setLayout(gridLayout);
		this.setBackground(new Color(185, 251, 192));
	}
	
	public static PlayerCardsPanel getInstance() {
		if(instance == null)
			instance = new PlayerCardsPanel();
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
	
	public void removeCard(CardPanel p, MouseEvent e) {
//		for (CardPanel cardPanel : selectedCards) {
//			System.out.println("Card");
//		}
//		System.out.println("\n");
		selectedCards.remove(p);
		if(e.getSource() == WellPanel.class) {
			this.remove(p);
		}
	}
}
