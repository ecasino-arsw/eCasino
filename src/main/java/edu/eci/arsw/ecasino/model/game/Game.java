package edu.eci.arsw.ecasino.model.game;

import java.util.ArrayList;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.core.JsonToken;

import edu.eci.arsw.ecasino.model.Player;

@JsonTypeInfo(
		use = Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type"
		)
@JsonSubTypes({
	@Type(value = BlackJack.class),
	@Type(value = HoldEm.class)
})
public interface Game {
	
	public void setupGame();
	
	public void start();
	
	public void finish();
	
	public ArrayList<Player> getPlayers();

}
