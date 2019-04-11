package edu.eci.arsw.eCasino.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.eCasino.model.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
	
	Player getPlayerByUsername(String username);

}
