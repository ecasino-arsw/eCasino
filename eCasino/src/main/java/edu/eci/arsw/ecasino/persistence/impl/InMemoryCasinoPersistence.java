/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.ecasino.persistence.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import edu.eci.arsw.ecasino.model.Lobby;
import edu.eci.arsw.ecasino.model.Player;
import edu.eci.arsw.ecasino.model.Table;
import edu.eci.arsw.ecasino.persistence.CasinoPersistence;
import edu.eci.arsw.ecasino.persistence.CasinoPersistenceException;

/**
 *
 * @author Daniel Vela
 */
@Component("cps")
public class InMemoryCasinoPersistence implements CasinoPersistence {
	
	private final Map<Integer, Lobby> lobbies = new HashMap<>();
	
	public InMemoryCasinoPersistence() throws CasinoPersistenceException {
		Lobby lobby = new Lobby(1);
		addNewLobby(lobby);
	}

	@Override
	public void addNewLobby(Lobby lobby) throws CasinoPersistenceException {
		if (lobbies.containsKey(lobby.getId())) {
			throw new CasinoPersistenceException("The given lobby id already exists: " + lobby.getId());
		}
		lobbies.put(lobby.getId(), lobby);
	}

	@Override
	public Set<Lobby> getAllLobbies() {
		Set<Lobby> allLobbies = new HashSet<>();
		lobbies.entrySet().stream().forEach((entry) -> {
			allLobbies.add(entry.getValue());
		});
		return allLobbies;
	}

	@Override
	public Lobby getLobby(int id) {
		return lobbies.get(id);
	}

	@Override
	public void addNewTable(Lobby lobby, Table table) throws CasinoPersistenceException {
		throw new CasinoPersistenceException("This method is not supported yet.");
	}

	@Override
	public List<Table> getAllTables() throws CasinoPersistenceException {
		throw new CasinoPersistenceException("This method is not supported yet.");
	}

	@Override
	public List<Table> getAllTablesInLobby(Lobby lobby) throws CasinoPersistenceException {
		throw new CasinoPersistenceException("This method is not supported yet.");
	}

	@Override
	public Table getTable(Lobby lobby, int id) throws CasinoPersistenceException {
		throw new CasinoPersistenceException("This method is not supported yet.");
	}

	@Override
	public void addNewPlayer(Player player) throws CasinoPersistenceException {
		throw new CasinoPersistenceException("This method is not supported yet.");
		
	}

	@Override
	public List<Player> getAllPlayers() throws CasinoPersistenceException {
		throw new CasinoPersistenceException("This method is not supported yet.");
	}

	@Override
	public Player getPlayer(String username) throws CasinoPersistenceException {
		throw new CasinoPersistenceException("This method is not supported yet.");
	}

	@Override
	public List<Player> getAllPlayersInTable(Table table) throws CasinoPersistenceException {
		throw new CasinoPersistenceException("This method is not supported yet.");
	}
    
}
