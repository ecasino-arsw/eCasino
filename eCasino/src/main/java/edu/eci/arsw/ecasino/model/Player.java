package edu.eci.arsw.ecasino.model;

import edu.eci.arsw.ecasino.model.cards.Deck;

public class Player {
	
	private String username;
	private Deck hand;
	
	public Player(String username) {
		setUsername(username);
		hand = new Deck();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Deck getHand() {
		return hand;
	}

	public void setHand(Deck hand) {
		this.hand = hand;
	}

	public void draw(Deck deck) {
		hand.addCard(deck.drawTopCard());
	}

}
