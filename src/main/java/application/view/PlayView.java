package application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import application.CardToImage;
import application.ViewsHandler;
import application.model.Card;
import application.model.Game;

public class PlayView extends JPanel{
	private static final long serialVersionUID = -8129006343701355252L;
	private JButton leaveButton = null;
	private AddSetButton addSetButton = null;
	private JPanel buttonsBar = null;
	private Vector<CardPanel> cardPanels = new Vector<CardPanel>();

	public PlayView() {
		this.setBackground(new Color(185, 251, 192));
		leaveButton = new JButton("lascia il gioco");
		addSetButton = new AddSetButton();
		buttonsBar = new JPanel();
		buttonsBar.setLayout(new FlowLayout());
		initButtons();
		CardToImage.getInstance();
		setPageLayout();		
	}
	
	public void showMyCards(List<CardPanel> cards) {
		for (CardPanel cardPanel : cards) {
			this.add(cardPanel);
		}
	}
	
	public void initButtons() {
		leaveButton.setSize(100, 50);
		leaveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ViewsHandler.getInstance().getView("home");
			}
		});
		
		buttonsBar.add(leaveButton);
		buttonsBar.add(addSetButton);
		

	}
	
	
	
	
	
	
	public void setPageLayout() {
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		
		this.add(buttonsBar, BorderLayout.NORTH);
		
		//bottom
//		PlayerCards bottom = new PlayerCards();
//		GridLayout gridLayout = new GridLayout(1,13,10,10);
//		bottom.setLayout(gridLayout);
//		bottom.setBackground(new Color(185, 251, 192));
		this.add(PlayerCardsPanel.getInstance(), BorderLayout.SOUTH);
		
		
		//ImageIcon image1 = new ImageIcon();
		//try {
			//image1.setImage(ImageIO.read(new File("/Users/giovannimarasco/git/ProgettoIA/target/classes/application/resources/210.png")));
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		ArrayList<Card> playerCards = Game.getInstance().getPlayers().get(0).getCards();
		for(int i = 0; i < playerCards.size(); i++) {
				ImageIcon image = new ImageIcon();
				//System.out.println(getClass().getResource("../resources/01.png"));
				//Random r = new Random();
				//int seme = r.nextInt(4);
				//int numero = r.nextInt(13);
				//try {
					//image.setImage(ImageIO.read(new File("/Users/giovannimarasco/git/ProgettoIA/target/classes/application/resources/"+seme+numero+".png")));
				//} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				//}
				Card c = playerCards.get(i);
				System.out.println("Sto cercando" + c.getSuite() + " " + c.getNumber());
				image.setImage(CardToImage.getInstance().getImageFromCard(c).getImage());
				CardPanel p1 = new CardPanel(image, PlayerCardsPanel.getInstance());
				p1.setCard(c);
				this.cardPanels.add(p1);
				PlayerCardsPanel.getInstance().add(p1);
		}
		 
		
		
		//center
		JPanel center = new JPanel();
		BorderLayout centerBorderLayout = new BorderLayout();
		center.setLayout(centerBorderLayout);
		center.setBackground(new Color(185, 251, 192));
		
//		JPanel gameSpots = new JPanel();
//		GridLayout gameSpotsGridLayout = new GridLayout(4,4,4,4);
//		gameSpots.setLayout(gameSpotsGridLayout);
//		gameSpots.setBackground(new Color(185, 251, 192));
//		
//		//TEST
//		for(int i = 0; i < 20; ++i) {
//			GameSpot gs = new GameSpot(PlayerCardsPanel.getInstance());
////			CardPanel c1 =new CardPanel(image1);
////			CardPanel c2 = new CardPanel(image1);
////			CardPanel c3 =new CardPanel(image1);
////			CardPanel c4 = new CardPanel(image1);
////			CardPanel c5 =new CardPanel(image1);
////			CardPanel c6 = new CardPanel(image);
////			CardPanel c7 =new CardPanel(image);
////			CardPanel c8 = new CardPanel(image);
////			CardPanel c9 =new CardPanel(image);
////			CardPanel c10 = new CardPanel(image);
////			CardPanel c11 =new CardPanel(image);
////			CardPanel c12 = new CardPanel(image);
////			CardPanel c13 = new CardPanel(image);
//			
//			Vector<CardPanel> cards = new Vector<CardPanel>();
////			cards.add(c1);
////			cards.add(c2);
////			cards.add(c3);
////			cards.add(c4);
////			cards.add(c5);
////			cards.add(c6);
////			cards.add(c7);
////			cards.add(c8);
////			cards.add(c9);
////			cards.add(c10);
////			cards.add(c11);
////			cards.add(c12);
////			cards.add(c13);
//			
//			gs.placeCards(cards);
//			gameSpots.add(gs);
//		}
		//END TEST
		
		
		
		GameSpotsPanel gameSpostPanel = new GameSpotsPanel();
		
//		JPanel deckAndWell = new JPanel();
//		deckAndWell.add(new CardPanel(image1));
//		deckAndWell.add(new CardPanel(image1));
//				
//		deckAndWell.setMaximumSize(new Dimension(110,120));
//		deckAndWell.setBackground(new Color(185, 251, 192));
//		deckAndWell.setLayout(new FlowLayout());
		
		
		ImageIcon backImage = new ImageIcon();
		ImageIcon firstImage = new ImageIcon();
		try {
			backImage.setImage(ImageIO.read(getClass().getResource("../resources/images/" + '9' + '9' + ".png")));
			Card c = Game.getInstance().getWell().get(0);
			firstImage.setImage(CardToImage.getInstance().getImageFromCard(c).getImage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DeckAndWellPanel deckAndWell = new DeckAndWellPanel(backImage, firstImage);
		
		center.add(deckAndWell, BorderLayout.NORTH);
		center.add(gameSpostPanel, BorderLayout.CENTER); 
		this.add(center,BorderLayout.CENTER);
	}
	
	public void updateView() {
//		for (CardPanel cardPanel : cardPanels) {
//			Card c = cardPanel.getCard();
//			//ImageIcon image = new ImageIcon();
//			//image.setImage(ImageIO.read(()));
//			cardPanel.setImage();
//		}
	}
}
