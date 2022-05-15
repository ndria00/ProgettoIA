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
import application.Settings;
import application.ViewsHandler;
import application.controller.PlayViewController;
import application.model.Card;
import application.model.Game;
import application.model.Play;

public class PlayView extends JPanel{
	private static final long serialVersionUID = -8129006343701355252L;
	private JButton leaveButton = null;
	private AddSetButton addSetButton = null;
	private JPanel buttonsBar = null;
	private Vector<CardPanel> cardPanels = new Vector<CardPanel>();
	private GameSpotsPanel gameSpotPanels;
	private DeckAndWellPanel deckAndWellPanel;
	private BotCardsPanel botCardsPanel;
	private static PlayView instance = null;
	
	public static PlayView getInstance() {
		if(instance == null) {
			instance = new PlayView();
		}
		return instance;
	}
	
	private PlayView() {
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
				CardPanel p1 = new CardPanel(image, PlayerCardsPanel.getInstance(),Settings.BORDER);
				p1.setCard(c);
				this.cardPanels.add(p1);
				PlayerCardsPanel.getInstance().add(p1);
		}
		 
		
		
		//center
		
		JPanel center = new JPanel();
		BorderLayout centerBorderLayout = new BorderLayout();
		center.setLayout(centerBorderLayout);
		center.setBackground(new Color(185, 251, 192));
		
		
		
		gameSpotPanels = new GameSpotsPanel();

		this.addMouseListener(new PlayViewController(this));
		
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
		
		deckAndWellPanel = new DeckAndWellPanel(backImage, firstImage);
		JPanel botCardsAndDeckAndWell = new JPanel();
		botCardsAndDeckAndWell.setLayout(new FlowLayout());
		botCardsPanel = new BotCardsPanel();
		botCardsAndDeckAndWell.add(botCardsPanel);
		botCardsAndDeckAndWell.add(deckAndWellPanel);
		center.add(botCardsAndDeckAndWell, BorderLayout.NORTH);
		center.add(gameSpotPanels, BorderLayout.CENTER);
		this.add(center,BorderLayout.CENTER);
		
	}
	
	public void updateView() {
		this.updateGameSpots();
		this.deckAndWellPanel.getWellPanel().updateWellPanel();
		PlayerCardsPanel.getInstance().update();
		this.botCardsPanel.update();
	}
	
	public void updateGameSpots() {
		if(gameSpotPanels != null) {
			int index = 0;
			GameSpot gameSpot;
			for(Play play : Game.getInstance().getPlays()) {
				//WHAT THE HELL IS THIS???
				gameSpot = gameSpotPanels.getGameSpots().get(index);
				index++;
				//
				gameSpot.removeAll();
				for(Card card : play) {
					//WHY CREATING A NEW IMAGE ICON WHEN YOU CAN JUST GET IT FROM THE HASH MAP?
					ImageIcon image = new ImageIcon();
					image.setImage(CardToImage.getInstance().getImageFromCard(card).getImage());
					CardPanel cardpanel = new CardPanel(image,Settings.NO_BORDER);
					cardpanel.setCard(card);
					gameSpot.add(cardpanel);
					gameSpot.setCurrentPlay(play);
				}
			}
		}
		System.out.println("UPDATED");
	}

	public DeckAndWellPanel getDeckAndWellPanel() {
		return deckAndWellPanel;
	}

	public void setDeckAndWellPanel(DeckAndWellPanel deckAndWellPanel) {
		this.deckAndWellPanel = deckAndWellPanel;
	}

	public BotCardsPanel getBotCardsPanel() {
		return botCardsPanel;
	}

	public void setBotCardsPanel(BotCardsPanel botCardsPanel) {
		this.botCardsPanel = botCardsPanel;
	}
	

}
