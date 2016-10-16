import draw.ConsoleDrawer;
import enums.CardDenomination;
import logic.Game;

import java.io.IOException;

/**
 * Created by megasoch on 12.10.2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Game game = new Game();
        game.run();
    }
}
