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
		Lobby lobby = new Lobby(1);
		addNewLobby(lobby);
		
		Player player1 = new Player("DCifuentes");
		Player player2 = new Player("DVela");
		Player player3 = new Player("Villate");
		
		
		ArrayList<Player> blackJackPlayers= new ArrayList<>();
		blackJackPlayers.add(player1);
		blackJackPlayers.add(player2);
		Table table1= new Table(1,new BlackJack(blackJackPlayers));
		
		ArrayList<Player> HoldEmPlayers= new ArrayList<>();
		HoldEmPlayers.add(player1);
		HoldEmPlayers.add(player2);
		HoldEmPlayers.add(player3);
		Table table2= new Table(2,new BlackJack(HoldEmPlayers));
		
		addNewTable(lobby,table1);
		addNewTable(lobby,table2);
		
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
	public void addPlayerToTable(Table table, Player player) {
		table.addPlayer(player);
		
	}
    
}
