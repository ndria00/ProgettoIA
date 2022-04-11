package application.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import application.model.Card;
import application.model.Game;
import application.view.GameSpotsPanel;


public class AddSetButtonController implements ActionListener{
	
	private ArrayList<ArrayList<Card>> possibleSets = new ArrayList<ArrayList<Card>>();
	private int index = 0;
	private int totalCardsValue = 0; 
	private int maxSets = GameSpotsPanel.gameSpotsNum;

	public void actionPerformed(ActionEvent e) {
//		JOptionPane.showMessageDialog(null,
//			    "WARNING.",
//			    "Warning",
//			    JOptionPane.WARNING_MESSAGE);
		ArrayList<Card> toAdd = (ArrayList<Card>) Game.getInstance().getRealPlayer().getCopiaSelectedCards();
		if(isValid(toAdd) && possibleSets.size() <= maxSets && toAdd.size() > 0) {
			totalCardsValue += getCardsValue(toAdd);
			possibleSets.add(index, toAdd);
			index++;
		}
		showMessage();
		
	}
	
	private boolean isValid(ArrayList<Card> set) {
		return true;
	}
	
	private void showMessage() {
		StringBuilder sb = new StringBuilder();
		for(ArrayList<Card> set : possibleSets) {
			//System.out.println("SET : " + set + "\n");
			for(Card card : set) {
				sb.append(card.toString() + " , ");
			}
			sb.append("\n");
		}
		JOptionPane.showMessageDialog(null,
		sb.toString(),
	    "Selected Sets:",
	    JOptionPane.WARNING_MESSAGE);

	}
	
	private int getCardsValue(ArrayList<Card> toAdd) {
		int cont = 0;
		for(Card card : toAdd) {
			cont += card.getValue();
		}
		return cont;
	}

}
