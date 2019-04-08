package edu.eci.arsw.eCasino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.eci.arsw.eCasino.model.Table;
import edu.eci.arsw.eCasino.persistence.repository.ILobbyRepository;
import edu.eci.arsw.eCasino.persistence.repository.ITableRepository;
import edu.eci.arsw.eCasino.service.contract.ITableServices;

@Component
public class TableServices implements ITableServices {
	
	@Autowired
	@Qualifier("TableMemoryRepository")
	ITableRepository tableRepository;
	
	@Autowired
	@Qualifier("LobbyMemoryRepository")
	ILobbyRepository lobbyRepository;

	@Override
	public List<Table> list() {
		return tableRepository.findAll();
	}
	
	@Override
	public List<Table> list(Integer lobbyId) {
		return tableRepository.findAll(lobbyId);
	}

	@Override
	public Table create(Table table) {
		if (null == table.getId())
			throw new RuntimeException("Invalid ID.");
		else if (null == table.getLobbyId())
			throw new RuntimeException("Invalid lobby ID.");
		else if (lobbyRepository.find(table.getLobbyId()) == null)
			throw new RuntimeException("The lobby doesn't exist.");
		else if (tableRepository.find(table.getLobbyId(), table.getId()) != null)
			throw new RuntimeException("The table exists in the lobby.");
		else
			tableRepository.save(table);
		return table;
	}

	@Override
	public Table get(Integer id) {
		return tableRepository.find(id);
	}

	@Override
	public Table get(Integer lobbyId, Integer id) {
		return tableRepository.find(lobbyId, id);
	}

	@Override
	public void update(Table table) {
		tableRepository.update(table);
	}

	@Override
	public void delete(Table table) {
		tableRepository.delete(table);
	}

}
