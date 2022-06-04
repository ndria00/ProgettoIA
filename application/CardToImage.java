package application;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		String id = null;

		BufferedReader reader =  new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("images")));
		try {

			while(reader.ready()) {
				ImageIcon image = new ImageIcon();
				String line = reader.readLine();
				//System.out.println("READING FROM FOLDER " + line);
				image.setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/"+line)).getScaledInstance(65, 90, Image.SCALE_SMOOTH));
				id =  line.split(".png")[0];
				this.cardsImages.put(id, image);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		for (File file : folder.listFiles()) {
			ImageIcon image = new ImageIcon();
			try {
				image.setImage(ImageIO.read(getClass().getResourceAsStream("./resources/images/"+file.getName())).getScaledInstance(65, 90, Image.SCALE_SMOOTH));
				id =  file.getName().split(".png")[0];
				this.cardsImages.put(id, image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
	
	
	public ImageIcon getImageFromCard(Card c) {
		String search = Integer.toString(c.getSuite()) + Integer.toString(c.getNumber());
		ImageIcon returned =  cardsImages.get(search);
//		Image img = returned.getImage().getScaledInstance(65, 90, Image.SCALE_SMOOTH);
//		returned.setImage(img);
		return returned;
	}
	
	public ImageIcon getBackImage() {
		ImageIcon returned =  cardsImages.get("99");
//		Image img = returned.getImage().getScaledInstance(65, 90, Image.SCALE_SMOOTH);
//		returned.setImage(img);
		return returned;
	}
	
}
