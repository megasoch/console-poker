package logic;

import enums.CardDenomination;
import enums.CombinationType;

import java.util.Collections;
import java.util.List;

/**
 * Created by megasoch on 16.10.2016.
 */
public class Combination implements Comparable {
    private CombinationType combinationType;
    private List<Card> cards;
    private CardDenomination mainCardDenomination1;
    private CardDenomination mainCardDenomination2;

    public void setCombinationType(CombinationType combinationType) {
        this.combinationType = combinationType;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public CombinationType getCombinationType() {
        return combinationType;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setMainCardDenomination1(CardDenomination mainCardDenomination1) {
        this.mainCardDenomination1 = mainCardDenomination1;
    }

    public void setMainCardDenomination2(CardDenomination mainCardDenomination2) {
        this.mainCardDenomination2 = mainCardDenomination2;
    }

    public CardDenomination getMainCardDenomination1() {
        return mainCardDenomination1;
    }

    public CardDenomination getMainCardDenomination2() {
        return mainCardDenomination2;
    }

    @Override
    public int compareTo(Object o) {
        Combination cmb = (Combination) o;
        if (combinationType.ordinal() > cmb.getCombinationType().ordinal()) {
            return 1;
        }
        if (combinationType.ordinal() < cmb.getCombinationType().ordinal()) {
            return -1;
        }
        Collections.sort(cards);
        Collections.sort(cmb.getCards());
        if (combinationType.equals(CombinationType.STRAIGHT) ||
                combinationType.equals(CombinationType.STRAIGHT_FLUSH)) {
            if (cards.get(0).getCardDenomination().ordinal() > cmb.getCards().get(0).getCardDenomination().ordinal()) {
                return 1;
            }
            if (cards.get(0).getCardDenomination().ordinal() > cmb.getCards().get(0).getCardDenomination().ordinal()) {
                return -1;
            }
            return 0;
        }
        if (combinationType.equals(CombinationType.FOUR_OF_A_KIND) ||
                combinationType.equals(CombinationType.FULL_HOUSE) ||
                combinationType.equals(CombinationType.TWO_PAIRS)) {
            int twoMainCardsCompare = compareTwoMainCards(this, cmb);
            if (twoMainCardsCompare != 0) {
                return twoMainCardsCompare;
            }
        }
        if (combinationType.equals(CombinationType.THREE_OF_A_KIND) ||
                combinationType.equals(CombinationType.ONE_PAIR)) {
            int oneMainCardsCompare = compareOneMainCards(this, cmb);
            if (oneMainCardsCompare != 0) {
                return oneMainCardsCompare;
            }
        }
        return linearCompare(cards, cmb.getCards());
    }

    private int linearCompare(List<Card> cards1, List<Card> cards2) {
        for (int i = cards1.size() - 1; i >= 0; i--) {
            if (cards1.get(i).getCardDenomination().ordinal() > cards2.get(i).getCardDenomination().ordinal()) {
                return 1;
            }
            if (cards1.get(i).getCardDenomination().ordinal() < cards2.get(i).getCardDenomination().ordinal()) {
                return -1;
            }
        }
        return 0;
    }

    private int compareTwoMainCards(Combination combination1, Combination combination2) {
        if (combination1.getMainCardDenomination1().ordinal() > combination2.getMainCardDenomination1().ordinal()) {
            return 1;
        } else if (combination1.getMainCardDenomination1().ordinal() < combination2.getMainCardDenomination1().ordinal()) {
            return -1;
        } else if (combination1.getMainCardDenomination2().ordinal() > combination2.getMainCardDenomination2().ordinal()) {
            return 1;
        } else if (combination1.getMainCardDenomination2().ordinal() < combination2.getMainCardDenomination2().ordinal()) {
            return -1;
        }
        return 0;
    }

    private int compareOneMainCards(Combination combination1, Combination combination2) {
        if (combination1.getMainCardDenomination1().ordinal() > combination2.getMainCardDenomination1().ordinal()) {
            return 1;
        } else if (combination1.getMainCardDenomination1().ordinal() < combination2.getMainCardDenomination1().ordinal()) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "|| " + cards.get(0) + " ||" +
                cards.get(1) + " ||" +
                cards.get(2) + " ||" +
                cards.get(3) + " ||" +
                cards.get(4) + " ||";
    }
}
