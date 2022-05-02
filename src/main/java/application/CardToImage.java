package application;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import application.model.Card;

public class CardToImage {
	
	private HashMap<String, ImageIcon> cardsImages = new HashMap<String, ImageIcon>();
	private static CardToImage instance = null;
	
	private CardToImage() {
		loadCards();
	}


	public static CardToImage getInstance() {
		if(instance == null)
			instance = new CardToImage();
		return instance;
	}
	
	
	private void loadCards() {
		File folder = null;
		String id = null;
		try {
			folder = new File(getClass().getResource("./resources/images").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (File file : folder.listFiles()) {
			ImageIcon image = new ImageIcon();
			try {
				image.setImage(ImageIO.read(getClass().getResourceAsStream("./resources/images/"+file.getName())));
				id =  file.getName().split(".png")[0];
				this.cardsImages.put(id, image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public ImageIcon getImageFromCard(Card c) {
		String search = Integer.toString(c.getSuite()) + Integer.toString(c.getNumber());
		ImageIcon returned =  cardsImages.get(search);
		Image img = returned.getImage().getScaledInstance(65, 90, Image.SCALE_SMOOTH);
		returned = new ImageIcon(img);
		return returned;
	}
	
}
