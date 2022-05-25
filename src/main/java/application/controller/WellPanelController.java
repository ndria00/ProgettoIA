package application.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.event.MouseInputListener;

import application.model.Card;
import application.model.Game;
import application.model.PlayerNotOpenedState;
import application.view.PlayView;
import application.view.PlayerCardsPanel;

public class WellPanelController implements MouseInputListener{
	
	
	public WellPanelController() {
	}
	
	
	public void mouseClicked(MouseEvent e) {
		System.out.println("Player has selected " + Game.getInstance().getRealPlayer().getSelectedCards().size()+ " cards");
		if(Game.getInstance().getRealPlayer().getSelectedCards().size() == 1) {
			
			//SCARTO 
			System.out.println("CLICKED");
			if(!Game.getInstance().getRealPlayer().hasPicked()) {
				System.out.println("YOU MUST PICK BEFORE DISCARDING");
				//give a visual error message
				return;
			}
			
			//il realPlayer scarta la carta e conclude il turno
			//System.out.println(PlayView.getInstance().getDeckAndWellPanel().getWellPanel());
			Card c = Game.getInstance().getRealPlayer().getSelectedCards().get(0);
			//CardPanel cardPanel = PlayerCardsPanel.getInstance().getCardPanelFromCard(c);
			int actualGameRoundNumber = Game.getInstance().getGameRoundNumber();
			Game.getInstance().playerDiscard(Game.getInstance().getRealPlayer(), c);
			//PlayerCardsPanel.getInstance().update();
			
			//rimuovo il componente CardPanel dalle carte del realPlayer
			//PlayerCardsPanel.getInstance().remove(cardPanel);
			
			
			//assegno al well panel l'immagine della carta appena scartata
			//System.out.println(cardPanel.getImage());
			//PlayView.getInstance().getDeckAndWellPanel().getWellPanel().setImage(cardPanel.getImage());
			System.out.println("Card on top of well: " + Game.getInstance().getWell().lastElement() + "Card discarded: " + c);
//			PlayerCardsPanel.getInstance().update();
//			PlayView.getInstance().getDeckAndWellPanel().getWellPanel().updateWellPanel();
			//PlayView.getInstance().getDeckAndWellPanel().update();
			//Aggiorno il panel che continene le carte del real player
			System.out.println("WAITING");
			
			//CyclicBarrier barrier = new CyclicBarrier(2);
			PlayerCardsPanel.getInstance().update();
			PlayView.getInstance().getDeckAndWellPanel().getWellPanel().updateWellPanel();
			
			Runnable r = new Runnable() {
				public void run() {
					System.out.println("Player : " + Game.getInstance().getPlayingPlayer().getClass());
					Game.getInstance().getPlayingPlayer().play(Game.getInstance().getPlays());
//				try {
//					barrier.await();
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (BrokenBarrierException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				}
			};
			if(actualGameRoundNumber == Game.getInstance().getGameRoundNumber()) {
				Thread t = new Thread(r);
				ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//				scheduledExecutorService.scheduleWithFixedDelay(t, 10, 0, TimeUnit.SECONDS);
				scheduledExecutorService.schedule(t, 2, TimeUnit.SECONDS);
			}
//			try {
//				barrier.await();
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (BrokenBarrierException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			//System.out.println("ciao");
			//PlayView.getInstance().getDeckAndWellPanel().getWellPanel().revalidate();
			//update View
			//PlayView.getInstance().updateGameSpots();
		}
		else if(Game.getInstance().getRealPlayer().getSelectedCards().size() == 0) {
			if(!Game.getInstance().getRealPlayer().isPlayingRound()) { //player is trying to pick but the bot didn't finish his round
                //give error message
                return;
            }else if(Game.getInstance().getRealPlayer().hasPicked()) { //player has already picked
                //give error message
                return;
            }else if(Game.getInstance().getRealPlayer().getState().getClass() == PlayerNotOpenedState.class) {
                System.out.println("Tried to pick but not opened");
                return;
            }
            
            //Se ci sono carte nel well
            if(Game.getInstance().getWell().size() >= 1) {
                //prelevo la prima carta dal well model
                Card card = Game.getInstance().getWell().pick();
                
                //Aggiungo la carta alle carte del realPlayer
                Game.getInstance().getRealPlayer().pickedCard(card);
                //System.out.println(card);
                
                //creo il cardPanel da aggiungere ai cardPanel del realPlayer
                //CardPanel cp = new CardPanel(CardToImage.getInstance().getImageFromCard(card), PlayerCardsPanel.getInstance(),Settings.NO_BORDER);
                //cp.setCard(card);
                //CardPanel cp = new CardPanel(null, PlayerCardsPanel.getInstance());
                
                //aggiungo il cardPanel alla view
                //PlayerCardsPanel.getInstance().add(cp);
                
                //prendo la carta sotto quella prelevata
                //Card nextCard = null;
                //if(Game.getInstance().getWell().size() != 0) {
                //    nextCard = Game.getInstance().getWell().lastElement();
                //}
                //Se la nextCard è nulla vuol dire che non ci sono più carte nel well
                //if(nextCard != null) {
                    //assegno nextCard al well
                //    PlayView.getInstance().getDeckAndWellPanel().getWellPanel().setImage(CardToImage.getInstance().getImageFromCard(nextCard));
                //}else {
                //    System.out.println("CARTE FINITE");
                //    PlayView.getInstance().getDeckAndWellPanel().getWellPanel().setImage(null);
                //}
                                    
                //rivalido i panel
                PlayView.getInstance().getDeckAndWellPanel().getWellPanel().updateWellPanel();
                PlayerCardsPanel.getInstance().update();
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
		PlayView.getInstance().getDeckAndWellPanel().getWellPanel().setBorder(BorderFactory.createLineBorder(Color.YELLOW,4));
	}


	public void mouseExited(MouseEvent e) {
		if(!PlayView.getInstance().getDeckAndWellPanel().getWellPanel().getClicked())
			PlayView.getInstance().getDeckAndWellPanel().getWellPanel().setBorder(null);
	}


	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
