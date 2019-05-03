package edu.eci.arsw.ecasino.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lobby {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	protected Lobby() {
	}

	public Lobby(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.format("Lobby %d", id);
	}

}
