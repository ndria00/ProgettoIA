package application.view;

import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import application.CardToImage;
import application.Settings;
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
			image.setImage(bimage);
			
			CardPanel cardPanel = new CardPanel(image,Settings.NO_BORDER);
			cardPanel.setCard(card);
			this.add(cardPanel);
		}
		this.setLayout(new FlowLayout());
		this.revalidate();
	}
	

}
