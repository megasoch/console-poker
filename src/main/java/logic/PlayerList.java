package logic;

import java.util.List;

/**
 * Created by megasoch on 13.10.2016.
 */

public interface PlayerList {
    void nextPlayer();
    int size();
    void goToDealer();
    void nextDealer();
    void nextRound();
    int inRoundPlayersSize();
    void updateStates();
    Player getCurrentPlayer();
    Player getDealer();
    List<Player> getAllPlayers();
    Player getWinner();
}
