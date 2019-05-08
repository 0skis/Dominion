import java.util.*;


public class PlayerHand {
	
	private ArrayList<Card> hand;
	private LinkedList<Card> drawpile;
	private LinkedList<Card> discardpile;
	private LinkedList<Card> inplay;
	private int money;
	private int actions;
	private int buys;
	
	public PlayerHand() {
		hand = new ArrayList<Card>();
		drawpile = new LinkedList<Card>();
		discardpile = new LinkedList<Card>();
		inplay = new LinkedList<Card>();
		money = 0;
		actions = 1;
		buys = 1;
		}
	
	public void draw(int k) {
		
		for (int i = 0; i < k; i++) {
			if (drawpile.peek() == null && discardpile.peek() == null) {
				System.out.println("No more cards to draw.\n");
				break;
			}
			if (drawpile.peek() == null) {
				//shuffle discard -> draw
				drawpile.addAll(discardpile);
				discardpile.clear();
				Collections.shuffle(drawpile);
			}
			hand.add(drawpile.pollFirst());
		}
	}
	
	public void play(Card c) {
		if (c.getCardType().contentEquals("V")) {
			return;
		}
		if (c.getCardType().contentEquals("T")) {
			money += c.getMoney();
			this.putinPlay(c);
			return;
		}
		if (c.getCardType().contentEquals("A")) {
			actions--;
			actions += c.getActions();
			money += c.getMoney();
			buys += c.getBuy();
			if (c.getDraw() > 0) {
				this.draw(c.getDraw());
			}
			this.putinPlay(c);
			return;
		}
	}
	
	public void putinPlay(Card c) {
		hand.remove(c);
		inplay.add(c);
	}
	
	public void discard(Card c) {
		if(!hand.contains(c)) {
			return;
		}
		discardpile.add(c);
		hand.remove(c);
	}
	
	public void playAllT() {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getCardType().contentEquals("T")) {
				play(hand.get(i));
				i--;
			}
		}
	}
	
	public void buy(Card c) {
		if (c.getCost() > money) {
			System.out.println("Cant afford!");
			return;
		} else if (buys < 1) {
			System.out.println("No buys left.");
			return;
		}
		discardpile.add(c);
		money -= c.getCost();
		buys--;
	}
	
	public int getPoints() {
		int output = 0;
		for (Card x : drawpile) {
			output += x.getPoints();
		}
		for (Card x : hand) {
			output += x.getPoints();
		}
		for (Card x : discardpile) {
			output += x.getPoints();
		}
		return output;
	}
	
	public int getMoney() {
		return money;
	}
	
	public int getActions() {
		return actions;
	}
	
	public void discardHand() {
		discardpile.addAll(hand);
		hand.clear();
	}
	
	public void discardInplay() {
		discardpile.addAll(inplay);
		inplay.clear();
	}
	
	public void resetMoney() {
		money = 0;
	}
	
	public void resetBuy() {
		buys = 1;
	}
	
	public void resetActions() {
		actions = 1;
	}
	
	public void resetAll() {
		resetMoney();
		resetBuy();
		resetActions();
	}
	
	public String handtoString() {
		String output = "";
		for	(Card x : hand) {
			output += x.getName() + ", ";
		}
		return output;
	}
	
	public void addCard(Card c) {
		drawpile.add(c);
	}
	
	public void shuffleDraw() {
		Collections.shuffle(drawpile);
	}
	
	public boolean contains(Card c) {
		return hand.contains(c);
	}
	
	public boolean containsActionCard() {
		for (Card x : hand) {
			if (x.getCardType().contentEquals("A")) {
				return true;
			}
		}
		return false;
	}
	
	public int getBuy() {
		return buys;
	}
	
	public boolean Empty() {
		return hand.isEmpty();
	}
	
}
