package logic;

import enums.CardDenomination;
import enums.CardSuit;

/**
 * Created by megasoch on 13.10.2016.
 */
public class Card {
    private CardDenomination cardDenomination;
    private CardSuit cardSuit;

    public Card(CardDenomination cardDenomination, CardSuit cardSuit) {
        this.cardDenomination = cardDenomination;
        this.cardSuit = cardSuit;
    }
}