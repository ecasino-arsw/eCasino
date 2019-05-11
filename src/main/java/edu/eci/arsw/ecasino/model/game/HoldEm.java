package edu.eci.arsw.ecasino.model.game;

import java.util.ArrayList;
import java.util.HashMap;

import edu.eci.arsw.ecasino.model.Player;
import edu.eci.arsw.ecasino.model.cards.Deck;
import edu.eci.arsw.ecasino.model.game.interf.IGame;

public class HoldEm implements IGame {

	private ArrayList<Player> players;
	private HashMap<String, Deck> playersHands;
	private Deck deck, tableCards;
	private int pot;
	private Player smallBlind, bigBlind, dealer;

	public static void main(String[] args) {
		ArrayList<Player> players = new ArrayList<>();
		players.add(new Player("johnDoe", "test1", "John Doe", "johndoe@domain.com"));
		players.add(new Player("janeDoe", "test2", "Jane Doe", "janedoe@domain.com"));
		players.add(new Player("jamesDoe", "test3", "James Doe", "jamesdoe@domain.com"));
		players.add(new Player("jackDoe", "test4", "Jack Doe", "jackdoe@domain.com"));
		HoldEm game = new HoldEm(players);
		game.setupGame();
		game.playHand();
	}

	public HoldEm(ArrayList<Player> players) {
		this.players = players;
		this.playersHands = new HashMap<>();
		for (Player player : players) {
			playersHands.put(player.getUsername(), new Deck());
		}
		this.deck = new Deck();
		this.tableCards = new Deck();
	}

	public void setupGame() {
		deck.createFullDeck();
		deck.shuffle();
		System.out.println("Shuffling...");
		pot = 0;
	}

	public void setButtons() {
		if (dealer == null) {
			dealer = players.get(0);
			smallBlind = players.get(1);
			bigBlind = players.get(2);
		} else {
			int dealerIndex = players.indexOf(dealer);
			int smallBlindIndex = players.indexOf(smallBlind);
			int bigBlindIndex = players.indexOf(bigBlind);
			if (dealerIndex < players.size()) {
				dealer = players.get(dealerIndex + 1);
			} else {
				dealer = players.get(0);
			}
			if (smallBlindIndex < players.size()) {
				smallBlind = players.get(dealerIndex + 1);
			} else {
				smallBlind = players.get(0);
			}
			if (bigBlindIndex < players.size()) {
				bigBlind = players.get(smallBlindIndex + 1);
			} else {
				bigBlind = players.get(0);
			}
		}
		System.out.println("Dealer: " + dealer.getUsername());
		System.out.println("Small blind: " + smallBlind.getUsername());
		System.out.println("Big blind: " + bigBlind.getUsername());
	}

	public void playHand() {
		setButtons();
		dealCards();
		playFlop();
		playTurn();
		playRiver();
	}

	public void dealCards() {
		for (int i = 0; i < 2; i++) {
			for (Player player : players) {
				playersHands.get(player.getUsername()).draw(deck);
			}
		}
		for (Player player : players) {
			System.out.println(player.getUsername() + "'s hand:");
			for (int i = 0; i < playersHands.get(player.getUsername()).deckSize(); i++) {
				System.out.println(playersHands.get(player.getUsername()).getCard(i).toString());
			}
		}
		// TODO: Betting round.
	}

	public void playFlop() {
		for (int i = 0; i < 3; i++) {
			tableCards.draw(deck);
		}
		System.out.println("Flop round:");
		for (int i = 0; i < tableCards.deckSize(); i++) {
			System.out.println(tableCards.getCard(i).toString());
		}
		// TODO: Betting round.
	}

	public void playTurn() {
		for (int i = 0; i < 1; i++) {
			tableCards.draw(deck);
		}
		System.out.println("Turn round:");
		for (int i = 0; i < tableCards.deckSize(); i++) {
			System.out.println(tableCards.getCard(i).toString());
		}
		// TODO: Betting round.
	}

	public void playRiver() {
		for (int i = 0; i < 1; i++) {
			tableCards.draw(deck);
		}
		System.out.println("River round:");
		for (int i = 0; i < tableCards.deckSize(); i++) {
			System.out.println(tableCards.getCard(i).toString());
		}
		// TODO: Betting round.
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public Deck getTableCards() {
		return tableCards;
	}

	public void setTableCards(Deck tableCards) {
		this.tableCards = tableCards;
	}

	public int getPot() {
		return pot;
	}

	public void setPot(int pot) {
		this.pot = pot;
	}

}
