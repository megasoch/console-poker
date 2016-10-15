package utils;

import enums.PlayerType;
import logic.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by megasoch on 12.10.2016.
 */
public class PlayersReader {
    public static List read(String filePath) throws IOException {
        List<Player> players = new LinkedList<>();
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);
        for (String playerDescription : lines) {
            players.add(createPlayer(playerDescription));
        }
        return players;
    }

    private static Player createPlayer(String playerDescription) {
        String[] tokens = playerDescription.split(" ");
        return new Player(tokens[0], PlayerType.valueOf(tokens[1]));
    }
}
