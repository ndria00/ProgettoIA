package application.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.event.MouseInputListener;

import application.model.Game;
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
		//CHECK! ASK TO PICK AND REVALIDATE VIEW
		Game.getInstance().playerPick(Game.getInstance().getRealPlayer(), true);
		//Deck is empty and must be refilled with cards from the well
		if(Game.getInstance().getDeck().size() == 0) {
			Game.getInstance().getDeck().setCards(Game.getInstance().getWell().shuffleNewDeck());
			//NO NEED TO REVALIDATE? MAYBE
		}
		
		PlayerCardsPanel.getInstance().update();
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
