package edu.eci.arsw.ecasino.model.game;

import java.util.ArrayList;

import edu.eci.arsw.ecasino.model.Player;

public class BlackJack implements Game {
	
	private ArrayList<Player> players;
	
	public BlackJack() {
		
	}

	@Override
	public void setupGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
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