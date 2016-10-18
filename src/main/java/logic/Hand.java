package logic;

/**
 * Created by megasoch on 13.10.2016.
 */
public class Hand {
    private Card firstCard;
    private Card secondCard;

    public void setFirstCard(Card firstCard) {
        this.firstCard = firstCard;
    }

    public void setSecondCard(Card secondCard) {
        this.secondCard = secondCard;
    }

    public Card getFirstCard() {
        return firstCard;
    }

    public Card getSecondCard() {
        return secondCard;
    }

    @Override
    public String toString() {
        if (this == null) {
            return "";
        }
        return "|| " + firstCard + " || " + secondCard + " ||";
    }
}
