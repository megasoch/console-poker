import draw.ConsoleDrawer;
import enums.CardDenomination;
import logic.Combination;
import logic.Game;
import utils.CombinationChecker;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by megasoch on 12.10.2016.
 */
public class Main {
    private static CardDenomination cardDenomination1;
    private static CardDenomination cardDenomination2;
    public static void main(String[] args) throws InterruptedException, IOException {
        Game game = new Game();
        game.run();
    }
}
