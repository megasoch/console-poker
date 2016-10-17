package logic;

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

    public void beginBets(Player player) {
        currentPlayerId = this.players.indexOf(player);
        while (!players.get(currentPlayerId).isActive() || !players.get(currentPlayerId).isPlayingHand()) {
            currentPlayerId = (currentPlayerId + 1) % players.size();
        }
        steps = 0;
    }

    @Override
    public boolean nextPlayer() {
        while (true) {
            currentPlayerId = (currentPlayerId + 1) % size();
            steps++;
            if (players.get(currentPlayerId).isActive() && players.get(currentPlayerId).isPlayingHand()) {
                break;
            }

        }
        return steps < players.size();
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

    public void nextRound() {
        for (Player player: players) {
            if (player.isActive()) {
                player.preRoundInitialize();
            }
        }
        nextDealer();
        steps = 0;
    }

    public Player getDealer() {
        return players.get(dealerId);
    }

    public void nextDealer() {
        while (!players.get((++dealerId) % players.size()).isActive()) {
        }
        dealerId = dealerId % players.size();
        currentPlayerId = dealerId;
    }

    public List<Player> getAllPlayers() {
        return players;
    }

    public int inRoundPlayersSize() {
        int res = 0;
        for (Player player: players) {
            if (player.isPlayingHand() && player.isActive()) {
                res++;
            }
        }
        return res;
    }

    public Player getWinner() {
        if(inRoundPlayersSize() > 1) {
            return null;
        }
        for (Player player: players) {
            if (player.isPlayingHand() && player.isActive()) {
                return player;
            }
        }
        return null;
    }
}
