package edu.eci.arsw.ecasino.model.cards;

public class Card {
	
	private Suit suit;
	private Value value;
	
	public Card(Suit suit, Value value) {
		setSuit(suit);
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Card: " + value + " of " + suit;
	}

}
