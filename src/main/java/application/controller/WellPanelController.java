package application.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.event.MouseInputListener;

import application.CardToImage;
import application.model.Card;
import application.model.Game;
import application.view.CardPanel;
import application.view.PlayerCardsPanel;
import application.view.WellPanel;

public class WellPanelController implements MouseInputListener{
	
	private WellPanel wellPanel = null;
	
	public WellPanelController(WellPanel w) {
		this.wellPanel = w;
	}
	
	
	public void mouseClicked(MouseEvent e) {
		if(Game.getInstance().getRealPlayer().getSelectedCards().size() == 1) {
			
			//SCARTO 
			System.out.println("CLICKED");
			//il realPlayer scarta la carta e conclude il turno
			System.out.println(this.wellPanel);
			Card c = Game.getInstance().getRealPlayer().getSelectedCards().remove(0);
			CardPanel cardPanel = PlayerCardsPanel.getInstance().getCardPanelFromCard(c);
			Game.getInstance().getPlayers().get(0).removeCard(c);
			
			
		
			//aggiungo la carta al model well
			//Game.getInstance().getWell().put(c.getCard());
			
			//rimuovo il componente CardPanel dalle carte del realPlayer
			PlayerCardsPanel.getInstance().remove(cardPanel);
			
			
			//assegno al well panel l'immagine della carta appena scartata
			System.out.println(cardPanel.getImage());
			this.wellPanel.setImage(cardPanel.getImage());
			
			//Aggiorno il panel che continene le carte del real player
			PlayerCardsPanel.getInstance().update();
			this.wellPanel.revalidate();
			//update View
		}
		else if(Game.getInstance().getRealPlayer().getSelectedCards().size() == 0) {
			
			
//				if(!Game.getInstance().getRealPlayer().isPlayingRound()) {
//					return;
//				}
				
				//Se ci sono carte nel well
				if(Game.getInstance().getWell().size() != 0) {
					//prelevo la prima carta dal well model
					Card card = Game.getInstance().getWell().pick();
					
					//Aggiungo la carta alle carte del realPlayer
					Game.getInstance().getRealPlayer().getCards().add(card);
					//System.out.println(card);
					
					//creo il cardPanel da aggiungere ai cardPanel del realPlayer
					CardPanel cp = new CardPanel(CardToImage.getInstance().getImageFromCard(card), PlayerCardsPanel.getInstance());
					cp.setCard(card);
					//CardPanel cp = new CardPanel(null, PlayerCardsPanel.getInstance());
					
					//aggiungo il cardPanel alla view
					PlayerCardsPanel.getInstance().add(cp);
					
					//prendo la carta sotto quella prelevata
					Card nextCard = null;
					if(Game.getInstance().getWell().size() != 0) {
						nextCard = Game.getInstance().getWell().lastElement();
					}
					//Se la nextCard è nulla vuol dire che non ci sono più carte nel well
					if(nextCard != null) {
						//assegno nextCard al well 
						this.wellPanel.setImage(CardToImage.getInstance().getImageFromCard(nextCard));
					}else {
						System.out.println("CARTE FINITE");
						this.wellPanel.setImage(null);
					}
										
					//rivalido i panel
					this.wellPanel.revalidate();
					PlayerCardsPanel.getInstance().revalidate();
				}else {
					System.out.println("NON CI SONO CARTE NEL WELL");
				}
			
		}
	}
	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent e) {
		this.wellPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW,4));
	}


	public void mouseExited(MouseEvent e) {
		if(!this.wellPanel.getClicked())
			this.wellPanel.setBorder(null);
	}


	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
