package application.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.event.MouseInputListener;

import application.CardToImage;
import application.model.Card;
import application.model.Game;
import application.view.CardPanel;
import application.view.DeckPanel;
import application.view.PlayerCardsPanel;

public class DeckController implements MouseInputListener{
	
	private DeckPanel deckPanel = null;
	
	public DeckController(DeckPanel d) {
		this.deckPanel = d;
	}
	
	public void mouseClicked(MouseEvent e) {
		if(!Game.getInstance().getRealPlayer().isPlayingRound()) {
			System.out.println("Player tried to pick but is not playing");
			//give error message
			return;
		}else if(Game.getInstance().getRealPlayer().hasPicked()) {
			System.out.println("Player tried to pick but has already picked");
			//give error message
			return;
		}
		Card c = Game.getInstance().getDeck().pick();
		Game.getInstance().getRealPlayer().pickedCard(c);
		//Deck is empty and must be refilled with cards from the well
		if(Game.getInstance().getDeck().size() == 0) {
			Game.getInstance().getDeck().setCards(Game.getInstance().getWell().shuffleNewDeck());
			//NO NEED TO REVALIDATE? MAYBE
		}
		
		//System.out.print(c.getSuite() + " " + c.getNumber() + "\n");
		ImageIcon image = new ImageIcon();
		image = CardToImage.getInstance().getImageFromCard(c);
		CardPanel cardPanel = new CardPanel(image, PlayerCardsPanel.getInstance());
		cardPanel.setCard(c);
		PlayerCardsPanel.getInstance().add(cardPanel);
		PlayerCardsPanel.getInstance().revalidate();
	}
	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent e) {
		this.deckPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW,4));
	}


	public void mouseExited(MouseEvent e) {
		if(!this.deckPanel.getClicked())
			this.deckPanel.setBorder(null);
	}


	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
