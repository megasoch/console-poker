package logic;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by megasoch on 13.10.2016.
 */
public class PlayerListImpl implements PlayerList {
    private Player[] players;
    private int dealerId;
    private int currentPlayerId;

    public PlayerListImpl(Player[] players) {
        this.players = players;
        dealerId = 0;
        currentPlayerId = 1;
    }

    @Override
    public void nextPlayer() {
        currentPlayerId = (currentPlayerId + 1) % players.length;
    }

    @Override
    public Player getDealer() {
        return players[dealerId];
    }

    @Override
    public Player getSmallBlindPlayer() {
        return players[(dealerId + 1) % players.length];
    }

    @Override
    public Player getBigBlindPlayer() {
        return players[(dealerId + 2) % players.length];
    }

    @Override
    public Player getCurrentPlayer() {
        return players[currentPlayerId];
    }

    @Override
    public int size() {
        return players.length;
    }
}
