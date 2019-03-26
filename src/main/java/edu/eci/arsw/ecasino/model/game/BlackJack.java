package edu.eci.arsw.ecasino.model.game;

import com.sun.media.jfxmedia.events.PlayerTimeListener;
import java.util.ArrayList;

import edu.eci.arsw.ecasino.model.Player;
import edu.eci.arsw.ecasino.model.cards.Deck;
import java.io.IOException;
import java.util.Scanner;

public class BlackJack implements Game {

    private ArrayList<Player> players;
    private Deck playerDeck, dealerDeck;
    private double playerMoney, playerBet;
    private Scanner userInput;
    public Deck playingDeck;

    /*public static void main(String[] args) throws IOException {
        System.out.println("BLACKJACK.....");

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("juan"));
        new BlackJack(players);

        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();
        System.out.println(playingDeck);
    }*/

    public BlackJack(ArrayList<Player> players) {
        this.players = players;
        start();
    }

    @Override
    public void setupGame() {
        this.playerDeck = new Deck();
        this.dealerDeck = new Deck();

        this.playerMoney = 100.00;

        this.userInput = new Scanner(System.in);

    }

    @Override
    public void start() {
        setupGame();
        playGame();
    }

    @Override
    public void finish() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void playGame() {
        while (playerMoney > 0) {
            System.out.println("Usted tiene $" + playerMoney + ", cuanto desea apostar?");
            playerBet = userInput.nextDouble();
            if(playerBet > playerMoney){
                System.out.println("Error, Supera su saldo actual");
                break;
            }
            
            boolean endRound = false;
            
            //Jugador obtiene dos cartas
            this.playerDeck.draw(playingDeck);
            this.playerDeck.draw(playingDeck);
            
            //dealer obtiene dos cartas
            this.dealerDeck.draw(playingDeck);
            this.dealerDeck.draw(playingDeck);
            
            while(true){
                System.out.println("Your hand: ");
                System.out.println(playerDeck.toString());
                System.out.println("La cuenta de tu mano es: "+playerDeck.cardsValue());
                
                
                System.out.println("Mano Dealer: "+ dealerDeck.getCard(0).toString() + " [Oculta]");
                
                System.out.println("(1) pedir carta o (2) plantarse? ");
                
                int response = userInput.nextInt();
                if(response == 1){
                    playerDeck.draw(playingDeck);
                    System.out.println("La otra carta es: "+playerDeck.getCard(playerDeck.deckSize()-1).toString());
                    
                    if(playerDeck.cardsValue()<21){
                        System.out.println("Perdio.. tiene mas de 21 "+playerDeck.cardsValue());
                        playerMoney-=playerBet;
                        endRound = true;
                       break;
                    }
                }
                if(response == 2)break;
            }
            
            System.out.println("Cartas dealer: "+dealerDeck.toString());
            if(dealerDeck.cardsValue() > playerDeck.cardsValue() && !endRound){
                System.out.println("Dealer gano");
                playerMoney-=playerBet;
                endRound = true;
                
            }
            while(dealerDeck.cardsValue() < 17 && !endRound){
                dealerDeck.draw(playingDeck);
                System.out.println("Carta> "+ dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
            }
            System.out.println("Cuenta dealer: "+ dealerDeck.cardsValue());
            
            if(dealerDeck.cardsValue() > 21 && !endRound){
                System.out.println("Dealer se pasa de 21, Usted GANA...");
                playerMoney+=playerBet;
                endRound =true;
            }
            
            if(playerDeck.cardsValue() == dealerDeck.cardsValue() && !endRound){
                System.out.println("Empate");
                endRound = true;
            }
            
            if(playerDeck.cardsValue() > dealerDeck.cardsValue() && !endRound){
                System.out.println("GANO....");
                playerMoney+=playerBet;
                endRound = true;
            }
            
            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);
            System.out.println("Fin de Mano");
        }
    }
}
