package logic;

import enums.PlayerDecision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by megasoch on 13.10.2016.
 */

public class PlayerListImpl implements PlayerList {
    private ArrayList<Player> players;
    private int currentPlayerId;
    private int dealerId;
    private int steps;

    public PlayerListImpl(List players) {
        this.players = new ArrayList<>();
        this.players.addAll(players);
        this.currentPlayerId = 0;
        this.steps = 0;
        this.dealerId = 0;
    }

    @Override
    public void nextPlayer() {
        currentPlayerId++;
        while (!players.get(currentPlayerId % players.size()).isPlayingHand()) {
            currentPlayerId++;
        }
        currentPlayerId = currentPlayerId % players.size();
    }

    @Override
    public Player getCurrentPlayer() {
        return players.get(currentPlayerId);
    }

    @Override
    public int size() {
        int res = 0;
        for (Player player: players) {
            if (player.isActive()) {
                res++;
            }
        }
        return res;
    }

    @Override
    public void goToDealer() {
        currentPlayerId = dealerId;
    }

    @Override
    public Player getDealer() {
        return players.get(dealerId);
    }

    @Override
    public void nextDealer() {
        dealerId++;
        while (!players.get(dealerId % players.size()).isActive()) {
            dealerId++;
        }
        dealerId = dealerId % players.size();
        currentPlayerId = dealerId;
    }

    @Override
    public List<Player> getAllPlayers() {
        return players;
    }

    @Override
    public void nextRound() {
        for (Player player: players) {
            if (player.isActive()) {
                if (player.getMoneyStack() == 0) {
                    player.setPlayerDecision(PlayerDecision.OUT);
                } else {
                    player.preRoundInitialize();
                }
            }
        }
        nextDealer();
    }

    @Override
    public int inRoundPlayersSize() {
        int res = 0;
        for (Player player: players) {
            if (player.isPlayingHand()) {
                res++;
            }
        }
        return res;
    }

    @Override
    public Player getWinner() {
        if(inRoundPlayersSize() > 1) {
            return null;
        }
        for (Player player: players) {
            if (player.isPlayingHand()) {
                return player;
            }
        }
        return null;
    }

    @Override
    public void updateStates() {
        for (Player player: players) {
            if (player.isPlayingHand() && player.getMoneyStack() == 0) {
                player.setPlayerDecision(PlayerDecision.HAS_NO_STACK_FOR_BET);
            }
        }
    }
}
