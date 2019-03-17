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

import edu.eci.arsw.ecasino.model.Lobby;
import edu.eci.arsw.ecasino.model.Player;
import edu.eci.arsw.ecasino.model.Table;
import edu.eci.arsw.ecasino.persistence.CasinoPersistence;
import edu.eci.arsw.ecasino.persistence.CasinoPersistenceException;

/**
 *
 * @author Daniel Vela
 */
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
	public void addNewTable(Lobby lobby, Table table) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Table> getAllTables() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Table> getAllTablesInLobby(Lobby lobby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table getTable(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Player> getAllPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayer(String username) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
