import enums.CardDenomination;
import enums.CardSuit;
import logic.Card;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.CombinationChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by megasoch on 16.10.2016.
 */
public class CombinationCheckerTest extends Assert {
    private static List<Card> straightCards;
    private static List<Card> flushCards;
    private static List<Card> fourOfAKindCards;

    @BeforeClass
    public static void initialize() {
        straightCards = new ArrayList<>();
        straightCards.add(new Card(CardDenomination.TEN, CardSuit.SPADES));
        straightCards.add(new Card(CardDenomination.EIGHT, CardSuit.CLUBS));
        straightCards.add(new Card(CardDenomination.JACK, CardSuit.CLUBS));
        straightCards.add(new Card(CardDenomination.NINE, CardSuit.CLUBS));
        straightCards.add(new Card(CardDenomination.QUEEN, CardSuit.CLUBS));

        flushCards = new ArrayList<>();
        flushCards.add(new Card(CardDenomination.TEN, CardSuit.DIAMONDS));
        flushCards.add(new Card(CardDenomination.EIGHT, CardSuit.DIAMONDS));
        flushCards.add(new Card(CardDenomination.NINE, CardSuit.DIAMONDS));
        flushCards.add(new Card(CardDenomination.TWO, CardSuit.DIAMONDS));
        flushCards.add(new Card(CardDenomination.QUEEN, CardSuit.DIAMONDS));

        fourOfAKindCards = new ArrayList<>();
        fourOfAKindCards.add(new Card(CardDenomination.ACE, CardSuit.HEARTS));
        fourOfAKindCards.add(new Card(CardDenomination.TWO, CardSuit.HEARTS));
        fourOfAKindCards.add(new Card(CardDenomination.ACE, CardSuit.SPADES));
        fourOfAKindCards.add(new Card(CardDenomination.ACE, CardSuit.DIAMONDS));
        fourOfAKindCards.add(new Card(CardDenomination.ACE, CardSuit.CLUBS));
    }

    @Test
    public void isStraight() {
        assertTrue(CombinationChecker.isStraight(straightCards));
    }

    @Test
    public void isFlush() {
        assertTrue(CombinationChecker.isFlush(flushCards));
    }

    @Test
    public void isFourOfAKind() {
        assertTrue(CombinationChecker.isFourOfAKind(fourOfAKindCards));
    }
}
