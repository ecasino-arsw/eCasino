package edu.eci.arsw.ecasino.service.contract;

import org.springframework.stereotype.Service;

import edu.eci.arsw.ecasino.model.Player;

@Service
public interface IPlayerServices {
	
	Iterable<Player> list();
	
	Player create(Player player);
	
	Player get(Long id);

	Player get(String username);
	
	void update(Player player);
	
	void delete(Player player);

}
