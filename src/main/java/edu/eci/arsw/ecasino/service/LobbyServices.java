package edu.eci.arsw.eCasino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.eci.arsw.eCasino.model.Lobby;
import edu.eci.arsw.eCasino.persistence.repository.LobbyRepository;
import edu.eci.arsw.eCasino.service.contract.ILobbyServices;

@Component
public class LobbyServices implements ILobbyServices {
	
	@Autowired
	LobbyRepository lobbyRepository;

	@Override
	public Iterable<Lobby> list() {
		return lobbyRepository.findAll();
	}

	@Override
	public Lobby create(Lobby lobby) {
		if (null == lobby.getId())
			throw new RuntimeException("Invalid ID.");
		else if (lobbyRepository.findOne(lobby.getId()) != null)
			throw new RuntimeException("The lobby exists.");
		else
			lobbyRepository.save(lobby);
		return lobby;
	}

	@Override
	public Lobby get(Long id) {
		return lobbyRepository.findOne(id);
	}

	@Override
	public void update(Lobby lobby) {
		lobbyRepository.save(lobby);
	}

	@Override
	public void delete(Lobby lobby) {
		lobbyRepository.delete(lobby);
	}

}
