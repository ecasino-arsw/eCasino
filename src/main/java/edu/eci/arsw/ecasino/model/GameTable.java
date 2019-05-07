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
        private String name;
        private double stakes;
	
	protected GameTable() {
	}
	
	public GameTable( Long lobbyId, String name, double stakes, int capacity) {
		
		this.lobbyId = lobbyId;
		this.name = name;
		this.stakes = stakes;
                
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getStakes() {
            return stakes;
        }

        public void setStakes(double stakes) {
            this.stakes = stakes;
        }

        
        
        
        
}
