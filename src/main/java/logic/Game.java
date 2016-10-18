package logic;

import comparators.PlayerCombinationComparator;
import draw.ConsoleDrawer;
import enums.PlayerDecision;
import timer.BlindTimer;
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
    private int pot;
    private ArrayList<Card> table;
    private int currentBet;
    private Player dealer;
    private Player smallBlindPlayer;
    private Player bigBlindPlayer;

    public Game() throws IOException {
        this.smallBlind = 10;
        this.bigBlind = 20;
        this.pot = 0;
        this.playerList = new PlayerListImpl(PlayersLoader.loadPlayers());
        this.cardDeck = new CardDeck();
        this.table = new ArrayList<>();
        this.currentBet = 0;
        this.dealer = playerList.getDealer();
    }


    public void run() throws InterruptedException, IOException {
        Timer timer = new Timer();
        BlindTimer blindTimer = new BlindTimer();
        timer.schedule(blindTimer, BlindTimer.getBlindTime(), BlindTimer.getBlindTime());

        while (playerList.size() > 1) {

            //GET CARD DECK
            //SHUFFLE
            cardDeck.prepareCardDeck();

            //BLINDS
            blinds();

            //DISTRIBUTION
            distribution();

            ConsoleDrawer.draw(this);
            Thread.sleep(2000);

            //BETS(PRE FLOP)
            playerList.goToDealer();
            if (!makeDecisions()) {
                Player winner = playerList.getWinner();
                winner.setMoneyStack(winner.getMoneyStack() + pot);
                roundInitialization();
                continue;
            }

            playerList.updateStates();

            //FLOP
            table.add(cardDeck.getCard());
            table.add(cardDeck.getCard());
            table.add(cardDeck.getCard());
            ConsoleDrawer.draw(this);
            Thread.sleep(2000);

            //BETS
            playerList.goToDealer();
            if (!makeDecisions()) {
                Player winner = playerList.getWinner();
                winner.setMoneyStack(winner.getMoneyStack() + pot);
                roundInitialization();
                continue;
            }

            playerList.updateStates();

            //TURN
            table.add(cardDeck.getCard());
            ConsoleDrawer.draw(this);
            Thread.sleep(2000);

            //BETS
            playerList.goToDealer();
            if (!makeDecisions()) {
                Player winner = playerList.getWinner();
                winner.setMoneyStack(winner.getMoneyStack() + pot);
                roundInitialization();
                continue;
            }

            playerList.updateStates();

            //RIVER
            table.add(cardDeck.getCard());
            ConsoleDrawer.draw(this);
            Thread.sleep(2000);

            //BETS
            playerList.goToDealer();
            if (!makeDecisions()) {
                Player winner = playerList.getWinner();
                winner.setMoneyStack(winner.getMoneyStack() + pot);
                roundInitialization();
                continue;
            }

            playerList.updateStates();

            //SHOWDOWN
            distributePot(roundWinners());
            Thread.sleep(5000);

            //NEW ROUND
            roundInitialization();
        }
        ConsoleDrawer.drawWinner(this);
    }

    private void distributePot(List<Player> winners) {
        ConsoleDrawer.drawRoundWinners(winners);
        for (Player player : winners) {
            player.setMoneyStack(player.getMoneyStack() + pot / winners.size());
        }
        for (int i = 0; i < pot % winners.size(); i++) {
            winners.get(i).setMoneyStack(winners.get(i).getMoneyStack() + 1);
        }
    }
    private void blinds() {
        playerList.nextPlayer();
        smallBlindPlayer = playerList.getCurrentPlayer();
        playerList.getCurrentPlayer().setPlayerDecision(PlayerDecision.SMALL_BLIND);
        pot += playerList.getCurrentPlayer().bet(smallBlind);

        playerList.updateStates();

        playerList.nextPlayer();
        bigBlindPlayer = playerList.getCurrentPlayer();
        playerList.getCurrentPlayer().setPlayerDecision(PlayerDecision.BIG_BLIND);
        pot += playerList.getCurrentPlayer().bet(bigBlind);
        currentBet = bigBlind;

        playerList.updateStates();
    }

    private void distribution() {
        Hand[] hands = new Hand[playerList.size()];
        for (int i = 0; i < hands.length; i++) {
            hands[i] = new Hand();
        }
        for (Hand hand : hands) {
            hand.setFirstCard(cardDeck.getCard());
        }
        playerList.goToDealer();
        for (Hand hand : hands) {
            hand.setSecondCard(cardDeck.getCard());
            playerList.nextPlayer();
            playerList.getCurrentPlayer().setHand(hand);
        }
    }

    private boolean makeDecisions() throws IOException {
        int currentRoundPlayersSize = playerList.inRoundPlayersSize();
        for (int i = 0; i < currentRoundPlayersSize; i++) {
            playerList.nextPlayer();
            pot += playerList.getCurrentPlayer().decision(currentBet);
            if (playerList.inRoundPlayersSize() == 1) {
                return false;
            }
        }
        return true;
    }

    private List<Player> roundWinners() {
        List<Card> cards = new ArrayList<>();
        List<Player> winners = new ArrayList<>();
        cards.addAll(table);
        for (Player player : playerList.getAllPlayers()) {
            if (player.isPlayingHand()) {
                cards.add(player.getHand().getFirstCard());
                cards.add(player.getHand().getSecondCard());
                player.setHighestCombination(CombinationChecker.highestCombination(cards));
                winners.add(player);
                cards.remove(player.getHand().getFirstCard());
                cards.remove(player.getHand().getSecondCard());
            }
        }
        Collections.sort(winners, new PlayerCombinationComparator());
        Collections.reverse(winners);
        for (int i = 1; i < winners.size(); i++) {
            if (winners.get(i).getHighestCombination().compareTo(winners.get(i - 1).getHighestCombination()) != 0) {
                winners = winners.subList(0, i);
            }
        }
        return winners;
    }

    private void roundInitialization() {
        smallBlind = BlindTimer.getSmallBlind();
        bigBlind = BlindTimer.getBigBlind();
        playerList.nextRound();
        pot = 0;
        table.clear();
        currentBet = 0;
        dealer = playerList.getDealer();
        smallBlindPlayer = null;
        bigBlindPlayer = null;
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

    public Player getDealer() {
        return dealer;
    }

    public Player getSmallBlindPlayer() {
        return smallBlindPlayer;
    }

    public Player getBigBlindPlayer() {
        return bigBlindPlayer;
    }

    public int getSmallBlind() {
        return smallBlind;
    }

    public int getBigBlind() {
        return bigBlind;
    }
}
