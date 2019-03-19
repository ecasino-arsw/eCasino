package edu.eci.arsw.ecasino.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lobby {
	
	private int id;
	private Map<Integer,Table> tables;
	
	public Lobby(int id) {
		this.id = id;
		tables = new HashMap<Integer,Table>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Integer, Table> getTables() {
		return tables;
	}

	public void setTables(Map<Integer, Table> tables) {
		this.tables = tables;
	}
	
	public Table getTable(int id) {
		return tables.get(id);
	}
	
	public void addTable(Table table) {
		tables.put(table.getId(), table);
	}

}
