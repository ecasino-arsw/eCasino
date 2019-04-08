package edu.eci.arsw.eCasino.persistence;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.eci.arsw.eCasino.model.Player;
import edu.eci.arsw.eCasino.persistence.repository.IPlayerRepository;

@Component
@Qualifier("PlayerMemoryRepository")
public class PlayerMemoryRepository implements IPlayerRepository {
	
	public static List<Player> playersContainer;

	public static List<Player> getContainer() {
		if (PlayerMemoryRepository.playersContainer == null)
			PlayerMemoryRepository.playersContainer = new ArrayList<>();
		return PlayerMemoryRepository.playersContainer;
	}

	@Override
	public List<Player> findAll() {
		return PlayerMemoryRepository.getContainer();
	}

	@Override
	public Player find(Integer id) {
		Optional<Player> answer = PlayerMemoryRepository.getContainer().stream().filter(p -> id.equals(p.getId()))
				.findFirst();
		return answer.isPresent() ? answer.get() : null;
	}

	@Override
	public Player getPlayerByUsername(String username) {
		return PlayerMemoryRepository.getContainer().stream().filter(p -> username.equals(p.getUsername())).findFirst().get();
	}

	@Override
	public Integer save(Player entity) {
		PlayerMemoryRepository.getContainer().add(entity);
		return entity.getId();
	}

	@Override
	public void update(Player entity) {
		PlayerMemoryRepository.playersContainer = PlayerMemoryRepository.getContainer().stream()
				.map(p -> p.getId().equals(entity.getId()) ? entity : p).collect(toList());
	}

	@Override
	public void delete(Player o) {
		PlayerMemoryRepository.playersContainer = PlayerMemoryRepository.getContainer().stream()
				.filter(p -> !p.getId().equals(o.getId())).collect(toList());
	}

	@Override
	public void remove(Long id) {
		PlayerMemoryRepository.playersContainer = PlayerMemoryRepository.getContainer().stream()
				.filter(p -> !p.getId().equals(id)).collect(toList());
	}

}
