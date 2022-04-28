package application.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import application.CardToImage;
import application.model.Card;
import application.model.Game;

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
		for (Component c : this.getComponents()) {
			this.remove(c);
		}
	}
	
	public void addCards(ArrayList<Card> cards) {
		for(Card card : cards) {

			CardPanel cardPanel = new CardPanel(CardToImage.getInstance().getImageFromCard(card), this);
			cardPanel.setCard(card);
			if(Game.getInstance().getRealPlayer().getSelectedCards().contains(card)) {
				cardPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4));
				cardPanel.setClicked(true);
			}
			this.add(cardPanel);
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
	
	
	public void update() {
		this.removeCards();
		this.addCards(Game.getInstance().getRealPlayer().getCards());
		this.revalidate();
	}
	
	public CardPanel getCardPanelFromCard(Card c) {
		for (Component component : this.getComponents()) {
			CardPanel cp = (CardPanel)component;
			if(cp.getCard().equals(c))
				return cp;
		}
		return null;
	}
}
