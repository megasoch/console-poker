package logic;

import AI.PokerAI;
import enums.PlayerType;

/**
 * Created by megasoch on 12.10.2016.
 */
public class Player {
    private String name;
    private PlayerType playerType;
    private int moneyStack;
    private int currentBet;
    private Hand hand;
    private boolean active; //Player in game
    private boolean playingHand; //Player is playing his hand
    private PokerAI ai;

    public Player() {
        this.moneyStack = 1000;
    }

    public Player(String name, PlayerType playerType) {
        this.name = name;
        this.playerType = playerType;
        this.moneyStack = 1000;
        this.currentBet = 0;
        this.active = true;
        this.playingHand = true;
    }

    public int bet(int betSize) {
        if (this.moneyStack >= betSize) {
            this.moneyStack -= betSize;
            this.currentBet += betSize;
            return betSize;
        }
        int ret = this.moneyStack;
        this.currentBet += this.moneyStack;
        this.moneyStack = 0;
        return ret;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public boolean isPlayingHand() {
        return playingHand;
    }

    public void setPlayingHand(boolean playingHand) {
        this.playingHand = playingHand;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void fold() {

    }

    public void check() {

    }

    public void call() {

    }

    public void decision() {

    }

    public String getName() {
        return name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public int getMoneyStack() {
        return moneyStack;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public Hand getHand() {
        return hand;
    }

    public PokerAI getAi() {
        return ai;
    }
}
