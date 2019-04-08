package edu.eci.arsw.eCasino.persistence;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.eci.arsw.eCasino.model.Table;
import edu.eci.arsw.eCasino.persistence.repository.ITableRepository;

@Component
@Qualifier("TableMemoryRepository")
public class TableMemoryRepository implements ITableRepository {

	public static List<Table> tablesContainer;

	public static List<Table> getContainer() {
		if (TableMemoryRepository.tablesContainer == null)
			TableMemoryRepository.tablesContainer = new ArrayList<>();
		return TableMemoryRepository.tablesContainer;
	}

	@Override
	public List<Table> findAll() {
		return TableMemoryRepository.getContainer();
	}

	@Override
	public List<Table> findAll(Integer lobbyId) {
		List<Table> answer = TableMemoryRepository.getContainer().stream().filter(t -> lobbyId.equals(t.getLobbyId()))
				.collect(toList());
		return answer;
	}

	@Override
	public Table find(Integer lobbyId, Integer id) {
		Optional<Table> answer = TableMemoryRepository.getContainer().stream()
				.filter(t -> id.equals(t.getId()) && lobbyId.equals(t.getLobbyId())).findFirst();
		return answer.isPresent() ? answer.get() : null;
	}

	@Override
	public Table find(Integer id) {
		Optional<Table> answer = TableMemoryRepository.getContainer().stream().filter(t -> id.equals(t.getId()))
				.findFirst();
		return answer.isPresent() ? answer.get() : null;
	}

	@Override
	public Integer save(Table entity) {
		TableMemoryRepository.getContainer().add(entity);
		return entity.getId();
	}

	@Override
	public void update(Table entity) {
		TableMemoryRepository.tablesContainer = TableMemoryRepository.getContainer().stream()
				.map(t -> t.getId().equals(entity.getId()) ? entity : t).collect(toList());
	}

	@Override
	public void delete(Table o) {
		TableMemoryRepository.tablesContainer = TableMemoryRepository.getContainer().stream()
				.filter(t -> !t.getId().equals(o.getId())).collect(toList());
	}

	@Override
	public void remove(Long id) {
		TableMemoryRepository.tablesContainer = TableMemoryRepository.getContainer().stream()
				.filter(t -> !t.getId().equals(id)).collect(toList());
	}

}
