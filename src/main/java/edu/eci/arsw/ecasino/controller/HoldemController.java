package edu.eci.arsw.ecasino.controller;


import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import edu.eci.arsw.ecasino.model.game.texasholdem.Holdem;
import edu.eci.arsw.ecasino.model.game.texasholdem.HoldemPlayer;
import edu.eci.arsw.ecasino.model.game.texasholdem.HoldemResponse;
import edu.eci.arsw.ecasino.model.game.texasholdem.TableType;
import edu.eci.arsw.ecasino.service.contract.IPlayerServices;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class HoldemController {

    @Autowired
    IPlayerServices playerServices;

    @Autowired
    SimpMessagingTemplate msgt;

    private int tableId;
    private String user;
    private String money;
    private String bigBlind;
    private HashMap<Integer, Holdem> holdem = new HashMap<>();

    @MessageMapping("/gameholdem{channel}")
    public synchronized void connectHoldem(@DestinationVariable String channel, int bigBlind) {
        tableId = Integer.parseInt(channel);
                
        if (!holdem.containsKey(tableId)) {
            Holdem addHoldem = (new Holdem(TableType.FIXED_LIMIT, bigBlind));
            holdem.put(tableId, addHoldem);
        }
        
        System.out.println("conreccted");
    }
    
    @MessageMapping("/gameholdem{channel}/user")
    @SendTo("/topic/gameholdem{channel}")
    public HoldemResponse updateTable(@DestinationVariable String channel, String data) {
        tableId = Integer.parseInt(channel);
        data = data.replace("{","");
        data = data.replace("}","");
        data = data.replace(":",",");
        data = data.replace("'","");
        List<String> items = Arrays.asList(data.split("\\s*,\\s*"));
        //usernam
        user = items.get(1).substring(1,items.get(1).length()-1);
        //money
        money = items.get(3).substring(1,items.get(3).length()-1);
        //bigBlind
        bigBlind = items.get(5).substring(1,items.get(5).length()-1);
                
        if (!holdem.containsKey(tableId)) {
            Holdem addHoldem = (new Holdem(TableType.FIXED_LIMIT, Integer.parseInt(bigBlind)));
            holdem.put(tableId, addHoldem);
        }
        
        System.out.println("conreccted");
        
        
        
        HoldemResponse client = new HoldemResponse();
        System.out.println("cliente"+client);
        client.messageReceived("play");
        System.out.println("cliente"+client.toString());
        HoldemPlayer player = new HoldemPlayer(user, Double.parseDouble(money), client);
        System.out.println("User: "+user+" money: "+Double.parseDouble(money)+" player: "+player);
        holdem.get(tableId).addPlayer(player);
        
        System.out.println("ok.."+player.toString());
        
        
        return new HoldemResponse();
    }
    
    @MessageMapping("/gameholdem{channel}/play")
    @SendTo("/topic/gameholdem{channel}")
    public HoldemResponse playRound(@DestinationVariable String channel) {
        tableId = Integer.parseInt(channel);
        
        
        holdem.get(tableId).run();
        
        
        
        
        
        return new HoldemResponse();
    }

}
