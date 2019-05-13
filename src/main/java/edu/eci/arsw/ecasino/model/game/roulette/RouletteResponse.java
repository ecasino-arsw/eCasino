package edu.eci.arsw.ecasino.model.game.roulette;

import edu.eci.arsw.ecasino.model.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RouletteResponse {

    int type;
    int winningNumber;
    String numbersOld;
    String winningColor;
    String winningDozen;
    ArrayList<Object> content;
    String username;
    String playerWin;
    float moneyEarn;
    String player;
    int timeTurn;
    int timeElapsed;

    public RouletteResponse() {
    }

    public ArrayList<Object> getContent() {
        this.content = new ArrayList<>(Arrays.asList(winningNumber, winningNumber, winningColor, winningDozen, username, playerWin, moneyEarn, player, timeElapsed, type));
        return content;
    }

    public RouletteResponse(int winner, String dozen, String numbersOld, List<String> numbersPlayer, List<String> timesSelect, String player) {
        this.type = 3;
        this.winningNumber = winner;
        this.numbersOld = numbersOld;
        this.winningDozen = dozen;
        this.player = player;
        
        getPlayerWinner(numbersPlayer);
        calculateEarn(timesSelect);
        setWinningDozen();
        setWinningColor();
        turnTimeRoulette();
    }

    public RouletteResponse(String username, int timeElapsed) {
        this.type = 1;
        this.username = username;
        this.timeElapsed = timeElapsed;
        turnTimeRoulette();
    }
    public RouletteResponse(int timeElapsed) {
        this.type = 2;
        this.timeElapsed = timeElapsed;
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

    public String getUsername() {
        return username;
    }

    private void getPlayerWinner(List<String> numbersPlayer) {
        if (numbersPlayer.contains(Integer.toString(winningNumber))) {
            this.playerWin = "win";
        } else {
            this.playerWin = "lose";
        }

    }

    private void calculateEarn(List<String> timesSelect) {
        int cant= Integer.parseInt(timesSelect.get(winningNumber));
        if (cant > 0) {
            this.moneyEarn = cant * 100 * 36;
        } else{
            //calcular fichas jugadas
            int fichas=0;
            for(int i =0 ; i < timesSelect.size(); i++){
                fichas+=Integer.parseInt(timesSelect.get(i));
            }
            this.moneyEarn = -1*(fichas*100);
        }

    }

    private void changeMoneyPlayer() {
        if (playerWin == "lose") {
            this.moneyEarn *= -1;
        }
    }

    private void turnTimeRoulette() {
        this.timeTurn = 27000;
    }
}
