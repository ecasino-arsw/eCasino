package edu.eci.arsw.eCasino.service.contract;

import org.springframework.stereotype.Service;

import edu.eci.arsw.eCasino.model.GameTable;

@Service
public interface IGameTableServices {
	
	Iterable<GameTable> list();
	
	Iterable<GameTable> list(Long lobbyId);

	GameTable create(GameTable table);

	GameTable get(Long id);
	
	GameTable get(Long lobbyId, Long id);

	void update(GameTable table);

	void delete(GameTable table);

}
