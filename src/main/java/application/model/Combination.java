package application.model;

public class Combination extends Play{
	private static final long serialVersionUID = -1237617730228735896L;

	@Override
	public boolean canAttach(Card card) {
		//cards are of the same suite
		if(card.getNumber() == this.get(0).getNumber() && this.size() < 4 && ! this.contains(card)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canAttach(Play p) {
		if(p.size() == 1)
			return canAttach(p.get(0));
		return false;
	}

	@Override
	public void attach(Play p) {
		
		for(Card c: p) {
			p.add(c);
		}
		
	}

}