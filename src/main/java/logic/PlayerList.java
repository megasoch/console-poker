package logic;

/**
 * Created by megasoch on 13.10.2016.
 */
public interface PlayerList {
    void nextPlayer();
    Player getDealer();
    Player getSmallBlindPlayer();
    Player getBigBlindPlayer();
    Player getCurrentPlayer();
    int size();
}
