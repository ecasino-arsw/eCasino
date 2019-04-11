package edu.eci.arsw.eCasino.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import edu.eci.arsw.eCasino.model.GameTable;

public interface GameTableRepository extends CrudRepository<GameTable, Long> {
	
	Iterable<GameTable> findAllByLobbyId(Long lobbyId);
	
	GameTable findOneByLobbyId(Long lobbyId, Long id);

}
