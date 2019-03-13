package edu.eci.arsw.ecasino.model;

import java.util.ArrayList;

public class Lobby {
	
	private ArrayList<Table> tables;
	
	public Lobby() {
		tables = new ArrayList<>();
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}

}
