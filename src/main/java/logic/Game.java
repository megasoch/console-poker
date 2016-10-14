package logic;

import utils.PlayersReader;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by megasoch on 12.10.2016.
 */
public class Game {
    private final String PLAYERS_LIST_FILE = "/src/main/resources/players.list";
    private int smallBlind;
    private int bigBlind;
    private PlayerList playerList;
    private CardDeck cardDeck;
    private int pot;

    public Game() throws IOException {
        this.smallBlind = 10;
        this.bigBlind = 20;
        this.pot = 0;
        this.playerList = new PlayerListImpl(PlayersReader.read(PLAYERS_LIST_FILE));
        this.cardDeck = new CardDeck();
    }

    public void run() {
        //POT == 0
        this.pot = 0;

        //GET CARD DECK
        //SHUFFLE
        cardDeck.prepareCardDeck();

        //BLINDS
        pot += playerList.getSmallBlindPlayer().bet(smallBlind);
        pot += playerList.getBigBlindPlayer().bet(bigBlind);

        //DISTRIBUTION
        distribution();

        //BETS
    }

    private void distribution() {
        Hand[] hands = new Hand[playerList.size()];
        for (Hand hand: hands) {
            hand.setFirstCard(cardDeck.getCard());
        }
        for (Hand hand: hands) {
            hand.setSecondCard(cardDeck.getCard());
            playerList.getCurrentPlayer().setHand(hand);
            playerList.nextPlayer();
        }
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public PlayerList getPlayerList() {
        return playerList;
    }

    public void setPlayerList(PlayerList playerList) {
        this.playerList = playerList;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }
}
