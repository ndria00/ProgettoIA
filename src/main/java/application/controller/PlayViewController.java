package application.controller;


import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.event.MouseInputListener;

import application.CardToImage;
import application.model.Card;
import application.model.Game;
import application.model.Play;
import application.view.CardPanel;
import application.view.GameSpot;
import application.view.GameSpotsPanel;
import application.view.PlayView;
import application.view.PlayerCardsPanel;

public class PlayViewController implements MouseInputListener{
	
	
	
	PlayView playView = null;
	GameSpotsPanel gameSpotsPanel = null;
	
	
	
	public PlayViewController(PlayView playView, GameSpotsPanel gameSpotPanel) {
		this.playView = playView;
	}
	
	public void setGameSpotsPanel(GameSpotsPanel gameSpotsPanel) {
		this.gameSpotsPanel = gameSpotsPanel;
	}
	
	
	
	public void updateGameSpots() {
		if(this.gameSpotsPanel != null) {
			for(Play play : Game.getInstance().getPlays()) {
				for(GameSpot gs : this.gameSpotsPanel.getGameSpots()) {
					gs.removeAll();
					for(Card card : play) {
						ImageIcon image = new ImageIcon();
						image.setImage(CardToImage.getInstance().getImageFromCard(card).getImage());
						CardPanel cardpanel = new CardPanel(image, PlayerCardsPanel.getInstance());
						cardpanel.setCard(card);
						gs.add(cardpanel);
					}
				}
			}
		}
		System.out.println("UPDATED");
	}
	

	public void mouseClicked(MouseEvent e) {
		updateGameSpots();
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
