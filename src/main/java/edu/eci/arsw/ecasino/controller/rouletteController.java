/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.ecasino.controller;


import edu.eci.arsw.ecasino.model.game.roulette.Roulette;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 *
 * @author 2106991
 */
@Controller
public class rouletteController {
    
    @Autowired
    SimpMessagingTemplate msgt;

    private Integer channel;
    private HashMap<Integer, ArrayList<Roulette>> roulettes = new HashMap<>();
    //private Roulette roulette = new Roulette();
    private ArrayList<Integer> listNumbers = new ArrayList<>();

    @MessageMapping("/topic/game{channel}")
    public synchronized void handlePointEvent(ArrayList<Integer> listNumbers, @DestinationVariable String chan) throws Exception {
        System.out.println("Nuevo punto recibido en el servidor!:" + listNumbers);
        int numWin=-1;
        channel = Integer.parseInt(chan);
        if (!roulettes.containsKey(channel)) {
            ArrayList<Roulette> arrayToAdd = new ArrayList<>();
            roulettes.put(channel, arrayToAdd);
            numWin = roulettes.get(channel).get(0).giraRuleta();
        }
        //roulettes.setTableID(channel);
        
        
        
        if(listNumbers.contains(numWin)){
            msgt.convertAndSend("/topic/game{channel}", numWin);
        }
        //listNumbers.).add(listNumbers);
        
        else{
            msgt.convertAndSend("/topic/game{channel}" , roulettes.get(channel));
            roulettes.get(channel).clear();
        }
        
        
        
    }

}
