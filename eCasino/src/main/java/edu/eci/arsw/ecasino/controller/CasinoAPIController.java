package edu.eci.arsw.ecasino.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.ecasino.persistence.CasinoPersistenceException;
import edu.eci.arsw.ecasino.services.CasinoServices;

@RestController
public class CasinoAPIController {
	
	@Autowired
	CasinoServices cs;
	
	@RequestMapping
	public ResponseEntity<?> getAllLobbiesHandler() {
		try {
			return new ResponseEntity<>(cs.getAllLobbies(), HttpStatus.OK);
		} catch (CasinoPersistenceException e) {
			Logger.getLogger(CasinoAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>("Lobbies not found", HttpStatus.NOT_FOUND);
		}
	}

}
