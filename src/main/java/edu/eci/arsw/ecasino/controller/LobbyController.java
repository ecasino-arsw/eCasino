package edu.eci.arsw.eCasino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.eCasino.model.Lobby;
import edu.eci.arsw.eCasino.service.contract.ILobbyServices;

@RestController
public class LobbyController {

	@Autowired
	ILobbyServices lobbyServices;

	@ResponseBody
	@RequestMapping(value = "/lobbies", method = RequestMethod.GET)
	public ResponseEntity<?> getLobbies() {
		try {
			return new ResponseEntity<>(lobbyServices.list(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/lobbies/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getLobby(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(lobbyServices.get(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/lobbies", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> createLobby(@RequestBody Lobby lobby) {
		try {
			return new ResponseEntity<>(lobbyServices.create(lobby), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/lobbies", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> updateLobby(@RequestBody Lobby lobby) {
		try {
			lobbyServices.update(lobby);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/lobbies", method = RequestMethod.DELETE, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> deleteLobby(@RequestBody Lobby lobby) {
		try {
			lobbyServices.delete(lobby);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.CONFLICT);
		}
	}

}
