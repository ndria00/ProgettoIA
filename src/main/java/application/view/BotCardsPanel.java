package application.view;

import java.awt.FlowLayout;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

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
			try {
				image.setImage(ImageIO.read(getClass().getResource("../resources/images/" + '9' + '9' + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CardPanel cardPanel = new CardPanel(image, PlayerCardsPanel.getInstance());
			cardPanel.setCard(card);
			this.setLayout(new FlowLayout());
			this.add(cardPanel);
		}

	}
	

}
