package edu.eci.arsw.ecasino.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import edu.eci.arsw.ecasino.model.GameTable;

public interface GameTableRepository extends CrudRepository<GameTable, Long> {
	
	Iterable<GameTable> findAllByLobbyId(Long lobbyId);
	
	GameTable findByIdAndLobbyId(Long id, Long lobbyId);

}
