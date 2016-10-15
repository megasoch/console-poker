package logic;

import utils.PlayersReader;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by megasoch on 12.10.2016.
 */
public class Game {
    private final String PLAYERS_LIST_FILE = "src/main/resources/players.list";
    private int smallBlind;
    private int bigBlind;
    private PlayerList playerList;
    private CardDeck cardDeck;
    private Player dealer;
    private Player smallBlindPlayer;
    private int pot;
    private Card[] table;

    public Game() throws IOException {
        this.smallBlind = 10;
        this.bigBlind = 20;
        this.pot = 0;
        this.playerList = new PlayerListImpl(PlayersReader.read(PLAYERS_LIST_FILE));
        this.cardDeck = new CardDeck();
        this.dealer = playerList.getCurrentPlayer();
        this.table = new Card[5];
    }

    public void run() {
        //POT == 0
        this.pot = 0;

        //GET CARD DECK
        //SHUFFLE
        cardDeck.prepareCardDeck();

        //BLINDS
        this.playerList.nextPlayer();
        smallBlindPlayer = playerList.getCurrentPlayer();
        this.pot += this.playerList.getCurrentPlayer().bet(smallBlind);

        this.playerList.nextPlayer();
        this.pot += this.playerList.getCurrentPlayer().bet(bigBlind);

        //DISTRIBUTION
        distribution();

        //BETS(PRE FLOP)
        playerList.nextPlayer();
        playerList.beginBets(playerList.getCurrentPlayer());
        makeDecisions();

        //FLOP
        table[0] = cardDeck.getCard();
        table[1] = cardDeck.getCard();
        table[2] = cardDeck.getCard();

        //BETS
        playerList.beginBets(smallBlindPlayer);
        makeDecisions();

        //TURN
        table[3] = cardDeck.getCard();

        //BETS
        playerList.beginBets(smallBlindPlayer);
        makeDecisions();

        //RIVER
        table[4] = cardDeck.getCard();

        //BETS
        playerList.beginBets(smallBlindPlayer);
        makeDecisions();

        //SHOWDOWN
        //combinationChecker()
        //movePotToWinner()

        //NEW ROUND
        //roundInitialization()
    }

    private void distribution() {
        Hand[] hands = new Hand[playerList.size()];
        for (int i = 0; i < hands.length; i++) {
            hands[i] = new Hand();
        }
        for (Hand hand : hands) {
            hand.setFirstCard(cardDeck.getCard());
        }
        for (Hand hand : hands) {
            hand.setSecondCard(cardDeck.getCard());
            playerList.getCurrentPlayer().setHand(hand);
            playerList.nextPlayer();
        }
    }

    private void makeDecisions() {
        do {
            playerList.getCurrentPlayer().decision();
        } while (playerList.nextPlayer());
    }

    public int getSmallBlind() {
        return smallBlind;
    }

    public int getBigBlind() {
        return bigBlind;
    }

    public PlayerList getPlayerList() {
        return playerList;
    }

    public int getPot() {
        return pot;
    }

    public Card[] getTable() {
        return table;
    }
}
