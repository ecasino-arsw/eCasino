package edu.eci.arsw.ecasino.model.game.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

import edu.eci.arsw.ecasino.model.Player;

public class BlackJack {
	
	public BlackJack(ArrayList<Player> players) {
		
	}

	public static void main(String[] args) {

		System.out.println("Welcome to Blackjack!");

		// playingDeck will be the deck the dealer holds
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();

		Deck playerCards = new Deck();
		double playerMoney = 100.0;
		Deck dealerCards = new Deck();

		Scanner userInput = new Scanner(System.in);

		while (playerMoney > 0) {
			System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
			double playerBet = userInput.nextDouble();
			boolean endRound = false;
			if (playerBet > playerMoney) {
				System.out.println("You cannot bet more than you have.");
				break;
			}

			System.out.println("Dealing...");
			playerCards.draw(playingDeck);
			playerCards.draw(playingDeck);

			dealerCards.draw(playingDeck);
			dealerCards.draw(playingDeck);

			while (true) {
				System.out.println("Your Hand:" + playerCards.toString());

				System.out.println("Your hand is currently valued at: " + playerCards.cardsValue());

				System.out.println("Dealer Hand: " + dealerCards.getCard(0).toString() + " and [hidden]");

				System.out.println("Would you like to (1)Hit or (2)Stand");
				int response = userInput.nextInt();
				if (response == 1) {
					playerCards.draw(playingDeck);
					System.out.println("You draw a:" + playerCards.getCard(playerCards.deckSize() - 1).toString());
					if (playerCards.cardsValue() > 21) {
						System.out.println("Bust. Currently valued at: " + playerCards.cardsValue());
						playerMoney -= playerBet;
						endRound = true;
						break;
					}
				}

				if (response == 2) {
					break;
				}

			}

			System.out.println("Dealer Cards:" + dealerCards.toString());
			if ((dealerCards.cardsValue() > playerCards.cardsValue()) && endRound == false) {
				System.out.println("Dealer beats you " + dealerCards.cardsValue() + " to " + playerCards.cardsValue());
				playerMoney -= playerBet;
				endRound = true;
			}
			while ((dealerCards.cardsValue() < 17) && endRound == false) {
				dealerCards.draw(playingDeck);
				System.out.println("Dealer draws: " + dealerCards.getCard(dealerCards.deckSize() - 1).toString());
			}
			System.out.println("Dealers hand value: " + dealerCards.cardsValue());
			if ((dealerCards.cardsValue() > 21) && endRound == false) {
				System.out.println("Dealer Busts. You win!");
				playerMoney += playerBet;
				endRound = true;
			}
			if ((dealerCards.cardsValue() == playerCards.cardsValue()) && endRound == false) {
				System.out.println("Push.");
				endRound = true;
			}
			if ((playerCards.cardsValue() > dealerCards.cardsValue()) && endRound == false) {
				System.out.println("You win the hand.");
				playerMoney += playerBet;
				endRound = true;
			} else if (endRound == false) // dealer wins
			{
				System.out.println("Dealer wins.");
				playerMoney -= playerBet;
			}

			// End of hand - put cards back in deck
			playerCards.moveAllToDeck(playingDeck);
			dealerCards.moveAllToDeck(playingDeck);
			System.out.println("End of Hand.");

		}
		// Game is over
		System.out.println("Game over! You lost all your money. :(");

		// Close Scanner
		userInput.close();

	}

}
