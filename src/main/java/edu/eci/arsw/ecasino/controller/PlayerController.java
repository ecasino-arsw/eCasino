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

import edu.eci.arsw.ecasino.model.Player;
import edu.eci.arsw.ecasino.service.contract.IPlayerServices;

@RestController
public class PlayerController {

	@Autowired
	IPlayerServices playerServices;

	@ResponseBody
	@RequestMapping(value = "/players", method = RequestMethod.GET)
	public ResponseEntity<?> getPlayers() {
		try {
			return new ResponseEntity<>(playerServices.list(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/players/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> getPlayer(@PathVariable String username) {
		try {
                    if(playerServices.get(username).getId()!=null){
                        return new ResponseEntity<>(playerServices.get(username), HttpStatus.OK);
                    }else{
                        return null;
                    }
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/players", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> createPlayer(@RequestBody Player player) {
		try {
			return new ResponseEntity<>(playerServices.create(player), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/players", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> updatePlayer(@RequestBody Player player) {
		try {
			playerServices.update(player);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/players", method = RequestMethod.DELETE, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> deletePlayer(@RequestBody Player player) {
		try {
			playerServices.delete(player);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.CONFLICT);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/players/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePlayerById(@PathVariable Long id) {
		try {

			Player selectPlayer = playerServices.get(id);
			playerServices.delete(selectPlayer);

			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.CONFLICT);
		}
	}

}
