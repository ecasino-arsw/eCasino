package edu.eci.arsw.ecasino.model.cards;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a deck of cards. This deck can be used to represent a full deck of cards to play a game,
 * as well as a player's hand or a shared deck in -for example- a Texas Hold'Em game.
 * @author Daniel Vela
 *
 */
public class Deck {

	ArrayList<Card> cards;

	/**
	 * Generates an empty deck, for it to be used in different ways.
	 */
	public Deck() {
		cards = new ArrayList<>();
	}

	/**
	 * Fills the card array with a new complete deck, with a card of each value for
	 * each suit.
	 */
	public void createFullDeck() {
		for (Suit suit : Suit.values()) {
			for (Value value : Value.values()) {
				this.cards.add(new Card(suit, value));
			}
		}
	}

	/**
	 * Shuffles the cards in the deck randomly.
	 */
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

	/**
	 * Draws the card in top of the deck and removes it from it.
	 * 
	 * @return the card on top of the deck.
	 */
	public Card drawTopCard() {
		Card drawnCard = getCard(0);
		removeCard(0);
		return drawnCard;
	}

	/**
	 * Moves all the cards in this deck to another deck received in the parameter.
	 * 
	 * @param moveTo the target deck to move all the cards to.
	 */
	public void moveAllToDeck(Deck moveTo) {
		int thisDeckSize = this.cards.size();
		for (int i = 0; i < thisDeckSize; i++) {
			moveTo.addCard(this.getCard(i));
		}
		for (int i = 0; i < thisDeckSize; i++) {
			this.removeCard(0);
		}
	}

	/**
	 * Removes a card from the deck.
	 * @param i the index of the card to remove.
	 */
	public void removeCard(int i) {
		this.cards.remove(i);
	}

	/**
	 * Gets a card from the deck.
	 * @param i the index of the card to get.
	 * @return the card in the received index.
	 */
	public Card getCard(int i) {
		return this.cards.get(i);
	}

	/**
	 * Adds a card to the deck.
	 * @param addCard the card to be added to the deck.
	 */
	public void addCard(Card addCard) {
		this.cards.add(addCard);
	}

	@Override
	public String toString() {
		String cardListOutput = "";
		for (Card aCard : this.cards) {
			cardListOutput += "\n" + aCard.toString();
		}
		return cardListOutput;
	}

	public int deckSize() {
		return cards.size();
	}

}
