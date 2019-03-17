/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.ecasino.persistence.impl;

import java.util.List;

import edu.eci.arsw.ecasino.model.Lobby;
import edu.eci.arsw.ecasino.model.Player;
import edu.eci.arsw.ecasino.model.Table;
import edu.eci.arsw.ecasino.persistence.CasinoPersistence;

/**
 *
 * @author villate
 */
public class InMemoryCasinoPersistence implements CasinoPersistence{

	@Override
	public void addNewLobby() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Lobby> getAllLobbies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getLobby(int id) {
		// TODO Auto-generated method stub
		
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
