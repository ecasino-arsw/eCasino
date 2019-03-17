package edu.eci.arsw.ecasino.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.ecasino.model.Lobby;
import edu.eci.arsw.ecasino.persistence.CasinoPersistenceException;
import edu.eci.arsw.ecasino.services.CasinoServices;

@RestController
public class CasinoAPIController {
		
	@Autowired
	CasinoServices cs;
	
	@RequestMapping(value = "/lobbies", method = RequestMethod.GET)
	public ResponseEntity<?> getAllLobbiesHandler() {
		try {
			return new ResponseEntity<>(cs.getAllLobbies(), HttpStatus.OK);
		} catch (CasinoPersistenceException ex) {
			Logger.getLogger(CasinoAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Lobbies not found.", HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/lobbies", method = RequestMethod.POST)
	public ResponseEntity<?> addNewLobbyHandler(@RequestBody Lobby lobby) {
		try {
			cs.addNewLobby(lobby);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (CasinoPersistenceException ex) {
			Logger.getLogger(CasinoAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Could not create new lobby, ID might be taken.", HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/lobbies/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getLobbyHandler(@PathVariable Integer id) {
		try {
			return new ResponseEntity<>(cs.getLobby(id), HttpStatus.OK);
		} catch (CasinoPersistenceException ex) {
			Logger.getLogger(CasinoAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Lobby not found.", HttpStatus.NOT_FOUND);
		}
	}

}
