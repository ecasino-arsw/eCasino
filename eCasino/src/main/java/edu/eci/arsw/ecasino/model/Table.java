package edu.eci.arsw.ecasino.model;

import java.util.ArrayList;

import edu.eci.arsw.ecasino.model.game.Game;

public class Table {
	
	private Game game;
	private ArrayList<Player> players;
	
	public Table() {
		players = new ArrayList<>();
	}
	
	public Table(Game game) {
		setGame(game);
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

}
