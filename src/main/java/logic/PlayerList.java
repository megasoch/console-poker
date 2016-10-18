package logic;

import java.util.List;

/**
 * Created by megasoch on 13.10.2016.
 */
public interface PlayerList {
    void nextPlayer();
    Player getCurrentPlayer();
    int size();
    void goToDealer();
    Player getDealer();
    void nextDealer();
    List<Player> getAllPlayers();
    void nextRound();
    int inRoundPlayersSize();
    Player getWinner();
    void updateStates();
}
