package application.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import application.ViewsHandler;

public class HomeView extends JPanel{
	private static final long serialVersionUID = -1748388828014345492L;
	
	private JButton playButton = null;
	
	public HomeView() {
		playButton = new JButton("Play");
		initPlayButton(playButton);
		this.setLayout(new GridLayout(3, 1));
		this.add(playButton);
	}
	private void initPlayButton(JButton button) {
		ActionListener listener = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ViewsHandler.getInstance().getView("play");
			}
		};
		button.addActionListener(listener);
	}

}
