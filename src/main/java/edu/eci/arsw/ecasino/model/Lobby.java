package edu.eci.arsw.eCasino.model;

import java.io.Serializable;

public class Lobby implements Serializable {

    private Integer id;
    private String nameGame;

    public Lobby() {
    }
    
    

    public Lobby(Integer id, String nameGame) {
        this.id = id;
        this.nameGame = nameGame;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

}
