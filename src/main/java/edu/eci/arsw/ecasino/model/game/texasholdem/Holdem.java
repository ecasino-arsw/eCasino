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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import edu.eci.arsw.ecasino.model.game.texasholdem.actions.Action;
import edu.eci.arsw.ecasino.model.game.texasholdem.actions.BetAction;
import edu.eci.arsw.ecasino.model.game.texasholdem.actions.RaiseAction;

public class Holdem {

    private static final int MAX_RAISES = 3;
    private static final boolean ALWAYS_CALL_SHOWDOWN = false;
    private final TableType tableType;
    private final int bigBlind;
    private final List<HoldemPlayer> players;
    private final List<HoldemPlayer> activePlayers;
    private final Deck deck;
    private final List<Card> board;
    private int dealerPosition;
    private HoldemPlayer dealer;
    private int actorPosition;
    private HoldemPlayer actor;
    private double minBet;
    private double bet;
    private final List<Pot> pots;
    private HoldemPlayer lastBettor;
    private double raises;

    public Holdem(TableType type, int bigBlind) {
        this.tableType = type;
        this.bigBlind = bigBlind;
        players = new ArrayList<HoldemPlayer>();
        activePlayers = new ArrayList<HoldemPlayer>();
        deck = new Deck();
        board = new ArrayList<Card>();
        pots = new ArrayList<Pot>();
    }

    public void addPlayer(HoldemPlayer player) {
        players.add(player);
    }

    public void run() {
        for (HoldemPlayer player : players) {
            player.getClient().joinedTable(tableType, bigBlind, players);
        }
        dealerPosition = -1;
        actorPosition = -1;
        while (true) {
            int noOfActivePlayers = 0;
            for (HoldemPlayer player : players) {
                if (player.getMoney() >= bigBlind) {
                    noOfActivePlayers++;
                }
            }
            if (noOfActivePlayers > 1) {
                playHand();
            } else {
                break;
            }
        }
        
        // Game over.
        board.clear();
        pots.clear();
        bet = 0;
        notifyBoardUpdated();
        for (HoldemPlayer player : players) {
            player.resetHand();
        }
        notifyPlayersUpdated(false);
        notifyMessage("Game over.");
    }

    private void playHand() {
        resetHand();
        
        // Small blind.
        if (activePlayers.size() > 2) {
            rotateActor();
        }
        postSmallBlind();
        
        // Big blind.
        rotateActor();
        postBigBlind();
        
        // Pre-Flop.
        dealHoleCards();
        doBettingRound();
        
        // Flop.
        if (activePlayers.size() > 1) {
            bet = 0;
            dealCommunityCards("Flop", 3);
            minBet = bigBlind;
            doBettingRound();

            // Turn.
            if (activePlayers.size() > 1) {
                bet = 0;
                dealCommunityCards("Turn", 1);
                if (tableType == TableType.FIXED_LIMIT) {
                    minBet = 2 * bigBlind;
                } else {
                    minBet = bigBlind;
                }
                doBettingRound();

                // River.
                if (activePlayers.size() > 1) {
                    bet = 0;
                    dealCommunityCards("River", 1);
                    if (tableType == TableType.FIXED_LIMIT) {
                        minBet = 2 * bigBlind;
                    } else {
                        minBet = bigBlind;
                    }
                    doBettingRound();

                    // Showdown.
                    if (activePlayers.size() > 1) {
                        bet = 0;
                        minBet = bigBlind;
                        doShowdown();
                    }
                }
            }
        }
    }

    private void resetHand() {
        // Clear the board.
        board.clear();
        pots.clear();
        notifyBoardUpdated();
        
        // Determine the active players.
        activePlayers.clear();
        for (HoldemPlayer player : players) {
            player.resetHand();
            // Player must be able to afford at least the big blind.
            if (player.getMoney() >= bigBlind) {
                activePlayers.add(player);
            }
        }
        
        // Rotate the dealer button.
        dealerPosition = (dealerPosition + 1) % activePlayers.size();
        dealer = activePlayers.get(dealerPosition);

        // Shuffle the deck.
        deck.shuffle();

        // Determine the first player to act.
        actorPosition = dealerPosition;
        actor = activePlayers.get(actorPosition);
        
        // Set the initial bet to the big blind.
        minBet = bigBlind;
        bet = minBet;
        
        // Notify all clients a new hand has started.
        for (HoldemPlayer player : players) {
            player.getClient().handStarted(dealer);
        }
        notifyPlayersUpdated(false);
        notifyMessage("New hand, %s is the dealer.", dealer);
    }

    private void rotateActor() {
        actorPosition = (actorPosition + 1) % activePlayers.size();
        actor = activePlayers.get(actorPosition);
        for (HoldemPlayer player : players) {
            player.getClient().actorRotated(actor);
        }
    }

    private void postSmallBlind() {
        final int smallBlind = bigBlind / 2;
        actor.postSmallBlind(smallBlind);
        contributePot(smallBlind);
        notifyBoardUpdated();
        notifyPlayerActed();
    }

    private void postBigBlind() {
        actor.postBigBlind(bigBlind);
        contributePot(bigBlind);
        notifyBoardUpdated();
        notifyPlayerActed();
    }

    private void dealHoleCards() {
        for (HoldemPlayer player : activePlayers) {
            player.setCards(deck.deal(2));
        }
        System.out.println();
        notifyPlayersUpdated(false);
        notifyMessage("%s deals the hole cards.", dealer);
    }

    private void dealCommunityCards(String phaseName, int noOfCards) {
        for (int i = 0; i < noOfCards; i++) {
            board.add(deck.deal());
        }
        notifyPlayersUpdated(false);
        notifyMessage("%s deals the %s.", dealer, phaseName);
    }

    private void doBettingRound() {
        // Determine the number of active players.
        int playersToAct = activePlayers.size();
        // Determine the initial player and bet size.
        if (board.size() == 0) {
            // Pre-Flop; player left of big blind starts, bet is the big blind.
            bet = bigBlind;
        } else {
            // Otherwise, player left of dealer starts, no initial bet.
            actorPosition = dealerPosition;
            bet = 0;
        }
        
        /*if (playersToAct == 2) {  //removed, fix by IW
            // Heads Up mode; player who is not the dealer starts.
            actorPosition = dealerPosition;
        }*/
        
        lastBettor = null;
        raises = 0;
        notifyBoardUpdated();
        
        while (playersToAct > 0) {
            rotateActor();
            Action action = null;
            if (actor.isAllIn()) {
                // Player is all-in, so must check.
                action = Action.CHECK;
                playersToAct--;
            } else {
                // Otherwise allow client to act.
                Set<Action> allowedActions = getAllowedActions(actor);
                action = actor.getClient().act(minBet, bet, allowedActions);
                // Verify chosen action to guard against broken clients (accidental or on purpose).
                if (!allowedActions.contains(action)) {
                    if (!(action instanceof BetAction && allowedActions.contains(Action.BET)) && !(action instanceof RaiseAction && allowedActions.contains(Action.RAISE))) {
                        throw new IllegalStateException(String.format("Player '%s' acted with illegal %s action", actor, action));
                    }
                }
                playersToAct--;
                if (action == Action.CHECK) {
                    // Do nothing.
                } else if (action == Action.CALL) {
                    double betIncrement = bet - actor.getBet();
                    if (betIncrement > actor.getMoney()) {
                        betIncrement = actor.getMoney();
                    }
                    actor.payCash(betIncrement);
                    actor.setBet(actor.getBet() + betIncrement);
                    contributePot(betIncrement);
                } else if (action instanceof BetAction) {
                    double amount = (tableType == TableType.FIXED_LIMIT) ? minBet : action.getAmount();
                    if (amount < minBet && amount < actor.getMoney()) {
                        throw new IllegalStateException("Illegal client action: bet less than minimum bet!");
                    }
                    if (amount > actor.getMoney() && actor.getMoney() >= minBet) {
                        throw new IllegalStateException("Illegal client action: bet more cash than you own!");
                    }
                    bet = amount;
                    minBet = amount;
                    double betIncrement = bet - actor.getBet();
                    if (betIncrement > actor.getMoney()) {
                        betIncrement = actor.getMoney();
                    }
                    actor.setBet(bet);
                    actor.payCash(betIncrement);
                    contributePot(betIncrement);
                    lastBettor = actor;
                    playersToAct = (tableType == TableType.FIXED_LIMIT) ? activePlayers.size() : (activePlayers.size() - 1);
                } else if (action instanceof RaiseAction) {
                    double amount = (tableType == TableType.FIXED_LIMIT) ? minBet : action.getAmount();
                    if (amount < minBet && amount < actor.getMoney()) {
                        throw new IllegalStateException("Illegal client action: raise less than minimum bet!");
                    }
                    if (amount > actor.getMoney() && actor.getMoney() >= minBet) {
                        throw new IllegalStateException("Illegal client action: raise more cash than you own!");
                    }
                    bet += amount;
                    minBet = amount;
                    double betIncrement = bet - actor.getBet();
                    if (betIncrement > actor.getMoney()) {
                        betIncrement = actor.getMoney();
                    }
                    actor.setBet(bet);
                    actor.payCash(betIncrement);
                    contributePot(betIncrement);
                    lastBettor = actor;
                    raises++;
                    if (tableType == TableType.FIXED_LIMIT && (raises < MAX_RAISES || activePlayers.size() == 2)) {
                        // All players get another turn.
                        playersToAct = activePlayers.size();
                    } else {
                        // Max. number of raises reached; other players get one more turn.
                        playersToAct = activePlayers.size() - 1;
                    }
                } else if (action == Action.FOLD) {
                    actor.setCards(null);
                    activePlayers.remove(actor);
                    actorPosition--;
                    if (activePlayers.size() == 1) {
                        // Only one player left, so he wins the entire pot.
                        notifyBoardUpdated();
                        notifyPlayerActed();
                        HoldemPlayer winner = activePlayers.get(0);
                        int amount = getTotalPot();
                        winner.win(amount);
                        notifyBoardUpdated();
                        notifyMessage("%s wins $ %d.", winner, amount);
                        playersToAct = 0;
                    }
                } else {
                    // Programming error, should never happen.
                    throw new IllegalStateException("Invalid action: " + action);
                }
            }
            actor.setAction(action);
            if (activePlayers.size() > 1) {
                notifyBoardUpdated();
                notifyPlayerActed();
            }
        }
        
        // Reset player's bets.
        for (HoldemPlayer player : activePlayers) {
            player.resetBet();
        }
        notifyBoardUpdated();
        notifyPlayersUpdated(false);
    }

    private Set<Action> getAllowedActions(HoldemPlayer player) {
        Set<Action> actions = new HashSet<Action>();
        if (player.isAllIn()) {
            actions.add(Action.CHECK);
        } else {
            double actorBet = actor.getBet();
            if (bet == 0) {
                actions.add(Action.CHECK);
                if (tableType == TableType.NO_LIMIT || raises < MAX_RAISES || activePlayers.size() == 2) {
                    actions.add(Action.BET);
                }
            } else {
                if (actorBet < bet) {
                    actions.add(Action.CALL);
                    if (tableType == TableType.NO_LIMIT || raises < MAX_RAISES || activePlayers.size() == 2) {
                        actions.add(Action.RAISE);
                    }
                } else {
                    actions.add(Action.CHECK);
                    if (tableType == TableType.NO_LIMIT || raises < MAX_RAISES || activePlayers.size() == 2) {
                        actions.add(Action.RAISE);
                    }
                }
            }
            actions.add(Action.FOLD);
        }
        return actions;
    }

    private void contributePot(double betIncrement) {
        for (Pot pot : pots) {
            if (!pot.hasContributer(actor)) {
                double potBet = pot.getBet();
                if (betIncrement >= potBet) {
                    // Regular call, bet or raise.
                    pot.addContributer(actor);
                    betIncrement -= pot.getBet();
                } else {
                    // Partial call (all-in); redistribute pots.
                    pots.add(pot.split(actor, betIncrement));
                    betIncrement = 0;
                }
            }
            if (betIncrement <= 0) {
                break;
            }
        }
        if (betIncrement > 0) {
            Pot pot = new Pot(betIncrement);
            pot.addContributer(actor);
            pots.add(pot);
        }
    }

    private void doShowdown() {
//        System.out.println("\n[DEBUG] Pots:");
//        for (Pot pot : pots) {
//            System.out.format("  %s\n", pot);
//        }
//        System.out.format("[DEBUG]  Total: %d\n", getTotalPot());
        
        // Determine show order; start with all-in players...
        List<HoldemPlayer> showingPlayers = new ArrayList<HoldemPlayer>();
        for (Pot pot : pots) {
            for (HoldemPlayer contributor : pot.getContributors()) {
                if (!showingPlayers.contains(contributor) && contributor.isAllIn()) {
                    showingPlayers.add(contributor);
                }
            }
        }
        // ...then last player to bet or raise (aggressor)...
        if (lastBettor != null) {
            if (!showingPlayers.contains(lastBettor)) {
                showingPlayers.add(lastBettor);
            }
        }
        //...and finally the remaining players, starting left of the button.
        int pos = (dealerPosition + 1) % activePlayers.size();
        while (showingPlayers.size() < activePlayers.size()) {
            HoldemPlayer player = activePlayers.get(pos);
            if (!showingPlayers.contains(player)) {
                showingPlayers.add(player);
            }
            pos = (pos + 1) % activePlayers.size();
        }
        
        // Players automatically show or fold in order.
        boolean firstToShow = true;
        int bestHandValue = -1;
        for (HoldemPlayer playerToShow : showingPlayers) {
            Hand hand = new Hand(board);
            hand.addCards(playerToShow.getCards());
            HandValue handValue = new HandValue(hand);
            boolean doShow = ALWAYS_CALL_SHOWDOWN;
            if (!doShow) {
                if (playerToShow.isAllIn()) {
                    // All-in players must always show.
                    doShow = true;
                    firstToShow = false;
                } else if (firstToShow) {
                    // First player must always show.
                    doShow = true;
                    bestHandValue = handValue.getValue();
                    firstToShow = false;
                } else {
                    // Remaining players only show when having a chance to win.
                    if (handValue.getValue() >= bestHandValue) {
                        doShow = true;
                        bestHandValue = handValue.getValue();
                    }
                }
            }
            if (doShow) {
                // Show hand.
                for (HoldemPlayer player : players) {
                    player.getClient().playerUpdated(playerToShow);
                }
                notifyMessage("%s has %s.", playerToShow, handValue.getDescription());
            } else {
                // Fold.
                playerToShow.setCards(null);
                activePlayers.remove(playerToShow);
                for (HoldemPlayer player : players) {
                    if (player.equals(playerToShow)) {
                        player.getClient().playerUpdated(playerToShow);
                    } else {
                        // Hide secret information to other players.
                        player.getClient().playerUpdated(playerToShow.publicClone());
                    }
                }
                notifyMessage("%s folds.", playerToShow);
            }
        }
        
        // Sort players by hand value (highest to lowest).
        Map<HandValue, List<HoldemPlayer>> rankedPlayers = new TreeMap<HandValue, List<HoldemPlayer>>();
        for (HoldemPlayer player : activePlayers) {
            // Create a hand with the community cards and the player's hole cards.
            Hand hand = new Hand(board);
            hand.addCards(player.getCards());
            // Store the player together with other players with the same hand value.
            HandValue handValue = new HandValue(hand);
//            System.out.format("[DEBUG] %s: %s\n", player, handValue);
            List<HoldemPlayer> playerList = rankedPlayers.get(handValue);
            if (playerList == null) {
                playerList = new ArrayList<HoldemPlayer>();
            }
            playerList.add(player);
            rankedPlayers.put(handValue, playerList);
        }

        // Per rank (single or multiple winners), calculate pot distribution.
        int totalPot = getTotalPot();
        Map<HoldemPlayer, Double> potDivision = new HashMap<HoldemPlayer, Double>();
        for (HandValue handValue : rankedPlayers.keySet()) {
            List<HoldemPlayer> winners = rankedPlayers.get(handValue);
            for (Pot pot : pots) {
                // Determine how many winners share this pot.
                int noOfWinnersInPot = 0;
                for (HoldemPlayer winner : winners) {
                    if (pot.hasContributer(winner)) {
                        noOfWinnersInPot++;
                    }
                }
                if (noOfWinnersInPot > 0) {
                    // Divide pot over winners.
                    double potShare = pot.getValue() / noOfWinnersInPot;
                    for (HoldemPlayer winner : winners) {
                        if (pot.hasContributer(winner)) {
                            Double oldShare = potDivision.get(winner);
                            if (oldShare != null) {
                                potDivision.put(winner, oldShare + potShare);
                            } else {
                                potDivision.put(winner, potShare);
                            }
                            
                        }
                    }
                    // Determine if we have any odd chips left in the pot.
                    double oddChips = pot.getValue() % noOfWinnersInPot;
                    if (oddChips > 0) {
                        // Divide odd chips over winners, starting left of the dealer.
                        pos = dealerPosition;
                        while (oddChips > 0) {
                            pos = (pos + 1) % activePlayers.size();
                            HoldemPlayer winner = activePlayers.get(pos);
                            Double oldShare = potDivision.get(winner);
                            if (oldShare != null) {
                                potDivision.put(winner, oldShare + 1);
//                                System.out.format("[DEBUG] %s receives an odd chip from the pot.\n", winner);
                                oddChips--;
                            }
                        }
                        
                    }
                    pot.clear();
                }
            }
        }
        
        // Divide winnings.
        StringBuilder winnerText = new StringBuilder();
        int totalWon = 0;
        for (HoldemPlayer winner : potDivision.keySet()) {
            Double potShare = potDivision.get(winner);
            winner.win(potShare);
            totalWon += potShare;
            if (winnerText.length() > 0) {
                winnerText.append(", ");
            }
            winnerText.append(String.format("%s wins $ %d", winner, potShare));
            notifyPlayersUpdated(true);
        }
        winnerText.append('.');
        notifyMessage(winnerText.toString());
        
        // Sanity check.
        if (totalWon != totalPot) {
            throw new IllegalStateException("Incorrect pot division!");
        }
    }

    private void notifyMessage(String message, Object... args) {
        message = String.format(message, args);
        for (HoldemPlayer player : players) {
            player.getClient().messageReceived(message);
        }
    }

    private void notifyBoardUpdated() {
        int pot = getTotalPot();
        for (HoldemPlayer player : players) {
            player.getClient().boardUpdated(board, bet, pot);
        }
    }

    private int getTotalPot() {
        int totalPot = 0;
        for (Pot pot : pots) {
            totalPot += pot.getValue();
        }
        return totalPot;
    }

    private void notifyPlayersUpdated(boolean showdown) {
        for (HoldemPlayer playerToNotify : players) {
            for (HoldemPlayer player : players) {
                if (!showdown && !player.equals(playerToNotify)) {
                    // Hide secret information to other players.
                    player = player.publicClone();
                }
                playerToNotify.getClient().playerUpdated(player);
            }
        }
    }

    private void notifyPlayerActed() {
        for (HoldemPlayer p : players) {
            HoldemPlayer playerInfo = p.equals(actor) ? actor : actor.publicClone();
            p.getClient().playerActed(playerInfo);
        }
    }
    
}
