package edu.eci.arsw.ecasino.model.game;

import edu.eci.arsw.ecasino.model.Player;
import edu.eci.arsw.ecasino.model.game.interf.IGame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Roulette implements IGame {

	private long tableId;
	private long lobbyId;
	private Random rand = new Random();
	private int numberWin;
	private int timeRulette;
	private ArrayList<Integer> listNumbersRoulette;
	private ArrayList<Player> listPlayers;
	private String listNumbersOld;
	private String dozen;

	protected Roulette() {
		this.listNumbersRoulette = generateNumbersRoulette();
	}

	/*
	 * public Roulette(long tableId, long lobbyId) { this.tableId = tableId;
	 * this.tableId = lobbyId; this.listNumbersRoulette = generateNumbersRoulette();
	 * }
	 */
	public Roulette(Player player) {
		setPlayerInList(player);
		this.listNumbersRoulette = generateNumbersRoulette();
	}

	public long getTableId() {
		return tableId;
	}

	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	public long getLobbyId() {
		return lobbyId;
	}

	public void setLobbyId(long lobbyId) {
		this.lobbyId = lobbyId;
	}

	public ArrayList<Player> getListPlayers() {
		return listPlayers;
	}

	public void setListPlayers(ArrayList<Player> listPlayers) {
		this.listPlayers = listPlayers;
	}

	public void setPlayerInList(Player player) {
		this.listPlayers.add(player);
	}

	public void deletePlayer(Player player) {
		this.listPlayers.remove(player);
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	public int getNumberWin() {
		return numberWin;
	}

	public void setNumberWin(int numberWin) {
		this.numberWin = numberWin;
	}

	public int getTimeRulette() {
		return timeRulette;
	}

	public ArrayList<Integer> getListNumbersRoulette() {
		return listNumbersRoulette;
	}

	public void setListNumbersRoulette(ArrayList<Integer> listNumbersRoulette) {
		this.listNumbersRoulette = listNumbersRoulette;
	}

	public void setTimeRulette(int timeRulette) {
		this.timeRulette = timeRulette;

	}

	public String getDozen() {
		int number = getNumberWin();
		if (number >= 1 && number <= 12) {
			this.dozen = "1";
		} else if (number >= 13 && number <= 24) {
			this.dozen = "2";
		} else if (number >= 25 && number <= 36) {
			this.dozen = "3";
		} else {
			this.dozen = "0";
		}
		return dozen;
	}

	public void setDozen(String dozen) {
		this.dozen = dozen;
	}

	private ArrayList<Integer> generateNumbersRoulette() {

		return new ArrayList<>(Arrays.asList(37, 1, 13, 36, 24, 3, 15, 34, 22, 5, 17, 32, 20, 7, 11, 30, 26, 9, 28, 0,
				2, 14, 35, 23, 4, 16, 33, 21, 6, 18, 31, 19, 8, 12, 29, 25, 10, 27));
	}

	public String getListNumbersOld() {
		return listNumbersOld;
	}

	public void addNumbersOldInList(int number) {
		this.listNumbersOld = number + "|" + listNumbersOld;
	}

	public int turnRoulette() {
		int winner;

		ArrayList<Integer> list = getListNumbersRoulette();
		winner = list.get(rand.nextInt(37));
		setNumberWin(winner);
		addNumbersOldInList(winner);
		return winner;
	}

}
