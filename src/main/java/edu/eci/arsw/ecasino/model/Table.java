package edu.eci.arsw.eCasino.model;

import edu.eci.arsw.eCasino.model.game.interf.IGame;
import edu.eci.arsw.ecasino.model.Player;
import java.io.Serializable;
import java.util.List;

public class Table implements Serializable {

    private Integer id;
    private Integer lobbyId;
    private String name;
    private List<Player> players;
    private double stakes;
    private IGame game;

    public Table(Integer id, Integer lobbyId, String name, List<Player> players, double stakes, IGame game) {
        this.id = id;
        this.lobbyId = lobbyId;
        this.name = name;
        this.players = players;
        this.stakes = stakes;
        this.game = game;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(Integer lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public double getStakes() {
        return stakes;
    }

    public void setStakes(double stakes) {
        this.stakes = stakes;
    }

    public IGame getGame() {
        return game;
    }

    public void setGame(IGame game) {
        this.game = game;
    }
    
    
    

}
