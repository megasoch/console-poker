package draw;

import enums.PlayerDecision;
import enums.PlayerType;
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

    public static void drawWinner(Game game) throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        Player winner = game.getPlayerList().getWinner();
        if (winner == null) {
            System.out.println("There is no WINNER yet!");
        } else {
            System.out.println("WINNER is " + winner.getName());
        }
        System.out.println();
    }

    private static void drawPlayersList(Game game) {
        for (Player player: game.getPlayerList().getAllPlayers()) {
            System.out.print(player.getName() + "\t");
            System.out.print(" Stack:" + player.getMoneyStack() + "\t");
            if (player.isPlayingHand()) {
                System.out.print(" Bet:" + player.getStagedBet() + "\t");
            }

            if (player.equals(game.getDealer())) {
                System.out.print(" DEALER");
            }
            if (player.equals(game.getSmallBlindPlayer())) {
                System.out.print(" SB");
            }
            if (player.equals(game.getBigBlindPlayer())) {
                System.out.print(" BB");
            }

            if (player.getPlayerType().equals(PlayerType.PLAYER) && player.isActive()) {
                System.out.print(" Hand: " + player.getHand());
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
        System.out.println();
    }

    private static void drawPot(Game game) {
        System.out.print("Pot:" + game.getPot() + "\t");
        System.out.println("Blinds: " + game.getSmallBlind() + "/" + game.getBigBlind());
    }
}
