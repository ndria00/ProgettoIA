package application.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
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
		if (!Game.getInstance().getRealPlayer().isPlayingRound()) {
			return;
		}
		
		Card c = Game.getInstance().getDeck().pick();
		//System.out.print(c.getSuite() + " " + c.getNumber() + "\n");
		ImageIcon image = new ImageIcon();
//		try {
//			image.setImage(ImageIO.read(getClass().getResource("../resources/images/" + c.getSuite() + c.getNumber() + ".png")));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
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
