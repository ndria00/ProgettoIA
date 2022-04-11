package application.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.event.MouseInputListener;

import application.model.Game;
import application.view.CardPanel;

public class CardPanelController implements MouseInputListener{
	private CardPanel cardPanel = null;
	public CardPanelController(CardPanel p) {
		this.cardPanel = p;
	}

	public void mouseClicked(MouseEvent e) {
		if(!cardPanel.getClicked()) {
			if(this.cardPanel.getOwner() != null) {
				Game.getInstance().getRealPlayer().getSelectedCards().add(this.cardPanel.getCard());
			}
			this.cardPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW,4));
			this.cardPanel.setClicked(true);
		}else {
			this.cardPanel.setBorder(null);
			this.cardPanel.setClicked(false);
			if(this.cardPanel.getOwner() != null) {
				CardPanel source = (CardPanel)e.getSource();
				Game.getInstance().getRealPlayer().getSelectedCards().remove(source.getCard());
			}
		}
		
		
		
//HOLD		
//		if(!cardPanel.getClicked()) {
//			if(this.cardPanel.getOwner() != null) {
//				this.cardPanel.getOwner().addSelectedCard(this.cardPanel);
//			}
//			this.cardPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW,4));
//			this.cardPanel.setClicked(true);
//		}else {
//			this.cardPanel.setBorder(null);
//			this.cardPanel.setClicked(false);
//			if(this.cardPanel.getOwner() != null)
//				this.cardPanel.getOwner().removeCard(this.cardPanel, e);
//		}
		
	}


	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent e) {
		this.cardPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW,4));
	}


	public void mouseExited(MouseEvent e) {
		if(!this.cardPanel.getClicked())
			this.cardPanel.setBorder(null);
	}


	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
