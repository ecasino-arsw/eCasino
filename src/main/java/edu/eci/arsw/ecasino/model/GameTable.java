package edu.eci.arsw.ecasino.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GameTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long lobbyId;
	
	protected GameTable() {
	}
	
	public GameTable(Long id, Long lobbyId) {
		this.id = id;
		this.lobbyId = lobbyId;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLobbyId() {
		return lobbyId;
	}

	public void setLobbyId(Long lobbyId) {
		this.lobbyId = lobbyId;
	}

}
