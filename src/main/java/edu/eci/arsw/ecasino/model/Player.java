package edu.eci.arsw.ecasino.model;

import edu.eci.arsw.ecasino.model.cards.Deck;

public class Player {
	
	private String username;
	private Deck hand;
	private int currency;
	
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
	
	public void transferCurrencyToPlayer(int amount, Player targetPlayer) throws CasinoException {
		substractCurrency(amount);
		targetPlayer.addCurrency(amount);
	}

	public void addCurrency(int amount) {
		this.currency += amount;
	}

	public void substractCurrency(int amount) throws CasinoException {
		if (this.currency < amount) {
			throw new CasinoException("There is not enough currency to transfer.");
		}
		this.currency -= amount;
	}

}
