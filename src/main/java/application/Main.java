package application;

import javax.swing.JFrame;

import application.view.HomeViewPanel;

public class Main {
	public static void main(String[] args) {
		//add all the pages to the viewsHandler
		ViewsHandler.getInstance().addView("home", new HomeViewPanel());
		
		JFrame frame = new JFrame("Scala 40");
		frame.setSize(1024, 720);
		frame.add(ViewsHandler.getInstance().getMainPanel());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
