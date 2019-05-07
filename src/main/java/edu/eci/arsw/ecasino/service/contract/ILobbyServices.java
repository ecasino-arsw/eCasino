package edu.eci.arsw.ecasino.service.contract;

import org.springframework.stereotype.Service;

import edu.eci.arsw.ecasino.model.Lobby;

@Service
public interface ILobbyServices {
	
	Iterable<Lobby> list();

	Lobby create(Lobby lobby);

	Lobby get(Long id);
        
	Lobby get(String nameGame);

	void update(Lobby lobby);

	void delete(Lobby lobby);

}
