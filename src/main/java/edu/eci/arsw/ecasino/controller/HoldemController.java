package edu.eci.arsw.ecasino.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import edu.eci.arsw.ecasino.model.game.texasholdem.Holdem;
import edu.eci.arsw.ecasino.model.game.texasholdem.HoldemResponse;

@Controller
public class HoldemController {
	
	@MessageMapping("/newgame{channel}")
	@SendTo("/topic/newgame{channel}")
	public HoldemResponse updateTable(@DestinationVariable String channel, Holdem holdem) {
		return new HoldemResponse();
	}

}
