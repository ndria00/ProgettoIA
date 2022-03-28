package application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class CardPanel extends JPanel implements MouseInputListener{
	private static final long serialVersionUID = 5369052523336312366L;
	private Integer id = null;
	private boolean clicked = false;
	private ImageIcon image = null;
	private JLabel label = null;
	private PlayerCards owner = null;
	
	
	public CardPanel(ImageIcon image) {
		setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.image = image;
		this.label = new JLabel(this.image);
		
		this.setBackground(new Color(185, 251, 192));
		this.add(this.label, BorderLayout.CENTER);
		this.addMouseListener(this);
	}
	
	public CardPanel(ImageIcon image, PlayerCards owner) {
		setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.image = image;
		this.label = new JLabel(this.image);
		this.owner = owner;
		this.setBackground(new Color(185, 251, 192));
		this.add(this.label, BorderLayout.CENTER);
		this.addMouseListener(this);
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
			if(this.owner != null)
				owner.removeCard(this);
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
