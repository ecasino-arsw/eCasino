package edu.eci.arsw.ecasino.model.cards;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a deck of cards. This deck can be used to represent a
 * full deck of cards to play a game, as well as a player's hand or a shared
 * deck in -for example- a Texas Hold'Em game.
 *
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
    

    public void draw(Deck comingFrom) {
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public int cardsValue() {
        int total = 0;
        int aces = 0;
        
        for (Card aCard : this.cards) {
            System.out.println("valor> "+aCard.getValue());
            if (aCard.getValue() == Value.ACE) {
                aces += 1;
            } else if (aCard.getValue() == Value.TWO) {
                total += 2;
            } else if (aCard.getValue() == Value.THREE) {
                total += 3;
            } else if (aCard.getValue() == Value.FOUR) {
                total += 4;
            } else if (aCard.getValue() == Value.FIVE) {
                total += 5;
            } else if (aCard.getValue() == Value.SIX) {
                total += 6;
            } else if (aCard.getValue() == Value.SEVEN) {
                total += 7;
            } else if (aCard.getValue() == Value.EIGHT) {
                total += 8;
            } else if (aCard.getValue() == Value.NINE) {
                total += 9;
            } else if (aCard.getValue() == Value.TEN) {
                total += 10;
            } else if (aCard.getValue() == Value.JACK) {
                total += 11;
            } else if (aCard.getValue() == Value.QUEEN) {
                total += 12;
            } else {
                total += 13;
            }

        }
        for(int i=0; i< aces; i++){
            if(total > 11)total +=1;
            else total+=11;
        }
        return total;
    }

}
