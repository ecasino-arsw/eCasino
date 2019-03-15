package edu.eci.arsw.ecasino.model;

import java.util.ArrayList;

public class Lobby {
	
	private int id;
	private ArrayList<Table> tables;
	
	public Lobby() {
		tables = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}

}
