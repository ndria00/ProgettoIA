package application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import application.ViewsHandler;

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
		JPanel bottom = new JPanel();
		GridLayout gridLayout = new GridLayout(1,13,10,10);
		bottom.setLayout(gridLayout);
		bottom.setBackground(new Color(185, 251, 192));
		this.add(bottom, BorderLayout.SOUTH);
		CardPanel p1 = new CardPanel();
		CardPanel p2 = new CardPanel();
		CardPanel p3 = new CardPanel();
		CardPanel p4 = new CardPanel();
		CardPanel p5 = new CardPanel();
		CardPanel p6 = new CardPanel();
		CardPanel p7 = new CardPanel();
		CardPanel p8 = new CardPanel();
		CardPanel p9 = new CardPanel();
		CardPanel p10 = new CardPanel();
		CardPanel p11 = new CardPanel();
		CardPanel p12 = new CardPanel();
		CardPanel p13 = new CardPanel();
		
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
	}
}
