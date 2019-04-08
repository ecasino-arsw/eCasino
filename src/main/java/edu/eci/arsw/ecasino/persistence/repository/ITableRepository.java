package edu.eci.arsw.eCasino.persistence.repository;

import java.util.List;

import edu.eci.arsw.eCasino.model.Table;

public interface ITableRepository extends DAO<Table, Integer> {
	
	List<Table> findAll(Integer lobbyId);
	
	Table find(Integer lobbyId, Integer id);

}
