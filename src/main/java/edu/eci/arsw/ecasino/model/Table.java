package edu.eci.arsw.ecasino.model;

import java.util.Set;

import edu.eci.arsw.ecasino.model.game.Game;

public class Table {

    private int id;
    private String nametable;
    private Game game;
    private Set<Player> players;

    public Table(int id, String nametable, Game game) {
        this.id = id;
        this.nametable = nametable;
        this.game = game;
    }

    public String getNameTable() {
        return nametable;
    }

    public void setNameTable(String nametable) {
        this.nametable = nametable;
    }

    public Table(Game game) {
        setGame(game);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Set<Player> getPlayers() {
        return players;
    }
    
    public void updateTable(Table table) {
    	
    }

}
