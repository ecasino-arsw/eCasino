package edu.eci.arsw.eCasino.service.contract;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.eCasino.model.Player;

@Service
public interface IPlayerServices {
	
	List<Player> list();
	
	Player create(Player player);
	
	Player get(Integer id);

	Player get(String username);
	
	void update(Player player);
	
	void delete(Player player);

}
