package application.view;



import javax.swing.ImageIcon;

import application.CardToImage;
import application.Settings;
import application.controller.WellPanelController;
import application.model.Game;


public class WellPanel extends CardPanel{

	private static final long serialVersionUID = 2786141514094370526L;
	


	public WellPanel(ImageIcon image) {
		super(image,Settings.NO_BORDER);
		this.removeMouseListener(this.getMouseListeners()[0]);
		this.addMouseListener(new WellPanelController());
	}
	
	public WellPanel(ImageIcon image, PlayerCardsPanel playCards) {
		super(image, playCards,Settings.NO_BORDER);
		this.removeMouseListener(this.getMouseListeners()[0]);
		this.addMouseListener(new WellPanelController());
	}
	
	public void updateWellPanel() {
//		WellPanel wellPanel = new WellPanel(CardToImage.getInstance().getImageFromCard(Game.getInstance().getWell().lastElement()));
//		PlayView.getInstance().getDeckAndWellPanel().setWellPanel(wellPanel);
		
		
		System.out.println("Card on top of well 1: " + Game.getInstance().getWell().lastElement());
		this.setImage(CardToImage.getInstance().getImageFromCard(Game.getInstance().getWell().lastElement()));
		System.out.println("Card on top of well 2: " + CardToImage.getInstance().getImageFromCard(Game.getInstance().getWell().lastElement()));
		this.setCard(Game.getInstance().getWell().lastElement());
		this.invalidate();
		this.revalidate();
		this.repaint();
		System.out.println("END WELL UPDATE");
	}
	

//	@Override
//	public void mouseClicked(MouseEvent e) {
//		if(PlayerCardsPanel.getInstance().selectedCards.size() == 1) {
//			
//			//SCARTO 
//			
//			//Game.getInstance().getWell().put(this.getCard());
//			CardPanel c = PlayerCardsPanel.getInstance().selectedCards.remove(0);
//			PlayerCardsPanel.getInstance().remove(c);
//			Game.getInstance().getPlayers().get(0).discard(this.getCard());
//			
//			super.setImage(c.getImage());
//			
//			PlayerCardsPanel.getInstance().revalidate();
//		}
//		else if(PlayerCardsPanel.getInstance().selectedCards.size() == 0) {
//			
//			//PESCA DAL POZZO
//			
//		}else {System.out.println(PlayerCardsPanel.getInstance().selectedCards.size());}
//	}
	
}
