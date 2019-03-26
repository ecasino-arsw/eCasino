package edu.eci.arsw.ecasino.model;

import java.util.Set;

import edu.eci.arsw.ecasino.model.game.Game;

public class Table {
	
	private int id;
	private Game game;
	private Set<Player> players;
	
	

	public Table(int id,Game game) {
		this.id=id;
		this.game=game;
	}
	
	public Table(Game game) {
		setGame(game);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public Set<Player> getPlayers() {
		return players;
	}

}
