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
import edu.eci.arsw.ecasino.model.Player;
import edu.eci.arsw.ecasino.model.Table;
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

	@RequestMapping(value = "/lobbies", method = RequestMethod.PUT)
	public ResponseEntity<?> updateLobbyHandler(@RequestBody Lobby lobby) {
		try {
			cs.updateLobby(lobby);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (CasinoPersistenceException ex) {
			Logger.getLogger(CasinoAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Could not update lobby, ID might not exist.", HttpStatus.NOT_FOUND);
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

	@RequestMapping(value = "/lobbies/{id}/tables", method = RequestMethod.GET)
	public ResponseEntity<?> getAllTablesInLobbyHandler(@PathVariable Integer id) {
		try {
			return new ResponseEntity<>(cs.getAllTablesInLobby(cs.getLobby(id)), HttpStatus.OK);
		} catch (CasinoPersistenceException ex) {
			Logger.getLogger(CasinoAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Lobby not found.", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/lobbies/{lobbyId}/tables", method = RequestMethod.POST)
	public ResponseEntity<?> addNewLobbyHandler(@PathVariable Integer lobbyId, @RequestBody Table table) {
		try {
			cs.addNewTable(cs.getLobby(lobbyId), table);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (CasinoPersistenceException ex) {
			Logger.getLogger(CasinoAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Could not update table, ID might not exist or isn't in lobby.",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/lobbies/{id}/tables", method = RequestMethod.PUT)
	public ResponseEntity<?> updateLobbyHandler(@PathVariable Integer id, @RequestBody Table table) {
		try {
			cs.updateTable(id, table);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (CasinoPersistenceException ex) {
			Logger.getLogger(CasinoAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Could not update table, ID might not exist or isn't in lobby.",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/lobbies/{lobbyId}/tables/{tableId}", method = RequestMethod.GET)
	public ResponseEntity<?> getTableHandler(@PathVariable Integer lobbyId, @PathVariable Integer tableId) {
		try {
			Lobby lobby = cs.getLobby(lobbyId);
			return new ResponseEntity<>(cs.getTable(lobby, tableId), HttpStatus.OK);
		} catch (CasinoPersistenceException ex) {
			Logger.getLogger(CasinoAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Lobby not found.", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/lobbies/{lobbyId}/tables/{tableId}/players", method = RequestMethod.GET)
	public ResponseEntity<?> getAllPlayersInTableHandler(@PathVariable Integer lobbyId, @PathVariable Integer tableId) {
		try {
			Lobby lobby = cs.getLobby(lobbyId);
			Table table = cs.getTable(lobby, tableId);
			return new ResponseEntity<>(cs.getAllPlayersInTable(table), HttpStatus.OK);
		} catch (CasinoPersistenceException ex) {
			Logger.getLogger(CasinoAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Lobby not found.", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/players", method = RequestMethod.GET)
	public ResponseEntity<?> getAllPlayersHandler() {
		try {
			return new ResponseEntity<>(cs.getAllPlayers(), HttpStatus.OK);
		} catch (CasinoPersistenceException ex) {
			Logger.getLogger(CasinoAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Players not found.", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/players", method = RequestMethod.POST)
	public ResponseEntity<?> addNewPlayerHandler(@RequestBody Player player) {
		try {
			cs.addNewPlayer(player);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (CasinoPersistenceException ex) {
			Logger.getLogger(CasinoAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Could not create new player, username might be taken.", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/players", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePlayerHandler(@RequestBody Player player) {
		try {
			cs.updatePlayer(player);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (CasinoPersistenceException ex) {
			Logger.getLogger(CasinoAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Could not create new player, username might be taken.", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/players/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> getPlayerHandler(@PathVariable String username) {
		try {
			return new ResponseEntity<>(cs.getPlayer(username), HttpStatus.OK);
		} catch (CasinoPersistenceException ex) {
			Logger.getLogger(CasinoAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Players not found.", HttpStatus.NOT_FOUND);
		}
	}

}
