import java.util.*;


/*
***TO DO LIST***
Spela flera spelare
Lägga till alla basic kort
Spelet tar slut när 3 högar är slut
*/

public class Main {

	public static void main(String[] args) {
		Scanner player = new Scanner(System.in);
		System.out.print("***Dominion Project***");
		System.out.println();
		System.out.println();
		boolean b = true;
		String q1 = "";
		while (b) {
			System.out.print("How many Provinces do you want?\n>>");
			q1 = player.nextLine();
			
			try {
				Integer.parseInt(q1);
				b = false;
			} catch (NumberFormatException e) {
				System.out.println("Error, input was not a number...");
			}
		}
		
		int prov = Integer.parseInt(q1);
		System.out.println();
		
		HashMap<String, Card> CardConverter = new HashMap<String, Card>();
		Pile pile = new Pile();
		PlayerHand hand = new PlayerHand();
		int turn = 0;
		
		Card Copper = new Card.Builder("Copper")
				.Type("T")
				.Cost(0)
				.Money(1)
				.build();
		
		Card Silver	= new Card.Builder("Silver")
				.Type("T")
				.Cost(3)
				.Money(2)
				.build();
		
		Card Gold = new Card.Builder("Gold")
				.Type("T")
				.Cost(6)
				.Money(3)
				.build();
		
		Card Estate	= new Card.Builder("Estate")
				.Type("V")
				.Cost(2)
				.Points(1)
				.build();
		
		Card Dutchy	= new Card.Builder("Dutchy")
				.Type("V")
				.Cost(5)
				.Points(3)
				.build();
		
		Card Province = new Card.Builder("Province")
				.Type("V")
				.Cost(8)
				.Points(6)
				.build();
		
		Card Woodcutter = new Card.Builder("Woodcutter")
				.Type("A")
				.Cost(3)
				.Money(2)
				.Buy(1)
				.build();
		
		Card Smithy = new Card.Builder("Smithy")
				.Type("A")
				.Cost(4)
				.Draw(3)
				.build();
		
		Card Village = new Card.Builder("Village")
				.Type("A")
				.Cost(3)
				.Draw(1)
				.Action(2)
				.build();
		
		Card Market = new Card.Builder("Market")
				.Type("A")
				.Cost(5)
				.Draw(1)
				.Money(1)
				.Action(1)
				.Buy(1)
				.build();
		
		Card Festival = new Card.Builder("Festival")
				.Type("A")
				.Cost(5)
				.Action(2)
				.Buy(1)
				.Money(2)
				.build();
		
		Card Laboratory = new Card.Builder("Laboratory")
				.Type("A")
				.Cost(5)
				.Draw(2)
				.Action(1)
				.build();
		
		pile.addCard(Copper, 60);
		pile.addCard(Silver, 40);
		pile.addCard(Gold, 30);
		pile.addCard(Estate, 8);
		pile.addCard(Dutchy, 8);;
		pile.addCard(Province, prov);
		pile.addCard(Woodcutter, 10);
		pile.addCard(Smithy, 10);
		pile.addCard(Village, 10);
		pile.addCard(Market, 10);
		pile.addCard(Festival, 10);
		pile.addCard(Laboratory, 10);
		
		CardConverter.put("Copper", Copper);
		CardConverter.put("Silver", Silver);
		CardConverter.put("Gold", Gold);
		CardConverter.put("Estate", Estate);
		CardConverter.put("Dutchy", Dutchy);
		CardConverter.put("Province", Province);
		CardConverter.put("Woodcutter", Woodcutter);
		CardConverter.put("Smithy", Smithy);
		CardConverter.put("Village", Village);
		CardConverter.put("Market", Market);
		CardConverter.put("Festival", Festival);
		CardConverter.put("Laboratory", Laboratory);
		CardConverter.put("copper", Copper);
		CardConverter.put("silver", Silver);
		CardConverter.put("gold", Gold);
		CardConverter.put("estate", Estate);
		CardConverter.put("dutchy", Dutchy);
		CardConverter.put("province", Province);
		CardConverter.put("woodcutter", Woodcutter);
		CardConverter.put("smithy", Smithy);
		CardConverter.put("village", Village);
		CardConverter.put("market", Market);
		CardConverter.put("festival", Festival);
		CardConverter.put("laboratory", Laboratory);
		CardConverter.put("cop", Copper);
		CardConverter.put("sil", Silver);
		CardConverter.put("gol", Gold);
		CardConverter.put("est", Estate);
		CardConverter.put("dut", Dutchy);
		CardConverter.put("pro", Province);
		CardConverter.put("woo", Woodcutter);
		CardConverter.put("smi", Smithy);
		CardConverter.put("vil", Village);
		CardConverter.put("mar", Market);
		CardConverter.put("fes", Festival);
		CardConverter.put("lab", Laboratory);
		

		for (int i = 0; i < 3; i++) {
			hand.addCard(Estate);
		}
		for (int i = 0; i < 7; i++) {
			hand.addCard(Copper);
		}
		hand.shuffleDraw();
		hand.draw(5);
		//gameloopen
		while(true) {
			if (pile.getAmmount(Province) == 0 || pile.areThreeEmpty()) {
				break;
			}
			turn++;
			//start of turn
			System.out.println("Turn " + turn + "");
			// Actions phase, play actions
			System.out.println("\n**Action phase**");
			while (true) {
				System.out.println();
				System.out.println("Your hand: ");
				System.out.println(hand.handtoString());
				System.out.println("Actions left: " + hand.getActions());
				System.out.println("Your money: " + hand.getMoney());
				if (hand.getActions() < 1) {
					System.out.println("\nYou have no actions left. Press Enter!");
					player.nextLine();
					break;
				}
				if (!hand.containsActionCard()) {
					System.out.println("\nYou have no action cards left.");
					break;
				}
				System.out.print("Play a card or type end!\n>>");
				String input = player.nextLine();
				if	(input.contentEquals("end")) {
					break;
				} else if (CardConverter.get(input) == null) {
					System.out.println("\nCard not found. Try again!");
				} else if (CardConverter.containsKey(input) && CardConverter.get(input).getCardType() == "A") {
					Card c = CardConverter.get(input);
					if (hand.contains(c)) {
						hand.play(c);
					} else {
						System.out.println("You dont have that card in your hand!");
					}
				} else if (CardConverter.get(input).getCardType() != "A") {
					System.out.println();
					System.out.println("That card is not an action card!");
				} else {
					System.out.println();
					System.out.println("You cant play that card!");
				}
			}
			//Money phase, play treasures
			System.out.println("\n**Treasure phase**");
			while (true) {
				if (hand.Empty()) {
					System.out.println();
					System.out.println("You hand is empty. Press Enter!");
					player.nextLine();
					System.out.println();
					break;
				}
				System.out.println();
				System.out.println("Your hand: ");
				System.out.println(hand.handtoString());
				System.out.println("Your money: " + hand.getMoney());
				System.out.print("\nPlay a treasure, type all to play all treasures or type end!\n>>");
				String input = player.nextLine();
				if	(input.contentEquals("end")) {
					break;
				} else if (input.contentEquals("all")) {
					hand.playAllT();
					System.out.print("\nYou played all treasures and have " + hand.getMoney() + " money. ");
					System.out.println("Going to buy phase!");
					break;
				} else if (CardConverter.get(input) == null) {
					System.out.println("\nCard not found. Try again!");
				} else if (CardConverter.containsKey(input) && CardConverter.get(input).getCardType() == "T") {
					Card c = CardConverter.get(input);
					if (hand.contains(c)) {
						hand.play(c);
					} else {
						System.out.println("You dont have that card in your hand!");
					}
				} else if (CardConverter.get(input).getCardType() != "A") {
					System.out.println();
					System.out.println("That card is not a treasure card!");
				} else {
					System.out.println();
					System.out.println("You cant play that card!");
				}
			}
			//Buyphase, buy cards from pile
			System.out.println("\n**Buy phase**");
			while (true) {
				if (hand.getBuy() == 0) {
					System.out.println();
					System.out.println("You have no buys left, ending turn. Press Enter!");
					player.nextLine();
					break;
				}
				System.out.println();
				System.out.println("You have " + hand.getMoney() + " money.");
				System.out.println("You have " + hand.getBuy()  + " buy(s).");
				System.out.print(pile.piletoString());
				System.out.println();
				System.out.print("\nBuy a card or type end!\n>>");
				String input = player.nextLine();
				if (input.contentEquals("end")) {
					System.out.println();
					break;
				} else if (CardConverter.get(input) == null) {
					System.out.println("\nCard not found. Try again!");
				} else if (pile.contains(CardConverter.get(input)) && pile.getAmmount(CardConverter.get(input)) > 0 && hand.getMoney() >= CardConverter.get(input).getCost()){
					hand.buy(CardConverter.get(input));
					pile.removeCard(CardConverter.get(input));
					System.out.println("\nYou bought a " + CardConverter.get(input).getName() + ".");
				} else if (CardConverter.get(input).getCost() > hand.getMoney()) {
					System.out.println("\nNot enough money, try again!");
				} else if(pile.getAmmount(CardConverter.get(input)) == 0) {
					System.out.println("\nNo cards left in the stockpile, try again!");
				} else {
					System.out.println("\nSomething went wrong, try again!");
				}
			}
			hand.discardHand();
			hand.discardInplay();
			hand.draw(5);
			hand.resetAll();
			//reset actions and buys
		}
		System.out.println("***Game finished!***");
		System.out.println("You got " + hand.getPoints() + " points in " + turn + " turns!");
		System.out.println("Press Enter to close.");
		player.nextLine();
		player.close();
		System.exit(0);
	}
}


