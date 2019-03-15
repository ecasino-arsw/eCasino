package edu.eci.arsw.ecasino.model;

import edu.eci.arsw.ecasino.model.game.Game;

public class Table {
	
	private int id;
	private Game game;
	
	public Table() {
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

}
