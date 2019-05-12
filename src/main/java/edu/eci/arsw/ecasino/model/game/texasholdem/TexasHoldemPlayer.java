// This file is part of the 'texasholdem' project, an open source
// Texas Hold'em poker application written in Java.
//
// Copyright 2009 Oscar Stigter
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package edu.eci.arsw.ecasino.model.game.texasholdem;

import java.util.List;

import edu.eci.arsw.ecasino.model.Player;
import edu.eci.arsw.ecasino.model.game.texasholdem.actions.Action;

/**
 * A Texas Hold'em player. <br />
 * <br />
 * 
 * The player's actions are delegated to a {@link Client}, which can be either
 * human-controlled or AI-controlled (bot).
 * 
 * @author Oscar Stigter
 */
public class TexasHoldemPlayer extends Player {

    /** Client application responsible for the actual behavior. */
    private final Client client;

    /** Hand of cards. */
    private final Hand hand;
    
    /** Whether the player has hole cards. */
    private boolean hasCards;

    /** Current bet. */
    private double bet;

    /** Last action performed. */
    private Action action;

    /**
     * Constructor.
     * 
     * @param name
     *            The player's name.
     * @param cash
     *            The player's starting amount of cash.
     * @param client
     *            The client application.
     */
    public TexasHoldemPlayer(String name, double money, Client client) {
        this.username = name;
        this.money = money;
        this.client = client;

        hand = new Hand();

        resetHand();
    }

    /**
     * Returns the client.
     * 
     * @return The client.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Prepares the player for another hand.
     */
    public void resetHand() {
        hasCards = false;
        hand.removeAllCards();
        resetBet();
    }

    /**
     * Resets the player's bet.
     */
    public void resetBet() {
        bet = 0;
        action = (hasCards() && money == 0) ? Action.ALL_IN : null;
    }

    /**
     * Sets the hole cards.
     */
    public void setCards(List<Card> cards) {
        hand.removeAllCards();
        if (cards != null) {
            if (cards.size() == 2) {
                hand.addCards(cards);
                hasCards = true;
                System.out.format("[CHEAT] %s's cards:\t%s\n", money, hand);
            } else {
                throw new IllegalArgumentException("Invalid number of cards");
            }
        }
    }

    /**
     * Returns whether the player has his hole cards dealt.
     * 
     * @return True if the hole cards are dealt, otherwise false.
     */
    public boolean hasCards() {
        return hasCards;
    }

    /**
     * Returns the player's current bet.
     * 
     * @return The current bet.
     */
    public double getBet() {
        return bet;
    }
    
    /**
     * Sets the player's current bet.
     * 
     * @param d
     *            The current bet.
     */
    public void setBet(double d) {
        this.bet = d;
    }

    /**
     * Returns the player's most recent action.
     * 
     * @return The action.
     */
    public Action getAction() {
        return action;
    }
    
    /**
     * Sets the player's most recent action.
     * 
     * @param action
     *            The action.
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * Indicates whether this player is all-in.
     * 
     * @return True if all-in, otherwise false.
     */
    public boolean isAllIn() {
        return hasCards() && (money == 0);
    }

    /**
     * Returns the player's hole cards.
     * 
     * @return The hole cards.
     */
    public Card[] getCards() {
        return hand.getCards();
    }

    /**
     * Posts the small blind.
     * 
     * @param blind
     *            The small blind.
     */
    public void postSmallBlind(int blind) {
        action = Action.SMALL_BLIND;
        money -= blind;
        bet += blind;
    }

    /**
     * Posts the big blinds.
     * 
     * @param blind
     *            The big blind.
     */
    public void postBigBlind(int blind) {
        action = Action.BIG_BLIND;
        money -= blind;
        bet += blind;
    }
    
    /**
     * Pays an amount of cash.
     * 
     * @param betIncrement
     *            The amount of cash to pay.
     */
    public void payCash(double betIncrement) {
        if (betIncrement > money) {
            throw new IllegalStateException("Player asked to pay more cash than he owns!");
        }
        money -= betIncrement;
    }
    
    /**
     * Wins an amount of money.
     * 
     * @param potShare
     *            The amount won.
     */
    public void win(double potShare) {
    	money += potShare;
    }

    /**
     * Returns a clone of this player with only public information.
     * 
     * @return The cloned player.
     */
    public TexasHoldemPlayer publicClone() {
        TexasHoldemPlayer clone = new TexasHoldemPlayer(username, money, null);
        clone.hasCards = hasCards;
        clone.bet = bet;
        clone.action = action;
        return clone;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return username;
    }

}
