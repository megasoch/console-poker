package utils;

import enums.PlayerType;
import logic.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by megasoch on 16.10.2016.
 */
public class PlayersLoader {
    public static List loadPlayers() {
        List<Player> players = new LinkedList<>();
        players.add(new Player("Andrew", PlayerType.AI));
        players.add(new Player("John", PlayerType.AI));
        players.add(new Player("Vasily", PlayerType.AI));
        players.add(new Player("Peter", PlayerType.AI));
        players.add(new Player("Aleksandr", PlayerType.PLAYER));
        return players;
    }
}
