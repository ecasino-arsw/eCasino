/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.ecasino.model.game.roulette;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author villate
 */
public class DataUser {

    private String username;
    private int channel;
    private String selectNumbers;
    private String timesNumbers;
    private float money;

    public DataUser() {
    }

    public DataUser(String username, int channel, String selectNumbers , String timesNumbers,float money) {
        this.username = username;
        this.channel = channel;
        this.selectNumbers = selectNumbers;
        this.timesNumbers = timesNumbers;
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public List<String> getSelectNumbers() {
        List<String> items = Arrays.asList(selectNumbers.split("\\s*,\\s*"));
        return items;
    }

    public void setSelectNumbers(String selectNumbers) {
        this.selectNumbers = selectNumbers;
    }

    public List<String> getTimesNumbers() {
        List<String> items = Arrays.asList(timesNumbers.split("\\s*,\\s*"));
        return items;
    }

    public void setTimesNumbers(String timesNumbers) {
        this.timesNumbers = timesNumbers;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    
    
    
}

