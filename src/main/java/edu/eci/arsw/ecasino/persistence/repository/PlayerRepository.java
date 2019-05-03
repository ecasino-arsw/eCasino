package edu.eci.arsw.ecasino.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.ecasino.model.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
	
	Player getPlayerByUsername(String username);

}
