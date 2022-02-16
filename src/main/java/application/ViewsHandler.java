package application;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class ViewsHandler {
	private JPanel mainPanel = null;
	private CardLayout cardLayout = null;
	private static ViewsHandler instance = null;
	
	protected ViewsHandler() {
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
	}
	
	
	public static ViewsHandler getInstance() {
		if(instance == null)
			instance = new ViewsHandler();
		return instance;
	}
	
	
	public void getView(String viewName) {
		cardLayout.show(mainPanel, viewName);
	}
	
	public void addView(String viewName, JPanel panel) {
		mainPanel.add(panel, viewName);
	}
	
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
}
