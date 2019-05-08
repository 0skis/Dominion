

public class Card {
	
	private Card() {
	}
	private String name;
	private Type type;
	private int actions;
	private int money;
	private int draw;
	private int buy;
	private int cost;
	private int points;
	
	public int getActions() {
		return actions;
	}
	
	public int getMoney() {
		return money;
	}
	
	public int getDraw() {
		return draw;
	}
	
	public int getBuy() {
		return buy;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCardType() {
		return type.toCardString();
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getPoints() {
		return points;
	}
	
public static class Builder {
		
		private String name;
		private Type type;
		private int actions;
		private int money;
		private int draw;
		private int buy;
		private int cost;
		private int points;
		
		public Builder(String n) {
			name = n;
		}
		
		public Builder Type(String t) {
			type = new Type(t);
			return this;
		}
		
		public Builder Action(int i) {
			actions = i;
			return this;
		}
		
		public Builder Money(int i) {
			money = i;
			return this;
		}
		
		public Builder Draw(int i) {
			draw = i;
			return this;
		}
		
		public Builder Buy(int i ) {
			buy = i;
			return this;
		}
		
		public Builder Cost(int i) {
			cost = i;
			return this;
		}
		
		public Builder Points(int i) {
			points = i;
			return this;
		}
		
		public Card build() {
			Card card = new Card();
			card.actions = actions;
			card.cost = cost;
			card.draw = draw;
			card.buy = buy;
			card.money = money;
			card.name = name;
			card.points = points;
			card.type = type;
			return card;
		}
	}
}

class Type{
	
	private String Cardtype;
	/*
	 * N = none
	 * A = action
	 * T = treasure
	 * V = victory
	*/
	public Type(String t) {
		Cardtype = t;
	}
	
	public String toCardString() {
		return Cardtype;
	}
}



