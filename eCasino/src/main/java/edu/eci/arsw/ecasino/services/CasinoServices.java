package edu.eci.arsw.ecasino.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
	 * @throws CasinoPersistenceException if the id of the lobby is already taken.
	 */
	public void addNewLobby(Lobby lobby) throws CasinoPersistenceException {
		cps.addNewLobby(lobby);
	}
	
	/**
	 * Gets all lobbies stored in persistence.
	 * @return a list of all the lobbies.
	 * @throws CasinoPersistenceException if there are no lobbies in persistence.
	 */
	public Set<Lobby> getAllLobbies() throws CasinoPersistenceException {
		return cps.getAllLobbies();
	}
	
	/**
	 * Gets a lobby identified with the provided id.
	 * @param id the id of the requested lobby.
	 * @throws CasinoPersistenceException if the requested lobby doesn't exist.
	 */
	public Lobby getLobby(int id) throws CasinoPersistenceException {
		Lobby lobby = cps.getLobby(id);
		if (lobby == null) {
			throw new CasinoPersistenceException("Lobby not found.");
		}
		return lobby;
	}
	
	/**
	 * Adds a new table to a lobby in persistence, the int id of the table is assigned incrementally.
	 * @param lobby the lobby where the table is going to be added.
	 * @param table the new table to be added.
	 * @throws CasinoPersistenceException if the lobby doesn't exist.
	 */
	public void addNewTable(Lobby lobby, Table table) throws CasinoPersistenceException {
		cps.addNewTable(lobby, table);
	}
	
	/**
	 * Gets all tables stored in persistence.
	 * @return a list of all the tables.
	 * @throws CasinoPersistenceException if there are no tables in persistence.
	 */
	public List<Table> getAllTables() throws CasinoPersistenceException {
		return cps.getAllTables();
	}
	
	/**
	 * Gets all tables stored in a specific lobby.
	 * @param the lobby to find the tables from.
	 * @return a list of all the tables in the requested lobby.
	 * @throws CasinoPersistenceException if the provided lobby doesn't exist.
	 */
	public Map<Integer, Table> getAllTablesInLobby(Lobby lobby) throws CasinoPersistenceException {
		return cps.getAllTablesInLobby(lobby);
	}
	
	/**
	 * Gets the table identified with the provided id.
	 * @param lobby the lobby in which the table is contained.
	 * @param id the id of the table to find.
	 * @return the table identified with th provided id.
	 * @throws CasinoPersistenceException if the lobby doesn't exist, the table isn't in the given lobby or 
	 * if the table doesn't exist.
	 */
	public Table getTable(Lobby lobby, int id) throws CasinoPersistenceException {
		return cps.getTable(lobby, id);
	}
	
	/**
	 * Adds new user to persistence
	 * @param player the new player to be added.
	 * @throws CasinoPersistenceException if the username is already taken
	 */
	public void addNewPlayer(Player player) throws CasinoPersistenceException {
		cps.addNewPlayer(player);
	}
	
	/**
	 * Gets all users stored in persistence.
	 * @return a list of all the users.
	 * @throws CasinoPersistenceException if there are no users in persistence.
	 */
	public Set<Player> getAllPlayers() throws CasinoPersistenceException {
		return cps.getAllPlayers();
	}
	
	/**
	 * Gets all users playing in a table.
	 * @return a list of all the users in the given table.
	 * @throws CasinoPersistenceException if there are no users in the table or the table doesn't exist.
	 */
	public Set<Player> getAllPlayersInTable(Table table) throws CasinoPersistenceException {
		return cps.getAllPlayersInTable(table);
	}
	
	/**
	 * Gets an user with the specified username
	 * @param username the username of the user to find
	 * @throws CasinoPersistenceException if the username doesn't exist.
	 */
	public Player getPlayer(String username) throws CasinoPersistenceException {
		return cps.getPlayer(username);
	}
	
	/**
	 * Adds an existing player to a table
	 * @param table the table to join
	 * @param player the player to add
	 */
	public void addPlayerToTable(Table table,Player player) throws CasinoPersistenceException{
		cps.addPlayerToTable(table,player);
	}

}
