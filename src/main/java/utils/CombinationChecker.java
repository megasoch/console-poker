package utils;

import enums.CardDenomination;
import enums.CombinationType;
import logic.Card;
import logic.Combination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by megasoch on 16.10.2016.
 */
public class CombinationChecker {
    private static CardDenomination mainCardDenomination1;
    private static CardDenomination mainCardDenomination2;

    public static Combination highestCombination(List<Card> cards) {
        int[] intComb = new int[]{1, 2, 3, 4, 5};
        List<Combination> combinations = new ArrayList<>();
        do {
            List<Card> combinationCards = new ArrayList<>();
            for (Integer id : intComb) {
                combinationCards.add(cards.get(id - 1));
            }
            CombinationType combinationType = CombinationType.HIGH_CARD;
            if (isStraightFlush(combinationCards)) {
                combinationType = CombinationType.STRAIGHT_FLUSH;
            } else if (isFourOfAKind(combinationCards)) {
                combinationType = CombinationType.FOUR_OF_A_KIND;
            } else if(isFullHouse(combinationCards)) {
                combinationType = CombinationType.FULL_HOUSE;
            } else if(isFlush(combinationCards)) {
                combinationType = CombinationType.FLUSH;
            } else if(isStraight(combinationCards)) {
                combinationType = CombinationType.STRAIGHT;
            } else if (isThreeOfAKind(combinationCards)) {
                combinationType = CombinationType.THREE_OF_A_KIND;
            } else if (isTwoPairs(combinationCards)) {
                combinationType = CombinationType.TWO_PAIRS;
            } else if (isOnePair(combinationCards)) {
                combinationType = CombinationType.ONE_PAIR;
            }
            Combination cmb = new Combination();
            cmb.setCards(combinationCards);
            cmb.setCombinationType(combinationType);
            cmb.setMainCardDenomination1(mainCardDenomination1);
            cmb.setMainCardDenomination2(mainCardDenomination2);
            combinations.add(cmb);
        } while (nextCombination(intComb, cards.size()));
        Collections.sort(combinations);
        return combinations.get(combinations.size() - 1);
    }

    private static boolean nextCombination(int[] a, int n) {
        int k = a.length;
        for (int i = k - 1; i >= 0; i--)
            if (a[i] < n - k + i + 1) {
                a[i]++;
                for (int j = i + 1; j < k; j++)
                    a[j] = a[j - 1] + 1;
                return true;
            }
        return false;
    }

    private static Combination getCombination(List<Card> cards) {
        return null;
    }

    public static boolean isStraight(List<Card> cards) {
        Collections.sort(cards);
        for (int i = 1; i < cards.size() - 1; i++) {
            if (cards.get(i - 1).getCardDenomination().ordinal() != cards.get(i).getCardDenomination().ordinal() - 1) {
                return false;
            }
        }
        int aceMinusFive = CardDenomination.ACE.ordinal() - CardDenomination.FIVE.ordinal();
        int diff = cards.get(4).getCardDenomination().ordinal() - cards.get(3).getCardDenomination().ordinal();
        return diff == 1 || diff == aceMinusFive ? true : false;
    }

    public static boolean isFlush(List<Card> cards) {
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i - 1).getCardSuit().ordinal() != cards.get(i).getCardSuit().ordinal()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isStraightFlush(List<Card> cards) {
        return isStraight(cards) && isFlush(cards);
    }

    public static boolean isFourOfAKind(List<Card> cards) {
        Collections.sort(cards);
        if (cards.get(0).getCardDenomination().ordinal() == cards.get(3).getCardDenomination().ordinal() ||
                cards.get(1).getCardDenomination().ordinal() == cards.get(4).getCardDenomination().ordinal()) {
            mainCardDenomination1 = cards.get(1).getCardDenomination();
            if (cards.get(0).getCardDenomination().equals(cards.get(1).getCardDenomination())) {
                mainCardDenomination2 = cards.get(4).getCardDenomination();
            } else {
                mainCardDenomination2 = cards.get(0).getCardDenomination();
            }
            return true;
        }
        return false;
    }

    public static boolean isFullHouse(List<Card> cards) {
        Collections.sort(cards);
        if (cards.get(0).getCardDenomination().ordinal() == cards.get(2).getCardDenomination().ordinal() &&
                cards.get(3).getCardDenomination().ordinal() == cards.get(4).getCardDenomination().ordinal()) {
            mainCardDenomination1 = cards.get(0).getCardDenomination();
            mainCardDenomination2 = cards.get(3).getCardDenomination();
            return true;
        }
        if (cards.get(0).getCardDenomination().ordinal() == cards.get(1).getCardDenomination().ordinal() &&
                cards.get(2).getCardDenomination().ordinal() == cards.get(4).getCardDenomination().ordinal()) {
            mainCardDenomination1 = cards.get(2).getCardDenomination();
            mainCardDenomination2 = cards.get(0).getCardDenomination();
            return true;
        }
        return false;
    }

    public static boolean isThreeOfAKind(List<Card> cards) {
        Collections.sort(cards);
        if (cards.get(0).getCardDenomination().ordinal() == cards.get(2).getCardDenomination().ordinal() ||
                cards.get(1).getCardDenomination().ordinal() == cards.get(3).getCardDenomination().ordinal() ||
                cards.get(2).getCardDenomination().ordinal() == cards.get(4).getCardDenomination().ordinal()) {
            mainCardDenomination1 = cards.get(2).getCardDenomination();
            mainCardDenomination2 = null;
            return true;
        }
        return false;
    }

    public static boolean isTwoPairs(List<Card> cards) {
        Collections.sort(cards);
        if (cards.get(0).getCardDenomination().ordinal() == cards.get(1).getCardDenomination().ordinal() &&
                cards.get(2).getCardDenomination().ordinal() == cards.get(3).getCardDenomination().ordinal()) {
            mainCardDenomination1 = cards.get(2).getCardDenomination();
            mainCardDenomination2 = cards.get(0).getCardDenomination();
            return true;
        }
        if (cards.get(1).getCardDenomination().ordinal() == cards.get(2).getCardDenomination().ordinal() &&
                cards.get(3).getCardDenomination().ordinal() == cards.get(4).getCardDenomination().ordinal()) {
            mainCardDenomination1 = cards.get(3).getCardDenomination();
            mainCardDenomination2 = cards.get(1).getCardDenomination();
            return true;
        }
        if (cards.get(0).getCardDenomination().ordinal() == cards.get(1).getCardDenomination().ordinal() &&
                cards.get(3).getCardDenomination().ordinal() == cards.get(4).getCardDenomination().ordinal()) {
            mainCardDenomination1 = cards.get(3).getCardDenomination();
            mainCardDenomination2 = cards.get(0).getCardDenomination();
            return true;
        }
        return false;
    }

    public static boolean isOnePair(List<Card> cards) {
        Collections.sort(cards);
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i - 1).getCardDenomination().ordinal() == cards.get(i).getCardDenomination().ordinal()) {
                mainCardDenomination1 = cards.get(i).getCardDenomination();
                mainCardDenomination2 = null;
                return true;
            }
        }
        return false;
    }

}
