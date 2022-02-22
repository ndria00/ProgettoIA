package application.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class CardPanel extends JPanel implements MouseInputListener{
	private static final long serialVersionUID = 5369052523336312366L;
	private boolean clicked = false;
	
	public CardPanel() {
		setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(60,120));
		this.setMaximumSize(getPreferredSize());
		this.addMouseListener(this);
	}


	public void mouseClicked(MouseEvent e) {
		if(!clicked) {
			this.setBorder(BorderFactory.createLineBorder(Color.YELLOW,4));
			clicked = true;
		}else {
			this.setBorder(null);
			clicked = false;
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


	

}
