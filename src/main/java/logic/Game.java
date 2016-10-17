package logic;

import draw.ConsoleDrawer;
import utils.CombinationChecker;
import utils.PlayersLoader;

import java.io.IOException;
import java.util.*;

/**
 * Created by megasoch on 12.10.2016.
 */
public class Game {
    private int smallBlind;
    private int bigBlind;
    private PlayerList playerList;
    private CardDeck cardDeck;
    private Player dealer;
    private Player smallBlindPlayer;
    private int pot;
    private ArrayList<Card> table;
    private int currentBet;

    public Game() throws IOException {
        this.smallBlind = 10;
        this.bigBlind = 20;
        this.pot = 0;
        this.playerList = new PlayerListImpl(PlayersLoader.loadPlayers());
        this.cardDeck = new CardDeck();
        this.dealer = playerList.getCurrentPlayer();
        this.table = new ArrayList<>();
        this.currentBet = 0;
    }

    public void run() throws InterruptedException, IOException {
        //POT == 0
        pot = 0;

        //GET CARD DECK
        //SHUFFLE
        cardDeck.prepareCardDeck();

        ConsoleDrawer.draw(this);

        //BLINDS
        playerList.nextPlayer();
        smallBlindPlayer = playerList.getCurrentPlayer();
        pot += playerList.getCurrentPlayer().bet(smallBlind);

        playerList.nextPlayer();
        pot += playerList.getCurrentPlayer().bet(bigBlind);
        currentBet = bigBlind;

        Thread.sleep(1000);
        ConsoleDrawer.draw(this);

        //DISTRIBUTION
        distribution();

        //BETS(PRE FLOP)
        playerList.nextPlayer();
        playerList.beginBets(playerList.getCurrentPlayer());
        makeDecisions();

        //FLOP
        table.add(cardDeck.getCard());
        table.add(cardDeck.getCard());
        table.add(cardDeck.getCard());
        Thread.sleep(1000);
        ConsoleDrawer.draw(this);

        //BETS
        playerList.beginBets(smallBlindPlayer);
        makeDecisions();

        //TURN
        table.add(cardDeck.getCard());
        Thread.sleep(1000);
        ConsoleDrawer.draw(this);

        //BETS
        playerList.beginBets(smallBlindPlayer);
        makeDecisions();

        //RIVER
        table.add(cardDeck.getCard());
        Thread.sleep(1000);
        ConsoleDrawer.draw(this);

        //BETS
        playerList.beginBets(smallBlindPlayer);
        makeDecisions();


        //SHOWDOWN
        roundWinners();
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
            pot += playerList.getCurrentPlayer().decision(currentBet);
        } while (playerList.nextPlayer());
    }

    private List<Player> roundWinners() {
        Map<Combination, Player> result = new TreeMap<>();
        List<Card> cards = new ArrayList<>();
        cards.addAll(table);
        for (Player player: playerList.getAllPlayers()) {
            if (player.isPlayingHand() && player.isActive()) {
                cards.add(player.getHand().getFirstCard());
                cards.add(player.getHand().getSecondCard());
                result.put(CombinationChecker.highestCombination(cards), player);
            }
            cards.remove(player.getHand().getFirstCard());
            cards.remove(player.getHand().getSecondCard());
        }
        List<Player> winners = new ArrayList<>();
        for (Combination combination: result.keySet()) {
            System.out.println(combination.getCombinationType());
        }
        return winners;
    }

    public PlayerList getPlayerList() {
        return playerList;
    }

    public int getPot() {
        return pot;
    }

    public ArrayList<Card> getTable() {
        return table;
    }
}
