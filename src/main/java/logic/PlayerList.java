package logic;

import java.util.List;

/**
 * Created by megasoch on 13.10.2016.
 */
public interface PlayerList {
    boolean nextPlayer();
    Player getCurrentPlayer();
    int size();
    void beginBets(Player player);
    Player getDealer();
    void nextDealer();
    void decreaseActivePlayersSize();
    List<Player> getAllPlayers();
}
