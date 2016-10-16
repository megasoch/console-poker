package draw;

import enums.PlayerDecision;
import logic.Card;
import logic.Game;
import logic.Player;

import java.io.IOException;

/**
 * Created by megasoch on 15.10.2016.
 */
public class ConsoleDrawer {
    public static void draw(Game game) throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        drawPot(game);
        drawPlayersList(game);
        drawTable(game);
    }

    private static void drawPlayersList(Game game) {
        for (Player player: game.getPlayerList().getAllPlayers()) {
            System.out.print(player.getName());
            System.out.print(" Stack " + player.getMoneyStack());
            System.out.print(" Decision: " + player.getPlayerDecision());
            if (player.getPlayerDecision().equals(PlayerDecision.CHECK) || player.getPlayerDecision().equals(PlayerDecision.CALL)) {
                System.out.print(" Staged bet: " + player.getStagedBet());
            }
            System.out.println();
        }
    }

    private static void drawTable(Game game) {
        if (!game.getTable().isEmpty()) {
            System.out.print("|| ");
        }
        for (Card card: game.getTable()) {
            System.out.print(card.toString() + " || ");
        }
        System.out.println();
    }

    private static void drawPot(Game game) {
        System.out.println("Pot: " + game.getPot());
    }
}
