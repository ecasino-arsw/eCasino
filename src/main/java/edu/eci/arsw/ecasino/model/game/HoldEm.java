package edu.eci.arsw.ecasino.model.game;

import java.util.ArrayList;

import edu.eci.arsw.ecasino.model.Player;
import edu.eci.arsw.ecasino.model.cards.Deck;

public class HoldEm implements Game {
	
	private ArrayList<Player> players;
	private Deck playDeck, sharedDeck;
	
	public static void main(String[] args) {
		ArrayList<Player> players = new ArrayList<>();
		//players.add(new Player("velomeister"));
		
	}
	
	public HoldEm() {
		players = new ArrayList<>();
		start();
	}

	@Override
	public void setupGame() {
		this.playDeck = new Deck();
		this.sharedDeck = new Deck();
		playDeck.createFullDeck();
		playDeck.shuffle();
		for (int i = 0; i < 2; i++) {
			for (Player player : players) {
				player.draw(playDeck);
			}
		}
	}

	@Override
	public void start() {
		setupGame();
		flop();
		middle();
		river();
		System.out.println("Players hands:");
		for (Player player : players) {
			System.out.println(player.getUsername());
			System.out.println(player.getHand());
		}
	}
	
	public void  flop() {
		for (int i = 0 ;  i < 3; i++) {
			sharedDeck.addCard(playDeck.drawTopCard());
		}
		System.out.println("Flop...");
	}
	
	public void middle() {
		sharedDeck.addCard(playDeck.drawTopCard());
		System.out.println("Middle...");
	}
	
	public void river() {
		sharedDeck.addCard(playDeck.drawTopCard());
		System.out.println("River...");
		for (int i = 0; i < sharedDeck.deckSize(); i++) { 
			System.out.println(sharedDeck.getCard(i).toString());
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

}
