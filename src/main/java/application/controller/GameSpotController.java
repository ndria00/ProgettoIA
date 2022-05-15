package application.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.event.MouseInputListener;

import application.model.Card;
import application.model.Game;
import application.model.PlayerOpenedState;
import application.view.GameSpot;
import application.view.PlayView;
import application.view.PlayerCardsPanel;

public class GameSpotController implements MouseInputListener{
	
	GameSpot gameSpotPanel = null;
	
	
	public GameSpotController(GameSpot gs) {
		this.gameSpotPanel = gs;
	}
	
	public void mouseClicked(MouseEvent e) {
		
		if (Game.getInstance().getRealPlayer().getSelectedCards().size() > 0 && Game.getInstance().getRealPlayer().hasPicked() && Game.getInstance().getRealPlayer().isPlayingRound()) {
					
					if(Game.getInstance().getRealPlayer().getState().getClass() == PlayerOpenedState.class) {
						System.out.println("TRIED TO ADD A CARD TO A GAME SPOT");
						if(gameSpotPanel.getCurrentPlay() == null)
							return;
						System.out.println("ADDING CARD TO GAME SPOT");
						for(Card c: Game.getInstance().getRealPlayer().getSelectedCards()) {
							if(this.gameSpotPanel.getCurrentPlay().canAttach(c)) {
								System.out.println("ADDED CARD TO GAME SPOT: " + c.toString());
								this.gameSpotPanel.getCurrentPlay().attach(c);
								Game.getInstance().getRealPlayer().getCards().remove(c);
							}
						}
						PlayView.getInstance().updateGameSpots();
					}
					else {
					//1. Chiama ASP Manager per provare a giocare le carte selezionate
					//Call ASP giving it selected cards 
					Game.getInstance().getRealPlayer().play(Game.getInstance().getPlays());
					
					//Aggiorno le carte del player reale
					//System.out.println("Try to Play");
					PlayerCardsPanel.getInstance().update();
					PlayView.getInstance().updateGameSpots();
					
					
					//1. Get possible configuration as array lists
					//2. Control if the total value is grater than 40
						//2.1 put the first array in the selected game spot
						//2.2 if there is more than one  array list 
							// 2.2.1 for each array put it in the first available game spot 
					}
		}
//		else if (Game.getInstance().getRealPlayer().getSelectedCards().size() > 0 ){//&& Game.getInstance().getRealPlayer().getState().getClass() == PlayerOpenedState.class ) {
//			
//			//adding all the selected cards to the game spot
//			gameSpotPanel.placeCards(Game.getInstance().getRealPlayer().getSelectedCards());
//			
//			
//			//Removing all the selected cards from the real player model 
//			for(Card c : gameSpotPanel.getModelCards()) {
//				Game.getInstance().getRealPlayer().getSelectedCards().remove(c);
//				Game.getInstance().getRealPlayer().getCards().remove(c);
//			}
//			
//			
//			//Removing all the selected cards from the player cards panel
//			PlayerCardsPanel.getInstance().update();
//			
//			//gameSpotPanel.owner.removeCards();
//			gameSpotPanel.revalidate();
//			
//			
//			//NEW 
//			//1. Get possible configuration as array lists
//			//2. put the first array in the selected game spot
//			//3. if there is more than one  array list 
//				// 3.1 for each array put it in the first available game spot 
//			
//		} 
		
	}
	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		gameSpotPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		
	}

	public void mouseExited(MouseEvent e) {
		gameSpotPanel.setBorder(BorderFactory.createLineBorder(Color.RED,1));
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
