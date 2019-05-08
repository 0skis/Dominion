import java.util.*;

public class Pile {
	
	private HashMap<Card, Integer> pile;
	
	public Pile() {
		pile = new HashMap<Card, Integer>();
	}
	
	public String piletoString() {
		String output = "Cards you can buy: \n";
		String V = "Victory cards: ";
		String T = "Treasure cards: ";
		String A = "Action cards: ";
		for (Card x : pile.keySet()) {
			if (x.getCardType().equals("T")) {
				T += x.getName() + "(" + x.getCost() + "*)" + "(" + pile.get(x) + ")" + ", ";
			} else if (x.getCardType().equals("V")) {
				V +=  x.getName() + "(" + x.getCost() + "*)" + "(" + x.getPoints() + "p)" + "(" + pile.get(x) + ")" + ", ";
			} else if (x.getCardType().equals("A")) {
				A +=  x.getName() + "(" + x.getCost() + "*)" + "(" + pile.get(x) + ")" + ", ";
			}
		}
		return output + T + "\n" + V + "\n" + A + "\n";
	}
	
	public boolean contains(Card c) {
		return pile.keySet().contains(c);
	}
	
	public void addCard(Card c, int ammount) {
		if (ammount < 0) {
			return;
		}
		pile.put(c, ammount);
	}
	
	public int getAmmount(Card c) {
		if (!pile.containsKey(c)) {
			return 0;
		}
		return pile.get(c);
	}
	
	public void removeCard(Card c) {
		if (!pile.containsKey(c)) {
			return;
		}
		if (pile.get(c) <= 0) {
			return;
		}
		pile.put(c, pile.get(c)-1);
	}
	
	public boolean areThreeEmpty() {
		int e = 0;
		for (int x : pile.values()) {
			if (x == 0) {
				e++;
			}
		}
		return e >= 3;
	}
}
