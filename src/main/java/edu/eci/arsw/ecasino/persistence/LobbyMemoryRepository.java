package edu.eci.arsw.eCasino.persistence;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.eci.arsw.ecasino.model.Lobby;
import edu.eci.arsw.eCasino.persistence.repository.ILobbyRepository;

@Component
@Qualifier("LobbyMemoryRepository")
public class LobbyMemoryRepository implements ILobbyRepository {

	public static List<Lobby> lobbiesContainer;

	public static List<Lobby> getContainer() {
		if (LobbyMemoryRepository.lobbiesContainer == null)
			LobbyMemoryRepository.lobbiesContainer = new ArrayList<>();
		return LobbyMemoryRepository.lobbiesContainer;
	}

	@Override
	public List<Lobby> findAll() {
		return LobbyMemoryRepository.getContainer();
	}

	@Override
	public Lobby find(Integer id) {
		Optional<Lobby> answer = LobbyMemoryRepository.getContainer().stream().filter(l -> id.equals(l.getId()))
				.findFirst();
		return answer.isPresent() ? answer.get() : null;
	}

	@Override
	public Integer save(Lobby entity) {
		LobbyMemoryRepository.getContainer().add(entity);
		return entity.getId();
	}

	@Override
	public void update(Lobby entity) {
		LobbyMemoryRepository.lobbiesContainer = LobbyMemoryRepository.getContainer().stream()
				.map(l -> l.getId().equals(entity.getId()) ? entity : l).collect(toList());
	}

	@Override
	public void delete(Lobby o) {
		LobbyMemoryRepository.lobbiesContainer = LobbyMemoryRepository.getContainer().stream()
				.filter(l -> !l.getId().equals(o.getId())).collect(toList());
	}

	@Override
	public void remove(Long id) {
		LobbyMemoryRepository.lobbiesContainer = LobbyMemoryRepository.getContainer().stream()
				.filter(l -> !l.getId().equals(id)).collect(toList());
	}

}
