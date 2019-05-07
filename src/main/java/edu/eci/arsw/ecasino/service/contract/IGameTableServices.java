package edu.eci.arsw.ecasino.service.contract;

import org.springframework.stereotype.Service;

import edu.eci.arsw.ecasino.model.GameTable;

@Service
public interface IGameTableServices {
	
	Iterable<GameTable> list();
	
	Iterable<GameTable> list(Long lobbyId);

	GameTable create(GameTable table);

	GameTable get(Long id);
	
	GameTable get(Long lobbyId, Long id);
	
        GameTable get(String name);
        
	void update(GameTable table);

	void delete(GameTable table);

}
