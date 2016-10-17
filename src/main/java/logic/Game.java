package logic;

import comparators.PlayerCombinationComparator;
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
        while (playerList.size() > 1) {
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

            //Thread.sleep(1000);
            ConsoleDrawer.draw(this);

            //DISTRIBUTION
            distribution();


            //BETS(PRE FLOP)
            playerList.nextPlayer();
            playerList.beginBets(playerList.getCurrentPlayer());
            if(!makeDecisions()) {
                Player winner = playerList.getWinner();
                winner.setMoneyStack(winner.getMoneyStack() + pot);
                roundInitialization();
                continue;
            }


            //FLOP
            table.add(cardDeck.getCard());
            table.add(cardDeck.getCard());
            table.add(cardDeck.getCard());
            //Thread.sleep(1000);
            ConsoleDrawer.draw(this);

            //BETS
            playerList.beginBets(smallBlindPlayer);
            if(!makeDecisions()) {
                Player winner = playerList.getWinner();
                winner.setMoneyStack(winner.getMoneyStack() + pot);
                roundInitialization();
                continue;
            }

            //TURN
            table.add(cardDeck.getCard());
            //Thread.sleep(1000);
            ConsoleDrawer.draw(this);

            //BETS
            playerList.beginBets(smallBlindPlayer);
            if(!makeDecisions()) {
                Player winner = playerList.getWinner();
                winner.setMoneyStack(winner.getMoneyStack() + pot);
                roundInitialization();
                continue;
            }

            //RIVER
            table.add(cardDeck.getCard());
            //Thread.sleep(1000);
            ConsoleDrawer.draw(this);

            //BETS
            playerList.beginBets(smallBlindPlayer);
            if(!makeDecisions()) {
                Player winner = playerList.getWinner();
                winner.setMoneyStack(winner.getMoneyStack() + pot);
                roundInitialization();
                continue;
            }

            //SHOWDOWN
            List<Player> winners = roundWinners();
            for (Player player: winners) {
                System.out.println(player.getHighestCombination().getCombinationType() + " " + player.getHighestCombination());
                player.setMoneyStack(player.getMoneyStack() + pot / winners.size());
            }

            //NEW ROUND
            roundInitialization();
        }
        ConsoleDrawer.drawWinner(this);
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

    private boolean makeDecisions() {
        do {
            pot += playerList.getCurrentPlayer().decision(currentBet);
            if (playerList.inRoundPlayersSize() == 1) {
                return false;
            }
        } while (playerList.nextPlayer());
        return true;
    }

    private List<Player> roundWinners() {
        List<Card> cards = new ArrayList<>();
        List<Player> winners = new ArrayList<>();
        cards.addAll(table);
        for (Player player: playerList.getAllPlayers()) {
            if (player.isPlayingHand()) {
                cards.add(player.getHand().getFirstCard());
                cards.add(player.getHand().getSecondCard());
                player.setHighestCombination(CombinationChecker.highestCombination(cards));
                winners.add(player);
            }
            cards.remove(player.getHand().getFirstCard());
            cards.remove(player.getHand().getSecondCard());
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
        smallBlind = smallBlind * 2;
        bigBlind = bigBlind * 2;
        playerList.nextRound();
        pot = 0;
        table.clear();
        currentBet = 0;
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
