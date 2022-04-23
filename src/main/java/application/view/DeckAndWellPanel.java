package application.view;


import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DeckAndWellPanel extends JPanel{
	private static final long serialVersionUID = -1504887529992253680L;
	
	private WellPanel wellPanel;
	private DeckPanel deckPanel;
	
	public DeckAndWellPanel(ImageIcon backImage, ImageIcon frontImage) {
		wellPanel = new WellPanel(frontImage);
		deckPanel = new DeckPanel(backImage);
		this.setLayout(new FlowLayout());
		this.add(wellPanel);
		this.add(deckPanel);
		//this.setMinimumSize(new Dimension(90,90));
		this.setBackground(new Color(185, 251, 192));
	}

	public WellPanel getWellPanel() {
		return wellPanel;
	}

	public void setWellPanel(WellPanel wellPanel) {
		this.wellPanel = wellPanel;
	}

	public DeckPanel getDeckPanel() {
		return deckPanel;
	}

	public void setDeckPanel(DeckPanel deckPanel) {
		this.deckPanel = deckPanel;
	}

}
