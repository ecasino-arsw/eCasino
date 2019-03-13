package edu.eci.arsw.ecasino.model;

import edu.eci.arsw.ecasino.model.game.Game;

public class Table {
	
	private Game game;
	
	public Table() {
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

}
