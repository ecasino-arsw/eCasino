/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.ecasino.controller;

import edu.eci.arsw.ecasino.model.Player;
import edu.eci.arsw.ecasino.model.game.roulette.DataUser;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import edu.eci.arsw.ecasino.model.game.roulette.Roulette;
import edu.eci.arsw.ecasino.model.game.roulette.RouletteResponse;
import edu.eci.arsw.ecasino.service.contract.IPlayerServices;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 *
 * @author villate
 */
@Controller
public class RouletteController {

    @Autowired
    IPlayerServices playerServices;

    @Autowired
    SimpMessagingTemplate msgt;

    private int tableId;
    private String user;
    private HashMap<Integer, Roulette> roulette = new HashMap<>();
    
    @MessageMapping("/newgame{channel}/time")
    //@SendTo("/topic/newgame{channel}")
    public synchronized void getTime(@DestinationVariable String channel) {
        tableId = Integer.parseInt(channel);
        Timer timer = new Timer();
        
        Roulette current = roulette.get(tableId);

        if (!current.isActive()) {
            TimerTask timeTurn = new TimerTask() {
                int cont = 0;
                @Override
                public void run() {
                    if (cont<=20) {
                        //System.out.println("falta: " + cont);
                        msgt.convertAndSend("/topic/newgame" + channel,new RouletteResponse(cont));
                        current.setTimeRulette(cont);
                        cont+=1;
                        if(cont==1){current.turnRoulette();}
                    }else{
                        
                        cont=0;
                    }if(!current.verifyPlayersActives()){
                        System.out.println("sin jugadores");
                        timer.cancel();
                    }
                    
                }
            };
            current.setActive(true);
            timer.schedule(timeTurn, 0, 1000);
        }

    }
    
    @MessageMapping("/newgame{channel}/disconnect")
    //@SendTo("/topic/newgame{channel}")
    public synchronized void disconnectUser(@DestinationVariable int channel, String username) {
        System.out.println("entro a eleiminar");
        tableId = channel;
        List<String> items = Arrays.asList(username.split("\\s*:\\s*"));
        username = items.get(1);
        username = username.substring(1, username.length() - 2);
        
        Player player = playerServices.get(username);
        System.out.println("player"+player.getFullName());
        Roulette current = roulette.get(tableId);
        System.out.println("");
        if (current.isActive()) {
            current.deletePlayer(player);
            System.out.println("ok, delete playaer"+player.toString());
        }

    }

    @MessageMapping("/newgame{channel}/user")
    @SendTo("/topic/newgame{channel}")
    public RouletteResponse connectRoulette(@DestinationVariable String channel, String username) {
        tableId = Integer.parseInt(channel);
        List<String> items = Arrays.asList(username.split("\\s*:\\s*"));
        username = items.get(1);
        username = username.substring(1, username.length() - 2);
        

        Player player = playerServices.get(username);
        
        if (!roulette.containsKey(tableId)) {
            Roulette addRoulette = (new Roulette(tableId, player));
            roulette.put(tableId, addRoulette);
        }

        Roulette current = roulette.get(tableId);
        int time = current.getTimeRulette();
       

        //roulette.setPlayerInList(player);
        //Player player = playerServices.get(data.getUsername());
        return new RouletteResponse(username, time);

    }

    @MessageMapping("/newgame{channel}/content")
    @SendTo("/topic/newgame{channel}")
    public RouletteResponse getNumberWinner(@DestinationVariable String channel, DataUser data) {
        tableId = Integer.parseInt(channel);
        
        Roulette current = roulette.get(tableId);
        /*System.err.println("jugadores: " + roulette.getListPlayers().size());
        System.err.println("id user: "+player.getId());

        System.out.println("contenido: " + data.getUsername());
        System.out.println("contenido: " + data.getChannel());
        System.out.println("contenido: " + data.getMoney());
        System.out.println("contenido: " + data.getSelectNumbers());
        System.out.println("contenido: " + data.getTimesNumbers());*/
        
        
        int win = current.getNumberWin();
        String numbersOld = current.getListNumbersOld();
        String dozen = current.getDozen();

        return new RouletteResponse(win, dozen, numbersOld, data.getSelectNumbers(), data.getTimesNumbers(), data.getUsername());
    }

}

/*@MessageMapping("/newgame{channel}/content")
    @SendTo("/topic/newgame{channel}")
    public RouletteResponse getContent(@DestinationVariable String channel, Roulette content) {
        
        return Roulette();
    }*/
