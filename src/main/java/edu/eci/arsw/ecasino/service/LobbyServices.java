package edu.eci.arsw.ecasino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.eci.arsw.ecasino.model.Lobby;
import edu.eci.arsw.ecasino.persistence.repository.LobbyRepository;
import edu.eci.arsw.ecasino.service.contract.ILobbyServices;

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
            System.out.println("verificando..");
		if (null == lobby.getNameGame()){
                     System.out.println("no valido");
			throw new RuntimeException("Invalid Game.");
                }
                       
		else if (lobbyRepository.getLobbyByNameGame(lobby.getNameGame())!= null){
                        System.out.println("country ya puesot" + lobby.getNameGame());
			throw new RuntimeException("The lobby exists.");
                        }
                        
		else
                        System.out.println("guardando..");
			lobbyRepository.save(lobby);
		return lobby;
	}

	@Override
	public Lobby get(Long id) {
		return lobbyRepository.findById(id).get();
	}

	@Override
	public void update(Lobby lobby) {
		lobbyRepository.save(lobby);
	}

	@Override
	public void delete(Lobby lobby) {
		lobbyRepository.delete(lobby);
	}

        @Override
        public Lobby get(String nameGame) {
            return lobbyRepository.getLobbyByNameGame(nameGame);
        }

}
