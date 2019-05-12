package edu.eci.arsw.ecasino.model.game;

import java.util.ArrayList;
import java.util.Arrays;

public class RouletteResponse {

	int winningNumber;
	String numbersOld;
	String winningColor;
	String winningDozen;
	ArrayList<Object> content;

	public RouletteResponse() {
	}

	public ArrayList<Object> getContent() {
		this.content = new ArrayList<>(Arrays.asList(winningNumber, winningNumber, winningColor, winningDozen));
		return content;
	}

	public RouletteResponse(int winner, String dozen, String numbersOld) {
		this.winningNumber = winner;
		this.numbersOld = numbersOld;
		this.winningDozen = dozen;
		setWinningDozen();
		setWinningColor();
	}

	public int getWinningNumber() {
		return winningNumber;
	}

	public String getNumbersOld() {
		return numbersOld;
	}

	public String getWinningColor() {
		return winningColor;
	}

	public void setWinningColor() {
		int number = getWinningNumber();
		ArrayList<Integer> redList = new ArrayList<>(
				Arrays.asList(1, 36, 3, 34, 5, 32, 7, 30, 9, 14, 23, 16, 21, 18, 19, 12, 25, 27));
		int isRed = redList.indexOf(number);
		if (number == 0 || number == 37) {
			this.winningColor = "green";
		} else if (redList.indexOf(number) > -1) {
			this.winningColor = "red";
		} else {
			this.winningColor = "black";
		}
	}

	public String getWinningDozen() {
		return winningDozen;
	}

	public String setWinningDozen() {
		if (winningDozen == "1") {
			this.winningDozen = "1 - 12";
		} else if (winningDozen == "2") {
			this.winningDozen = "13 - 24";
		} else if (winningDozen == "3") {
			this.winningDozen = "25 - 36";
		} else {
			this.winningDozen = "0 and 00";
		}
		return this.winningDozen;
	}

}
