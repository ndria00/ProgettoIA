package application.view;

import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import application.model.Card;
import application.model.Game;

public class WellPanel extends CardPanel{

	private static final long serialVersionUID = 2786141514094370526L;

	public WellPanel(ImageIcon image) {
		super(image);
		// TODO Auto-generated constructor stub
	}
	
	public WellPanel(ImageIcon image, PlayerCardsPanel playCards) {
		super(image, playCards);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(PlayerCardsPanel.getInstance().selectedCards.size() == 1) {
			
			//SCARTO 
			
			//Game.getInstance().getWell().put(this.getCard());
			CardPanel c = PlayerCardsPanel.getInstance().selectedCards.remove(0);
			PlayerCardsPanel.getInstance().remove(c);
			Game.getInstance().getPlayers().get(0).discard(this.getCard());
			
			super.setImage(c.getImage());
			
			PlayerCardsPanel.getInstance().revalidate();
		}
		else if(PlayerCardsPanel.getInstance().selectedCards.size() == 0) {
			
			//PESCA DAL POZZO
			
		}else {System.out.println(PlayerCardsPanel.getInstance().selectedCards.size());}
	}
	
}
