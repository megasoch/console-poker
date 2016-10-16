package logic;

import enums.CardDenomination;
import enums.CardSuit;

/**
 * Created by megasoch on 13.10.2016.
 */
public class Card implements Comparable {
    private CardDenomination cardDenomination;
    private CardSuit cardSuit;

    public Card(CardDenomination cardDenomination, CardSuit cardSuit) {
        this.cardDenomination = cardDenomination;
        this.cardSuit = cardSuit;
    }

    public CardDenomination getCardDenomination() {
        return cardDenomination;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    @Override
    public String toString() {
        return cardDenomination + "_" + cardSuit;
    }

    @Override
    public int compareTo(Object o) {
        int ord1 = this.cardDenomination.ordinal();
        int ord2 = ((Card)o).getCardDenomination().ordinal();
        if (ord1 == ord2) {
            return 0;
        }
        return ord1 > ord2 ? 1 : -1;
    }
}
