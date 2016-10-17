package logic;

import enums.CardDenomination;
import enums.CardSuit;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by megasoch on 13.10.2016.
 */

public class CardDeck {
    private LinkedList<Card> cards = new LinkedList<>();

    public CardDeck() {
    }

    public void prepareCardDeck() {
        cards.clear();
        for (CardDenomination cardDenomination: CardDenomination.values()) {
            for (CardSuit cardSuit: CardSuit.values()) {
                cards.add(new Card(cardDenomination, cardSuit));
            }
        }
        Collections.shuffle(cards);
    }

    public Card getCard() {
        return cards.poll();
    }
}
