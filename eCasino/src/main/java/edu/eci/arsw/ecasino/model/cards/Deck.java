package edu.eci.arsw.ecasino.model.cards;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

	ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<>();
	}

	public void createFullDeck() {
		for (Suit suit : Suit.values()) {
			for (Value value : Value.values()) {
				this.cards.add(new Card(suit, value));
			}
		}
	}

	public void shuffle() {
		ArrayList<Card> tmpDeck = new ArrayList<Card>();
		Random random = new Random();
		int randomCardIndex = 0;
		int originalSize = this.cards.size();
		for (int i = 0; i < originalSize; i++) {
			randomCardIndex = random.nextInt((this.cards.size() - 1 - 0) + 1) + 0;
			tmpDeck.add(this.cards.get(randomCardIndex));
			this.cards.remove(randomCardIndex);
		}
		this.cards = tmpDeck;
	}

	public void removeCard(int i) {
		this.cards.remove(i);
	}

	public Card getCard(int i) {
		return this.cards.get(i);
	}

	public void addCard(Card addCard) {
		this.cards.add(addCard);
	}

	public void draw(Deck comingFrom) {
		this.cards.add(comingFrom.getCard(0));
		comingFrom.removeCard(0);
	}

	public void moveAllToDeck(Deck moveTo) {
		int thisDeckSize = this.cards.size();
		for (int i = 0; i < thisDeckSize; i++) {
			moveTo.addCard(this.getCard(i));
		}
		for (int i = 0; i < thisDeckSize; i++) {
			this.removeCard(0);
		}
	}

	public int deckSize() {
		return this.cards.size();
	}

	@Override
	public String toString() {
		String cardListOutput = "";
		for (Card aCard : this.cards) {
			cardListOutput += "\n" + aCard.toString();
		}
		return cardListOutput;
	}

}
