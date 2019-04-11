package edu.eci.arsw.eCasino.service.contract;

import org.springframework.stereotype.Service;

import edu.eci.arsw.eCasino.model.Lobby;

@Service
public interface ILobbyServices {
	
	Iterable<Lobby> list();

	Lobby create(Lobby lobby);

	Lobby get(Long id);

	void update(Lobby lobby);

	void delete(Lobby lobby);

}
