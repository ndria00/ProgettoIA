package application.view;



import javax.swing.ImageIcon;

import application.controller.DeckController;

public class DeckPanel extends CardPanel{
	
	
	

	private static final long serialVersionUID = 3154124699346921639L;
	public DeckPanel(ImageIcon image) {
		super(image);
		this.removeMouseListener(this.getMouseListeners()[0]);
		this.addMouseListener(new DeckController(this));
	}
	
	public DeckPanel(ImageIcon image, PlayerCardsPanel playerCards) {
		super(image, playerCards);
		this.removeMouseListener(this.getMouseListeners()[0]);
		this.addMouseListener(new DeckController(this));
	}

//	@Override
//	public void mouseClicked(MouseEvent e) {
//		Card c = Game.getInstance().getDeck().pick();
//		//System.out.print(c.getSuite() + " " + c.getNumber() + "\n");
//		ImageIcon image = new ImageIcon();
//		try {
//			image.setImage(ImageIO.read(getClass().getResource("../resources/" + c.getSuite() + c.getNumber() + ".png")));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		CardPanel card = new CardPanel(image, PlayerCardsPanel.getInstance());
//		card.setCard(c);
//		PlayerCardsPanel.getInstance().add(card);
//		PlayerCardsPanel.getInstance().revalidate();
//	}
}
