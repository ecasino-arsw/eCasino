package edu.eci.arsw.ecasino.controller;

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

import edu.eci.arsw.ecasino.model.GameTable;
import edu.eci.arsw.ecasino.service.contract.IGameTableServices;

@RestController
public class GameTableController {
	
	@Autowired
	IGameTableServices tableServices;

	@ResponseBody
	@RequestMapping(value = "/lobbies/{lobbyId}/tables", method = RequestMethod.GET)
	public ResponseEntity<?> getTables(@PathVariable Long lobbyId) {
		try {
			return new ResponseEntity<>(tableServices.list(lobbyId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/lobbies/{lobbyId}/tables/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTable(@PathVariable Long lobbyId, @PathVariable Long id) {
		try {
			return new ResponseEntity<>(tableServices.get(lobbyId, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/lobbies/{lobbyId}/tables", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> createTable(@RequestBody GameTable gameTable) {
		try {
			return new ResponseEntity<>(tableServices.create(gameTable), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/lobbies/{lobbyId}/tables", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> updateTable(@RequestBody GameTable gameTable) {
		try {
			tableServices.update(gameTable);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/lobbies/{lobbyId}/tables", method = RequestMethod.DELETE, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> deleteTable(@RequestBody GameTable gameTable) {
		try {
			tableServices.delete(gameTable);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.CONFLICT);
		}
	}

}
