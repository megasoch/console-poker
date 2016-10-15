package draw;

import logic.Card;
import logic.Game;
import logic.Player;

/**
 * Created by megasoch on 15.10.2016.
 */
public class ConsoleDrawer {
    public static void draw(Game game) {
        drawPlayersList(game);
        drawTable(game);
    }

    private static void drawPlayersList(Game game) {
        for (Player player: game.getPlayerList().getAllPlayers()) {
            System.out.println(player.getName() + " " + player.getHand().toString());
        }
    }

    private static void drawTable(Game game) {
        System.out.print("|| ");
        for (Card card: game.getTable()) {
            System.out.print(card.toString() + " || ");
        }
        System.out.println();
    }
}
