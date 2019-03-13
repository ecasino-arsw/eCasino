package edu.eci.arsw.ecasino.model.game;

import java.util.ArrayList;

import edu.eci.arsw.ecasino.model.Player;
import edu.eci.arsw.ecasino.model.cards.Deck;

public class HoldEm implements Game {
	
	private ArrayList<Player> players;
	private Deck playDeck, sharedDeck;
	
	public HoldEm() {
		setupGame();
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
