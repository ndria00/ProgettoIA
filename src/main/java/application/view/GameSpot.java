package application.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class GameSpot extends JPanel implements MouseInputListener{
	private static final long serialVersionUID = 6535477337343318403L;
	
	private List<CardPanel> cards = null;
	//private boolean taken = false;
	private PlayerCardsPanel owner = null;
	
	public GameSpot(PlayerCardsPanel p) {
		super( new GridLayout(1,0,-25,0));
		this.owner = p;
//		GridLayout layout = new GridLayout(1,0,-25,0);
//		this.setLayout(layout);
		cards = new ArrayList<CardPanel>();
		super.setBackground(new Color(185, 251, 192));
		this.setBorder(BorderFactory.createLineBorder(Color.RED,1));
		this.addMouseListener(this);
		//this.setMinimumSize(new Dimension(400,120));
		//this.setPreferredSize(new Dimension(400, 120));
		//this.setSize(new Dimension(400,120));
	}
	
	public void placeCards(Vector<CardPanel> cards) {
		//if(!this.taken) {
			this.cards.addAll(cards);
			for (CardPanel cardPanel : cards) {
				super.add(cardPanel);
			}
			//this.taken = true;
		//}
	}
	
	public void removeAllCards() {
		for (CardPanel cardPanel : this.cards) {
			this.cards.remove(cardPanel);
			this.remove(cardPanel);
		}
		//this.taken = false;
	}

	public void mouseClicked(MouseEvent e) {
		if (owner.selectedCards.size() > 0) {
			this.placeCards(owner.spostaCarte());
			owner.removeCards();
			this.revalidate();
		}
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		
	}

	public void mouseExited(MouseEvent e) {
		this.setBorder(BorderFactory.createLineBorder(Color.RED,1));
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
