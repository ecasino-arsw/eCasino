package edu.eci.arsw.eCasino.model;

import java.io.Serializable;

public class Lobby implements Serializable {

	private Integer id;
	
	public Lobby () {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
