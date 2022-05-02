package application.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import application.CardToImage;
import application.Settings;
import application.controller.GameSpotController;
import application.model.Card;
import application.model.Play;

public class GameSpot extends JPanel {
	private static final long serialVersionUID = 6535477337343318403L;
	
	private List<Card> modelCards = null;
	private List<CardPanel> cardPanels = null;
	//private boolean taken = false;
	public PlayerCardsPanel owner = null;
	private Integer id = null;
	private Play play = null;
	
	public GameSpot(PlayerCardsPanel p, Integer id) {
		super( new GridLayout(1,0,-25,0));
		this.owner = p;
		this.id = id;
//		GridLayout layout = new GridLayout(1,0,-25,0);
//		this.setLayout(layout);
		modelCards = new ArrayList<Card>();
		cardPanels = new ArrayList<CardPanel>();
		super.setBackground(new Color(185, 251, 192));
		this.setBorder(BorderFactory.createLineBorder(Color.RED,1));
		this.addMouseListener(new GameSpotController(this));
		//this.setMinimumSize(new Dimension(400,120));
		//this.setPreferredSize(new Dimension(400, 120));
		//this.setSize(new Dimension(400,120));
	}
	
	public void setPlay(Play p) {
		this.play = p;
	}
	
	public void placeCards(List<Card> cards) {
		//if(!this.taken) {
			this.modelCards.addAll(cards);
			for (Card card : modelCards) {
				CardPanel p = new CardPanel(CardToImage.getInstance().getImageFromCard(card),Settings.NO_BORDER);
				p.setCard(card);
				this.cardPanels.add(p);
				super.add(p);
				//this.play.add(cardPanel.getCard());
			}
			this.revalidate();
			//this.taken = true;
		//}
	}
	
	public void removeAllCards() {
		for (CardPanel cardPanel : this.cardPanels) {
			this.modelCards.remove(cardPanel.getCard());
			this.remove(cardPanel);
		}
		//this.taken = false;
	}

	
	
	public ArrayList<Card> getModelCards(){
//		ArrayList<Card> modelCards = new ArrayList<Card>();
//		for (CardPanel cardPanel : cards) {
//			modelCards.add(cardPanel.getCard());
//		}
//		return modelCards;
		return (ArrayList<Card>)this.modelCards;
	}
	
	
	public ArrayList<CardPanel> getCardPanels(){
		return (ArrayList<CardPanel>)this.cardPanels;
	}
	
//	public void mouseClicked(MouseEvent e) {
//		if (owner.selectedCards.size() > 0) {
//			
//			this.placeCards(owner.spostaCarte());
//			owner.removeCards();
//			this.revalidate();
//		}
//		
//	}
//	
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void mouseEntered(MouseEvent e) {
//		this.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
//		
//	}
//
//	public void mouseExited(MouseEvent e) {
//		this.setBorder(BorderFactory.createLineBorder(Color.RED,1));
//	}
//
//	public void mouseDragged(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void mouseMoved(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}

}
