package application.view;

import javax.swing.JButton;

import application.controller.AddSetButtonController;

public class AddSetButton extends JButton{

	private static final long serialVersionUID = 5203903846394690367L;
	
	public AddSetButton() {
		super("Aggiungi combinazione +");
		this.setSize(200, 50);
		this.addActionListener(new AddSetButtonController());
	}
	

}
