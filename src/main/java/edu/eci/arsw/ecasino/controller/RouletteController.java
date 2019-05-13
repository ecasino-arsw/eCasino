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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author villate
 */
@Controller
public class RouletteController {

    @Autowired
    IPlayerServices playerServices;

    @MessageMapping("/newgame{channel}/user")
    @SendTo("/topic/newgame{channel}")
    public RouletteResponse connectRoulette(@DestinationVariable String channel, Roulette roulette, String username) {
        username=username.substring(2,username.length()-2);
               
        List<String> items = Arrays.asList(username.split("\\s*:\\s*"));
        username = items.get(1).substring(1);
        Player player = playerServices.get(username);
        roulette.setPlayerInList(player);
        //Player player = playerServices.get(data.getUsername());
        return new RouletteResponse(username);
        

    }
    @MessageMapping("/newgame{channel}/content")
    @SendTo("/topic/newgame{channel}")
    public RouletteResponse getNumberWinner(@DestinationVariable String channel, Roulette roulette, DataUser data) {

        
        /*System.err.println("jugadores: " + roulette.getListPlayers().size());
        System.err.println("id user: "+player.getId());

        System.out.println("contenido: " + data.getUsername());
        System.out.println("contenido: " + data.getChannel());
        System.out.println("contenido: " + data.getMoney());
        System.out.println("contenido: " + data.getSelectNumbers());
        System.out.println("contenido: " + data.getTimesNumbers());*/
        Player player = playerServices.get(data.getUsername());
        int win = roulette.turnRoulette();
        String numbersOld = roulette.getListNumbersOld();
        String dozen = roulette.getDozen();
        
        return new RouletteResponse(win, dozen, numbersOld, data.getSelectNumbers(), data.getTimesNumbers(),data.getUsername());
    }

}

/*@MessageMapping("/newgame{channel}/content")
    @SendTo("/topic/newgame{channel}")
    public RouletteResponse getContent(@DestinationVariable String channel, Roulette content) {
        
        return Roulette();
    }*/
