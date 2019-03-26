package edu.eci.arsw.ecasino.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lobby {

    private int id;
    private Map<Integer, Table> tables;
    private String name;
    private int numOfTables;

    public Lobby(int id, String name) {
        this.id = id;
        this.name = name;
        tables = new HashMap<Integer, Table>();
        this.numOfTables = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getNumOfTables() {
        return numOfTables;
    }

    public void setNumOfTables() {
        this.numOfTables = this.tables.size();
    }

    public Table getTable(int id) {
        return tables.get(id);
    }

    public void addTable(Table table) {
        tables.put(table.getId(), table);
        setNumOfTables();
    }

}
