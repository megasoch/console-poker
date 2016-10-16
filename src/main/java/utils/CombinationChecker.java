package utils;

import logic.Card;
import logic.Combination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by megasoch on 16.10.2016.
 */
public class CombinationChecker {
    public static Combination highestCombination(List<Card> cards) {
        int[] intComb = new int[]{1, 2, 3, 4, 5};
        do {
            List<Card> combinationCards = new ArrayList<>();
            for (Integer id : intComb) {
                combinationCards.add(cards.get(id - 1));
            }

        } while (nextCombination(intComb, cards.size()));
        return null;
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
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i - 1).getCardDenomination().ordinal() != cards.get(i).getCardDenomination().ordinal() - 1) {
                return false;
            }
        }
        return true;
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
            return true;
        }
        return false;
    }


}
