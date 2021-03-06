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
        private int capacity;
        private int currentPlayers;
	
	protected GameTable() {
	}
	
	public GameTable( Long lobbyId, String name, double stakes, int capacity) {
		
		this.lobbyId = lobbyId;
		this.name = name;
		this.stakes = stakes;
                this.capacity = capacity;
                this.currentPlayers = 0;
                
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

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public int getCurrentPlayers() {
            return currentPlayers;
        }

        public void setCurrentPlayers(int currentPlayers) {
            this.currentPlayers = currentPlayers;
        }
        
        
        

        
        
        
        
}
