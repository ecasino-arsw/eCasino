package edu.eci.arsw.ecasino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.eci.arsw.ecasino.model.Player;
import edu.eci.arsw.ecasino.persistence.repository.PlayerRepository;
import edu.eci.arsw.ecasino.service.contract.IPlayerServices;

@Component
public class PlayerServices implements IPlayerServices {
	
	@Autowired
	PlayerRepository playerRepository;

	@Override
	public Iterable<Player> list() {
		return playerRepository.findAll();
	}

	@Override
	public Player create(Player player) {
		if (null == player.getId())
			throw new RuntimeException("Invalid ID.");
		else if (playerRepository.findById(player.getId()) != null)
			throw new RuntimeException("The player exists.");
		else
			playerRepository.save(player);
		return player;
	}

	@Override
	public Player get(Long id) {
		return playerRepository.findById(id).get();
	}

	@Override
	public Player get(String username) {
		return playerRepository.getPlayerByUsername(username);
	}

	@Override
	public void update(Player player) {
		playerRepository.save(player);
	}

	@Override
	public void delete(Player player) {
		playerRepository.delete(player);
	}

}
