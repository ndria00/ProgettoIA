package application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import application.Settings;
import application.ViewsHandler;

import java.util.ArrayList;
import java.util.List;

public class PlayView extends JPanel{
	private static final long serialVersionUID = -8129006343701355252L;
	private JButton leaveButton = null;

	public PlayView() {
		this.setBackground(new Color(185, 251, 192));
		leaveButton = new JButton("leave the game");
		initButton();
		setPageLayout();		
	}
	
	public void showMyCards(List<CardPanel> cards) {
		for (CardPanel cardPanel : cards) {
			this.add(cardPanel);
		}
	}
	
	public void initButton() {
		leaveButton.setSize(100, 50);
		leaveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ViewsHandler.getInstance().getView("home");
			}
		});
	}
	public void setPageLayout() {
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		this.add(leaveButton, BorderLayout.NORTH);
		
		//bottom
		JPanel bottom = new JPanel();
		GridLayout gridLayout = new GridLayout(1,13,10,10);
		bottom.setLayout(gridLayout);
		bottom.setBackground(new Color(185, 251, 192));
		this.add(bottom, BorderLayout.SOUTH);
		CardPanel p1 = new CardPanel('2', Settings.SUITE_SPADES);
		CardPanel p2 = new CardPanel('2', Settings.SUITE_SPADES);
		CardPanel p3 = new CardPanel('2', Settings.SUITE_SPADES);
		CardPanel p4 = new CardPanel('2', Settings.SUITE_SPADES);
		CardPanel p5 = new CardPanel('2',Settings.SUITE_SPADES);
		CardPanel p6 = new CardPanel('2',Settings.SUITE_SPADES);
		CardPanel p7 = new CardPanel('2',Settings.SUITE_SPADES);
		CardPanel p8 = new CardPanel('2',Settings.SUITE_SPADES);
		CardPanel p9 = new CardPanel('2',Settings.SUITE_SPADES);
		CardPanel p10 = new CardPanel('2',Settings.SUITE_SPADES);
		CardPanel p11 = new CardPanel('2',Settings.SUITE_SPADES);
		CardPanel p12 = new CardPanel('2',Settings.SUITE_SPADES);
		CardPanel p13 = new CardPanel('2',Settings.SUITE_SPADES);
		
		bottom.add(p1);
		bottom.add(p2);
		bottom.add(p3);
		bottom.add(p4);
		bottom.add(p5);
		bottom.add(p6);
		bottom.add(p7);
		bottom.add(p8);
		bottom.add(p9);
		bottom.add(p10);
		bottom.add(p11);
		bottom.add(p12);
		bottom.add(p13);
		
		//center
		JPanel center = new JPanel();
		GridLayout centerGridLayout = new GridLayout(3,1);
		center.setLayout(centerGridLayout);
		center.setBackground(new Color(185, 251, 192));
		
		JPanel gameSpots = new JPanel();
		GridLayout gameSpotsGridLayout = new GridLayout(4,5,4,4);
		gameSpots.setLayout(gameSpotsGridLayout);
		gameSpots.setBackground(new Color(185, 251, 192));
		
		//TEST
		for(int i = 0; i < 20; ++i) {
			GameSpot gs = new GameSpot();
			CardPanel c1 = new CardPanel('2',Settings.SUITE_SPADES);
			c1.setBackground(Color.red);
			CardPanel c2 = new CardPanel('2',Settings.SUITE_SPADES);
			c2.setBackground(Color.BLUE);
			CardPanel c3 = new CardPanel('2',Settings.SUITE_SPADES);
			c3.setBackground(Color.cyan);
			CardPanel c4 = new CardPanel('2',Settings.SUITE_SPADES);
			c4.setBackground(Color.MAGENTA);
			
			ArrayList<CardPanel> cards = new ArrayList<CardPanel>();
			cards.add(c1);
			cards.add(c2);
			cards.add(c3);
			cards.add(c4);
			
			gs.placeCards(cards);
			gameSpots.add(gs);
		}
		//END TEST
		
		JPanel deckAndWell = new JPanel();
		deckAndWell.add(new CardPanel('2', Settings.SUITE_SPADES));
		deckAndWell.add(new CardPanel('2', Settings.SUITE_SPADES));
		deckAndWell.setMaximumSize(new Dimension(110,120));
		deckAndWell.setBackground(new Color(185, 251, 192));
		deckAndWell.setLayout(new FlowLayout());
		
		center.add(deckAndWell);
		center.add(gameSpots); 
		this.add(center,BorderLayout.CENTER);
	}
}
