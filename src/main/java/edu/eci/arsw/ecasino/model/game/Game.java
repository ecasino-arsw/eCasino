package edu.eci.arsw.ecasino.model.game;

import java.util.ArrayList;
import java.util.Set;

import edu.eci.arsw.ecasino.model.Player;

public interface Game {
	
	public void setupGame();
	
	public void start();
	
	public void finish();
	
	public ArrayList<Player> getPlayers();

}
