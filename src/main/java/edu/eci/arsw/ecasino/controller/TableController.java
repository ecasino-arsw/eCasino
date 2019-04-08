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

import edu.eci.arsw.eCasino.model.Table;
import edu.eci.arsw.eCasino.service.contract.ITableServices;

@RestController
public class TableController {
	
	@Autowired
	ITableServices tableServices;

	@ResponseBody
	@RequestMapping(value = "/lobbies/{lobbyId}/tables", method = RequestMethod.GET)
	public ResponseEntity<?> getTables(@PathVariable Integer lobbyId) {
		try {
			return new ResponseEntity<>(tableServices.list(lobbyId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/lobbies/{lobbyId}/tables/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTable(@PathVariable Integer lobbyId, @PathVariable Integer id) {
		try {
			return new ResponseEntity<>(tableServices.get(lobbyId, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/lobbies/{lobbyId}/tables", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> createTable(@RequestBody Table table) {
		try {
			return new ResponseEntity<>(tableServices.create(table), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/lobbies/{lobbyId}/tables", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> updateTable(@RequestBody Table table) {
		try {
			tableServices.update(table);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/lobbies/{lobbyId}/tables", method = RequestMethod.DELETE, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> deleteTable(@RequestBody Table table) {
		try {
			tableServices.delete(table);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.CONFLICT);
		}
	}

}
