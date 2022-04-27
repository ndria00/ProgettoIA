package application.view;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import application.CardToImage;
import application.model.Card;
import application.model.Game;

public class BotCardsPanel extends JPanel{
	private static final long serialVersionUID = 2239958166674050082L;
	
	
	public BotCardsPanel() {
		this.update();
	}
	
	
	public void update() {
		this.removeAll();
		System.out.println(Game.getInstance().getPlayers().get(1).getCards().size());
		for(Card card : Game.getInstance().getPlayers().get(1).getCards()) {
			ImageIcon image = new ImageIcon();
			//image.setImage(ImageIO.read(getClass().getResource("../resources/images/" + '9' + '9' + ".png")));
			Image bimage = CardToImage.getInstance().getImageFromCard(card).getImage();
			image.setImage((BufferedImage) bimage);
			
			CardPanel cardPanel = new CardPanel(image, PlayerCardsPanel.getInstance());
			cardPanel.setCard(card);
			this.setLayout(new FlowLayout());
			this.add(cardPanel);
		}

	}
	

}
