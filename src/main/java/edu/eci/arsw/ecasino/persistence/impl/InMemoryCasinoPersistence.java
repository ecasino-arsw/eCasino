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
		Lobby lobby1 = new Lobby(1, "BlackJack");
		Lobby lobby2 = new Lobby(2, "HoldEm");
		addNewLobby(lobby1);
		addNewLobby(lobby2);
		
		Player player1 = new Player("DCifuentes");
		Player player2 = new Player("DVela");
		Player player3 = new Player("Villate");
		
		
		ArrayList<Player> blackJackPlayers= new ArrayList<>();
		blackJackPlayers.add(player1);
		blackJackPlayers.add(player2);
		Table table1= new Table(1,"Bogota",new BlackJack(blackJackPlayers));
                
		ArrayList<Player> blackJackPlayers2= new ArrayList<>();
		blackJackPlayers2.add(player1);
		blackJackPlayers2.add(player3);
		Table table3= new Table(3,"Vegas",new BlackJack(blackJackPlayers2));
                
                
		ArrayList<Player> HoldEmPlayers= new ArrayList<>();
		HoldEmPlayers.add(player1);
		HoldEmPlayers.add(player2);
		HoldEmPlayers.add(player3);
		Table table2= new Table(2,"Paris",new BlackJack(HoldEmPlayers));
                
		
		addNewTable(lobby1,table1);
		addNewTable(lobby2,table2);
		addNewTable(lobby1,table3);
                
		
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
		// TODO Auto-generated method stub
		throw new CasinoPersistenceException("Method not supported yet.");
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
		// TODO Auto-generated method stub
		throw new CasinoPersistenceException("Method not supported yet.");
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

	@Override
	public void updatePlayer(Player player) throws CasinoPersistenceException {
		// TODO Auto-generated method stub
		throw new CasinoPersistenceException("Method not supported yet.");
	}
    
}
