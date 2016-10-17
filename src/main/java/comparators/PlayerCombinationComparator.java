package comparators;

import logic.Player;

import java.util.Comparator;

/**
 * Created by megasoch on 17.10.2016.
 */
public class PlayerCombinationComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Player player1 = (Player)o1;
        Player player2 = (Player)o2;
        return player1.getHighestCombination().compareTo(player2.getHighestCombination());
    }
}
