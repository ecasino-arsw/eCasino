package edu.eci.arsw.eCasino.service.contract;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.eCasino.model.Table;

@Service
public interface ITableServices {
	
	List<Table> list();
	
	List<Table> list(Integer lobbyId);

	Table create(Table table);

	Table get(Integer id);
	
	Table get(Integer lobbyId, Integer id);

	void update(Table table);

	void delete(Table table);

}
