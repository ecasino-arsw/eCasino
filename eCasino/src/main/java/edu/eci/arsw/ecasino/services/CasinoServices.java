package edu.eci.arsw.ecasino.services;

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
		cps.addNewLobby();
	}
	
	/**
	 * Gets all lobbies stored in persistence.
	 * @return a list of all the lobbies.
	 */
	public List<Lobby> getAllLobbies() {
		return cps.getAllLobbies();
	}
	
	/**
	 * Gets a lobby identified with the provided id.
	 * @param id the id of the requested lobby.
	 * @throws CasinoPersistenceException if the requested lobby doesn't exist.
	 */
	public void getLobby(int id) {
		cps.getLobby(id);
	}
	
	/**
	 * Adds a new table to a lobby in persistence, the int id of the table is assigned incrementally.
	 * @param lobby the lobby where the table is going to be added.
	 * @param table the new table to be added.
	 * @throws CasinoPersistenceException if the lobby doesn't exist.
	 */
	public void addNewTable(Lobby lobby, Table table) {
		cps.addNewTable(lobby, table);
	}
	
	/**
	 * Gets all tables stored in persistence.
	 * @return a list of all the tables.
	 */
	public List<Table> getAllTables() {
		return cps.getAllTables();
	}
	
	/**
	 * Gets all tables stored in a specific lobby.
	 * @param the lobby to find the tables from.
	 * @return a list of all the tables in the requested lobby.
	 * @throws CasinoPersistenceException if the provided lobby doesn't exist.
	 */
	public List<Table> getAllTablesInLobby(Lobby lobby) {
		return cps.getAllTablesInLobby(lobby);
	}
	
	/**
	 * Gets the table identified with the provided id.
	 * @param id the id of the table to find.
	 * @return the table identified with th provided id.
	 * @throws CasinoPersistenceException if the table doesn't exist.
	 */
	public Table getTable(int id) {
		return cps.getTable(id);
	}
	
	/**
	 * Adds new user to persistence
	 * @param player the new player to be added.
	 * @throws CasinoPersistenceException if the username is already taken
	 */
	public void addNewPlayer(Player player) {
		cps.addNewPlayer(player);
	}
	
	/**
	 * Gets all users stored in persistence.
	 * @return a list of all the users.
	 */
	public List<Player> getAllPlayers() {
		return cps.getAllPlayers();
	}
	
	/**
	 * Gets an user with the specified username
	 * @param username the username of the user to find
	 * @throws CasinoPersistenceException if the user doesn't exist.
	 */
	public Player getPlayer(String username) {
		return cps.getPlayer(username);
	}

}
