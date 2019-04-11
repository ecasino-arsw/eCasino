package edu.eci.arsw.eCasino.model.cards;

public class Card {
	
	private Suit suit;
	private Value value;
	
	protected Card() {
	}

	public Card(Suit suit, Value value) {
		this.suit = suit;
		this.value = value;
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

}
