package application.view;

import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import application.model.Card;
import application.model.Game;

public class DeckPanel extends CardPanel{
	
	
	

	private static final long serialVersionUID = 3154124699346921639L;
	public DeckPanel(ImageIcon image) {
		super(image);
		// TODO Auto-generated constructor stub
	}
	
	public DeckPanel(ImageIcon image, PlayerCardsPanel playerCards) {
		super(image, playerCards);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Card c = Game.getInstance().getDeck().pick();
		//System.out.print(c.getSuite() + " " + c.getNumber() + "\n");
		ImageIcon image = new ImageIcon();
		try {
			image.setImage(ImageIO.read(getClass().getResource("../resources/" + c.getSuite() + c.getNumber() + ".png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CardPanel card = new CardPanel(image, PlayerCardsPanel.getInstance());
		card.setCard(c);
		PlayerCardsPanel.getInstance().add(card);
		PlayerCardsPanel.getInstance().revalidate();
	}
}
