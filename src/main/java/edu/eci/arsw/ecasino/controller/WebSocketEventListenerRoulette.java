/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.ecasino.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import edu.eci.arsw.ecasino.model.game.roulette.RouletteResponse;

@Component
public class WebSocketEventListenerRoulette {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListenerRoulette.class);

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		logger.info("Received a new web socket connection, game Roulette");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

		String username = (String) headerAccessor.getSessionAttributes().get("username");
		// int channel = (int) headerAccessor.getSessionAttributes().get("channel");
		if (username != null) {
			logger.info("User Disconnected : " + username);

			RouletteResponse rr = new RouletteResponse();
			rr.getContent();
			messagingTemplate.convertAndSend("/topic/newgame{channel}", rr);
		}
	}
}
