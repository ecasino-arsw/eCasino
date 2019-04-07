/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.ecasino.persistence.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import edu.eci.arsw.ecasino.model.Lobby;
import edu.eci.arsw.ecasino.model.Player;
import edu.eci.arsw.ecasino.model.Table;
import edu.eci.arsw.ecasino.model.game.BlackJack;
import edu.eci.arsw.ecasino.model.game.HoldEm;
import edu.eci.arsw.ecasino.persistence.CasinoPersistence;
import edu.eci.arsw.ecasino.persistence.CasinoPersistenceException;

/**
 *
 * @author Daniel Vela
 */
@Component("cps")
public class InMemoryCasinoPersistence implements CasinoPersistence {
	
	private final Map<Integer, Lobby> lobbies = new HashMap<>();
	private final Map<String,Player> players= new HashMap<>();
	
	public InMemoryCasinoPersistence() throws CasinoPersistenceException {
		Lobby lobby1 = new Lobby(1, "Lobby1");
		Lobby lobby2 = new Lobby(2, "Lobby2");
		
		Player player1 = new Player("DCifuentes");
		Player player2 = new Player("DVela");
		Player player3 = new Player("Villate");
		
		Table table1= new Table(1,"Bogota",new HoldEm());
		Table table3= new Table(3,"Vegas",new BlackJack());
		Table table2= new Table(2,"Paris",new BlackJack());
		
		addNewLobby(lobby1);
		addNewLobby(lobby2);
		
		addNewPlayer(player1);
		addNewPlayer(player2);
		addNewPlayer(player3);
		
		addNewTable(lobby1,table1);
		addNewTable(lobby2,table2);
		addNewTable(lobby1,table3);
		
		joinLobby(lobby1,player1);
		joinLobby(lobby1,player2);
		joinLobby(lobby1,player3);
		
		joinTable(table1,player1);
		joinTable(table1,player2);
		joinTable(table2,player3);
                
		
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
	public void updateLobby(Lobby lobby) throws CasinoPersistenceException {
		if(!lobbies.containsKey(lobby.getId())) {
			throw new CasinoPersistenceException("Could not find a lobby with the given id: " + lobby.getId());
		}
		lobbies.get(lobby.getId()).updateLobby(lobby);
		
		
	}

	@Override
	public void addNewTable(Lobby lobby, Table table) throws CasinoPersistenceException {
		lobby.addTable(table);
	}

	@Override
	public List<Table> getAllTables() throws CasinoPersistenceException {
		List<Table> allTables = new ArrayList<>();
		lobbies.entrySet().stream().forEach((entry) -> {
			allTables.addAll(entry.getValue().getTables().values());
		});
		return allTables;
	}

	@Override
	public Map<Integer, Table> getAllTablesInLobby(Lobby lobby) throws CasinoPersistenceException {
		return lobby.getTables();
	}

	@Override
	public Table getTable(Lobby lobby, int id) throws CasinoPersistenceException {
		return lobby.getTable(id);
	}

	@Override
	public void updateTable(int lobbyId, Table table) throws CasinoPersistenceException {
		if(!lobbies.containsKey(lobbyId)) {
			throw new CasinoPersistenceException("Could not find a lobby with the given id: " + lobbyId);
		}else if(!lobbies.get(lobbyId).getTables().containsKey(table.getId())) {
			throw new CasinoPersistenceException("Could not find a table with the given id in lobby "+ lobbyId +": " + table.getId());
		}
		lobbies.get(lobbyId).getTable(table.getId()).updateTable(table);
	}

	@Override
	public void addNewPlayer(Player player) throws CasinoPersistenceException {
		if (players.containsKey(player.getUsername())) {
			throw new CasinoPersistenceException("The given username already exists: " + player.getUsername());
		}
		players.put(player.getUsername(), player);
		
	}

	@Override
	public Set<Player> getAllPlayers() throws CasinoPersistenceException {
		return new HashSet<Player>(players.values());
	}

	@Override
	public Player getPlayer(String username) throws CasinoPersistenceException {
		return players.get(username);
	}

	@Override
	public Set<Player> getAllPlayersInTable(Table table) throws CasinoPersistenceException {
		return table.getPlayers();
	}

	@Override
	public void joinTable(Table table, Player player) {
		player.joinTable(table);		
	}

	@Override
	public void updatePlayer(Player player) throws CasinoPersistenceException {
		if(!players.containsKey(player.getUsername())) {
			throw new CasinoPersistenceException("Could not find a player with the given username: " + player.getUsername());
		}
		players.get(player.getUsername()).updatePlayer(player);
	}

	@Override
	public void joinLobby(Lobby lobby, Player player) {
		
		player.joinLobby(lobby);
	}
    
}
