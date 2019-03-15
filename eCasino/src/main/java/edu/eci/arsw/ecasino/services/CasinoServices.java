package edu.eci.arsw.ecasino.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.ecasino.model.Lobby;
import edu.eci.arsw.ecasino.model.Player;
import edu.eci.arsw.ecasino.model.Table;
import edu.eci.arsw.ecasino.persistence.CasinoPersistence;
import edu.eci.arsw.ecasino.persistence.CasinoPersistenceException;

@Service
public class CasinoServices {
	
	@Autowired
	private CasinoPersistence cps;
	
	/**
	 * Adds a new lobby to persistence, the int id of the lobby is assigned incrementally.
	 */
	public void addNewLobby() {
		// TODO: Implementation
	}
	
	/**
	 * Gets all lobbies stored in persistence.
	 * @return a list of all the lobbies.
	 */
	public List<Lobby> getAllLobbies() {
		ArrayList<Lobby> lobbies = new ArrayList<>();
		// TODO: Implementation
		return lobbies;  // TODO: Obtain from persistence.
	}
	
	/**
	 * Gets a lobby identified with the provided id.
	 * @param id the id of the requested lobby.
	 * @throws CasinoPersistenceException if the requested lobby doesn't exist.
	 */
	public void getLobby(int id) {
		// TODO: Implementation
	}
	
	/**
	 * Adds a new table to a lobby in persistence, the int id of the table is assigned incrementally.
	 * @param lobby the lobby where the table is going to be added.
	 * @param table the new table to be added.
	 * @throws CasinoPersistenceException if the lobby doesn't exist.
	 */
	public void addNewTable(Lobby lobby, Table table) {
		// TODO: Implementation
	}
	
	/**
	 * Gets all tables stored in persistence.
	 * @return a list of all the tables.
	 */
	public List<Table> getAllTables() {
		ArrayList<Table> tables = new ArrayList<>();
		// TODO: Implementation
		return tables;  // TODO: Obtain from persistence.
	}
	
	/**
	 * Gets all tables stored in a specific lobby.
	 * @param the lobby to find the tables from.
	 * @return a list of all the tables in the requested lobby.
	 * @throws CasinoPersistenceException if the provided lobby doesn't exist.
	 */
	public List<Table> getAllTablesInLobby(Lobby lobby) {
		ArrayList<Table> tables = new ArrayList<>();
		// TODO: Implementation
		return tables;  // TODO: Obtain from persistence.
	}
	
	/**
	 * Gets the table identified with the provided id.
	 * @param id the id of the table to find.
	 * @return the table identified with th provided id.
	 * @throws CasinoPersistenceException if the table doesn't exist.
	 */
	public Table getTable(int id) {
		// TODO: Implementation
		return new Table();  // TODO: Obtain from persistence.
	}
	
	/**
	 * Adds new user to persistence
	 * @param player the new player to be added.
	 * @throws CasinoPersistenceException if the username is already taken
	 */
	public void addNewPlayer(Player player) {
		// TODO: Implementation
	}
	
	/**
	 * Gets all users stored in persistence.
	 * @return a list of all the users.
	 */
	public List<Player> getAllPlayers() {
		ArrayList<Player> players = new ArrayList<>();
		// TODO: Implementation
		return players;  // TODO: Obtain from persistence.
	}
	
	/**
	 * Gets an user with the specified username
	 * @param username the username of the user to find
	 * @throws CasinoPersistenceException if the user doesn't exist.
	 */
	public Player getPlayer(String username) {
		// TODO: Implementation
		return new Player(username); // TODO: Obtain from persistence.
	}

}
