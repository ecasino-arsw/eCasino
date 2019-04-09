package edu.eci.arsw.eCasino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.eci.arsw.eCasino.model.Lobby;
import edu.eci.arsw.eCasino.persistence.repository.ILobbyRepository;
import edu.eci.arsw.eCasino.service.contract.ILobbyServices;

@Component
public class LobbyServices implements ILobbyServices {
	
	@Autowired
	@Qualifier("LobbyMemoryRepository")
	ILobbyRepository lobbyRepository;

	@Override
	public List<Lobby> list() {
		return lobbyRepository.findAll();
	}

	@Override
	public Lobby create(Lobby lobby) {
		if (null == lobby.getId())
			throw new RuntimeException("Invalid ID.");
		else if (lobbyRepository.find(lobby.getId()) != null)
			throw new RuntimeException("The lobby exists.");
		else
			lobbyRepository.save(lobby);
		return lobby;
	}

	@Override
	public Lobby get(Integer id) {
		return lobbyRepository.find(id);
	}

	@Override
	public void update(Lobby lobby) {
		lobbyRepository.update(lobby);
	}

	@Override
	public void delete(Lobby lobby) {
		lobbyRepository.delete(lobby);
	}

}
