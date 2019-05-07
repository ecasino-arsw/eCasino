package edu.eci.arsw.ecasino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.eci.arsw.ecasino.model.GameTable;
import edu.eci.arsw.ecasino.persistence.repository.GameTableRepository;
import edu.eci.arsw.ecasino.persistence.repository.LobbyRepository;
import edu.eci.arsw.ecasino.service.contract.IGameTableServices;

@Component
public class GameTableServices implements IGameTableServices {
	
	@Autowired
	GameTableRepository gameTableRepository;
	
	@Autowired
	LobbyRepository lobbyRepository;

	@Override
	public Iterable<GameTable> list() {
		return gameTableRepository.findAll();
	}
	
	@Override
	public Iterable<GameTable> list(Long lobbyId) {
		return gameTableRepository.findAllByLobbyId(lobbyId);
	}

	@Override
	public GameTable create(GameTable gameTable) {
		if (null == gameTable.getName())
			throw new RuntimeException("Invalid Name.");
		else if (null == gameTable.getLobbyId())
			throw new RuntimeException("Invalid lobby ID.");
		else if (lobbyRepository.findById(gameTable.getLobbyId()) == null)
			throw new RuntimeException("The lobby doesn't exist.");
		else if (gameTableRepository.findByName(gameTable.getName()) != null)
			throw new RuntimeException("The table exist.");
		else
			gameTableRepository.save(gameTable);
		return gameTable;
	}

	@Override
	public GameTable get(Long id) {
		return gameTableRepository.findById(id).get();
	}

	@Override
	public GameTable get(Long lobbyId, Long id) {
		return gameTableRepository.findByIdAndLobbyId(id, lobbyId);
	}

	@Override
	public void update(GameTable gameTable) {
		gameTableRepository.save(gameTable);
	}

	@Override
	public void delete(GameTable gameTable) {
		gameTableRepository.delete(gameTable);
	}

        @Override
        public GameTable get(String name) {
            return gameTableRepository.findByName(name);
        }

}
