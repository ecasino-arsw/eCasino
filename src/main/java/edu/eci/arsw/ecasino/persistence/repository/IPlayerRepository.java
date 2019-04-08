package edu.eci.arsw.eCasino.persistence.repository;

import org.springframework.stereotype.Repository;

import edu.eci.arsw.eCasino.model.Player;

@Repository
public interface IPlayerRepository extends DAO<Player, Integer> {
	
	Player getPlayerByUsername(String username);

}
