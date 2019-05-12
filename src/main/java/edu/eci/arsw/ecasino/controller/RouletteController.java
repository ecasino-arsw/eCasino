/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.ecasino.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import edu.eci.arsw.ecasino.model.game.roulette.Roulette;
import edu.eci.arsw.ecasino.model.game.roulette.RouletteResponse;

/**
 *
 * @author villate
 */
@Controller
public class RouletteController {

	@MessageMapping("/newgame{channel}")
	@SendTo("/topic/newgame{channel}")
	public RouletteResponse getNumberWinner(@DestinationVariable String channel, Roulette roulette) {
		int win = roulette.turnRoulette();
		String numbersOld = roulette.getListNumbersOld();
		String dozen = roulette.getDozen();
		return new RouletteResponse(win, dozen, numbersOld);
	}
	/*
	 * @MessageMapping("/newgame{channel}")
	 * 
	 * @SendTo("/topic/newgame{channel}") public RouletteResponse
	 * sedName(@DestinationVariable String channel, Roulette roulette) { int win =
	 * roulette.turnRoulette(); String numbersOld = roulette.getListNumbersOld();
	 * String dozen = roulette.getDozen(); return new RouletteResponse( win, dozen,
	 * numbersOld); }
	 */
}
