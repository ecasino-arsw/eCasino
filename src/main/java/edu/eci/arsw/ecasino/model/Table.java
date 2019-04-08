package edu.eci.arsw.eCasino.model;

import java.io.Serializable;

public class Table implements Serializable {

	private Integer id;
	private Integer lobbyId;
	
	public Table() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLobbyId() {
		return lobbyId;
	}

	public void setLobbyId(Integer lobbyId) {
		this.lobbyId = lobbyId;
	}

}
