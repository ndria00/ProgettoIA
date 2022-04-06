package application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import application.model.Card;

public class CardPanel extends JPanel implements MouseInputListener{
	private static final long serialVersionUID = 5369052523336312366L;
	private Integer id = null;
	private boolean clicked = false;
	private ImageIcon image = null;
	private JLabel label = null;
	private PlayerCardsPanel owner = null;
	private Card card = null;
	
	
	public CardPanel(ImageIcon image) {
		setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		//this.image = image;
		Image img = image.getImage();
		img = img.getScaledInstance(65, 90,  Image.SCALE_SMOOTH);
		this.image = new ImageIcon(img);
		this.label = new JLabel(this.image);
		
		this.setBackground(new Color(185, 251, 192));
		this.add(this.label, BorderLayout.CENTER);
		this.addMouseListener(this);
	}
	
	public CardPanel(ImageIcon image, PlayerCardsPanel owner) {
		setBackground(Color.WHITE);
		//this.setLayout(new BorderLayout());
		Image img = image.getImage();
		img = img.getScaledInstance(65, 90,  Image.SCALE_SMOOTH);
		this.image = new ImageIcon(img);
		this.label = new JLabel(this.image);
		this.owner = owner;
		this.setBackground(new Color(185, 251, 192));
		this.add(this.label, BorderLayout.CENTER);
		this.addMouseListener(this);
	}
	
	public void setImage(ImageIcon image) {
//		this.remove(this.label);
//		this.label = new JLabel(image);
//		this.add(label);
		
		this.label.setIcon(image);
//		this.repaint();
//		this.revalidate();
		System.out.println("DENTRO");
	}
	
	public ImageIcon getImage() {
		return this.image;
	}
	
	
	public Card getCard() {
		return this.card;
	}
	
	public void setCard(Card c) {
		this.card = c;
	}


	



	public void mouseClicked(MouseEvent e) {
		if(!clicked) {
			if(this.owner != null) {
				owner.addSelectedCard(this);
			}
			this.setBorder(BorderFactory.createLineBorder(Color.YELLOW,4));
			clicked = true;
		}else {
			this.setBorder(null);
			clicked = false;
			if(owner != null)
				this.owner.removeCard(this, e);
		}
		
	}


	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent e) {
		this.setBorder(BorderFactory.createLineBorder(Color.YELLOW,4));
	}


	public void mouseExited(MouseEvent e) {
		if(!clicked)
			this.setBorder(null);
	}


	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public boolean getClicked() {
		return this.clicked;
	}

}
