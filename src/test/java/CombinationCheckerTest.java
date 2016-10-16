import enums.CardDenomination;
import enums.CardSuit;
import enums.CombinationType;
import logic.Card;
import logic.Combination;
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
    private static List<Card> fullHouseCards;
    private static List<Card> threeOfAKindCards;
    private static List<Card> twoPairsCards;
    private static List<Card> onePairCards;

    private static List<Card> sevenCardStraightCards;

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

        fullHouseCards = new ArrayList<>();
        fullHouseCards.add(new Card(CardDenomination.ACE, CardSuit.CLUBS));
        fullHouseCards.add(new Card(CardDenomination.ACE, CardSuit.HEARTS));
        fullHouseCards.add(new Card(CardDenomination.KING, CardSuit.CLUBS));
        fullHouseCards.add(new Card(CardDenomination.ACE, CardSuit.DIAMONDS));
        fullHouseCards.add(new Card(CardDenomination.KING, CardSuit.SPADES));

        threeOfAKindCards = new ArrayList<>();
        threeOfAKindCards.add(new Card(CardDenomination.TWO, CardSuit.CLUBS));
        threeOfAKindCards.add(new Card(CardDenomination.TWO, CardSuit.DIAMONDS));
        threeOfAKindCards.add(new Card(CardDenomination.THREE, CardSuit.CLUBS));
        threeOfAKindCards.add(new Card(CardDenomination.TWO, CardSuit.HEARTS));
        threeOfAKindCards.add(new Card(CardDenomination.SEVEN, CardSuit.CLUBS));

        twoPairsCards = new ArrayList<>();
        twoPairsCards.add(new Card(CardDenomination.ACE, CardSuit.CLUBS));
        twoPairsCards.add(new Card(CardDenomination.QUEEN, CardSuit.CLUBS));
        twoPairsCards.add(new Card(CardDenomination.QUEEN, CardSuit.DIAMONDS));
        twoPairsCards.add(new Card(CardDenomination.ACE, CardSuit.HEARTS));
        twoPairsCards.add(new Card(CardDenomination.EIGHT, CardSuit.CLUBS));

        onePairCards = new ArrayList<>();
        onePairCards.add(new Card(CardDenomination.JACK, CardSuit.CLUBS));
        onePairCards.add(new Card(CardDenomination.SEVEN, CardSuit.CLUBS));
        onePairCards.add(new Card(CardDenomination.THREE, CardSuit.HEARTS));
        onePairCards.add(new Card(CardDenomination.JACK, CardSuit.CLUBS));
        onePairCards.add(new Card(CardDenomination.ACE, CardSuit.CLUBS));

        sevenCardStraightCards = new ArrayList<>();
        sevenCardStraightCards.add(new Card(CardDenomination.JACK, CardSuit.CLUBS));
        sevenCardStraightCards.add(new Card(CardDenomination.TEN, CardSuit.HEARTS));
        sevenCardStraightCards.add(new Card(CardDenomination.ACE, CardSuit.DIAMONDS));
        sevenCardStraightCards.add(new Card(CardDenomination.QUEEN, CardSuit.SPADES));
        sevenCardStraightCards.add(new Card(CardDenomination.ACE, CardSuit.CLUBS));
        sevenCardStraightCards.add(new Card(CardDenomination.KING, CardSuit.HEARTS));
        sevenCardStraightCards.add(new Card(CardDenomination.TWO, CardSuit.CLUBS));
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

    @Test
    public void isFullHouse() {
        assertTrue(CombinationChecker.isFullHouse(fullHouseCards));
    }

    @Test
    public void isThreeOfAKind() {
        assertTrue(CombinationChecker.isThreeOfAKind(threeOfAKindCards));
    }

    @Test
    public void isTwoPairs() {
        assertTrue(CombinationChecker.isTwoPairs(twoPairsCards));
    }

    @Test
    public void isOnePair() {
        assertTrue(CombinationChecker.isOnePair(onePairCards));
    }

    @Test
    public void isStraightOfSevenCards() {
        assertEquals(CombinationType.STRAIGHT, CombinationChecker.highestCombination(sevenCardStraightCards).getCombinationType());
    }
}
