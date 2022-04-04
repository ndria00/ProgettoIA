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
	
	public WellPanel(ImageIcon image, PlayerCards playCards) {
		super(image, playCards);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(PlayerCards.getInstance().selectedCards.size() == 1) {
			//Game.getInstance().getWell().add(new Card());
			CardPanel c = PlayerCards.getInstance().selectedCards.remove(0);
			PlayerCards.getInstance().remove(c);
			
			super.setImage(c.getImage());
			
			PlayerCards.getInstance().revalidate();
		}
		else if(PlayerCards.getInstance().selectedCards.size() == 0) {
			
		}
	}
	
}
