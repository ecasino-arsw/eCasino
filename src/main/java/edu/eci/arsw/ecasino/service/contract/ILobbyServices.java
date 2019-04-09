package edu.eci.arsw.eCasino.service.contract;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.eCasino.model.Lobby;

@Service
public interface ILobbyServices {
	
	List<Lobby> list();

	Lobby create(Lobby lobby);

	Lobby get(Integer id);

	void update(Lobby lobby);

	void delete(Lobby lobby);

}
