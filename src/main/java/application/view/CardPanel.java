package application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class CardPanel extends JPanel implements MouseInputListener{
	private static final long serialVersionUID = 5369052523336312366L;
	private boolean clicked = false;
	private JLabel numberL = null;
	private JLabel numberR = null;
	private JLabel suit = null;
	
	
	public CardPanel(Character number, Character suit) {
		setBackground(Color.WHITE);
		this.numberL = new JLabel();
		this.numberR = new JLabel();
		this.suit = new JLabel();
		this.setLayout(new BorderLayout());
		
		this.numberL.setText(number.toString());
		this.numberR.setText(number.toString());
		this.suit.setText("				"+suit.toString());
		this.add(this.numberL, BorderLayout.WEST);
		this.add(this.numberR, BorderLayout.EAST);
		this.add(this.suit, BorderLayout.CENTER);
		
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
