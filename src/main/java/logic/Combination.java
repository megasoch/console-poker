package logic;

import enums.CombinationType;

import java.util.Collections;
import java.util.List;

/**
 * Created by megasoch on 16.10.2016.
 */
public class Combination implements Comparable {
    private CombinationType combinationType;
    private List<Card> cards;

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

    @Override
    public int compareTo(Object o) {
        Combination cmb = (Combination)o;
        if (combinationType.ordinal() > cmb.getCombinationType().ordinal()) {
            return 1;
        }
        if (combinationType.ordinal() < cmb.getCombinationType().ordinal()) {
            return -1;
        }
        Collections.sort(cards);
        Collections.sort(cmb.getCards());
        for (int i = cards.size() - 1; i >=0; i--) {
            if (cards.get(i).getCardDenomination().ordinal() > cmb.getCards().get(i).getCardDenomination().ordinal()) {
                return 1;
            }
            if (cards.get(i).getCardDenomination().ordinal() < cmb.getCards().get(i).getCardDenomination().ordinal()) {
                return -1;
            }
        }
        return 0;
    }
}
