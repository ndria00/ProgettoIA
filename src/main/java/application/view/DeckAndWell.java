package application.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DeckAndWell extends JPanel{
	private static final long serialVersionUID = -1504887529992253680L;
	
	private CardPanel[] panels = new CardPanel[2];
	
	public DeckAndWell(ImageIcon backImage, ImageIcon frontImage) {
		panels[0] = new WellPanel(frontImage);
		panels[1] = new DeckPanel(backImage);
		this.setLayout(new FlowLayout());
		this.add(panels[0]);
		this.add(panels[1]);
		//this.setMaximumSize(new Dimension(90,90));
		this.setBackground(new Color(185, 251, 192));
	}

}
