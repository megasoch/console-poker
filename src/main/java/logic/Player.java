package logic;

import AI.PokerAI;
import enums.PlayerDecision;
import enums.PlayerType;

/**
 * Created by megasoch on 12.10.2016.
 */
public class Player {
    private String name;
    private PlayerType playerType;
    private int moneyStack;
    private int stagedBet;
    private Hand hand;
    private PokerAI ai;
    private PlayerDecision playerDecision;

    public Player() {
        this.moneyStack = 1000;
    }

    public Player(String name, PlayerType playerType) {
        this.name = name;
        this.playerType = playerType;
        this.moneyStack = 1000;
        this.stagedBet = 0;
        this.playerDecision = PlayerDecision.PRE_BET;
    }

    public int bet(int betSize) {
        if (this.moneyStack >= betSize) {
            this.moneyStack -= betSize;
            this.stagedBet += betSize;
            return betSize;
        }
        int ret = this.moneyStack;
        this.stagedBet += this.moneyStack;
        this.moneyStack = 0;
        return ret;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public boolean isPlayingHand() {
        if (playerDecision.equals(PlayerDecision.FOLD)) {
            return false;
        }
        return true;
    }

    public boolean isActive() {
        return moneyStack > 0;
    }

    public void fold() {
        playerDecision = PlayerDecision.FOLD;
    }

    public void check() {
        playerDecision = PlayerDecision.CHECK;
    }

    public void call(int betSize) {
        playerDecision = PlayerDecision.CALL;
        stagedBet += betSize;
        moneyStack -= betSize;
    }

    public int decision(int currentBet) {
        int addBet = currentBet - stagedBet;
        if (addBet == 0) {
            check();
            return 0;
        }
        if (addBet > 0 && moneyStack >= addBet) {
            call(addBet);
            return addBet;
        }
        fold();
        return 0;
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

    public Hand getHand() {
        return hand;
    }

    public PokerAI getAi() {
        return ai;
    }

    public int getStagedBet() {
        return stagedBet;
    }

    public PlayerDecision getPlayerDecision() {
        return playerDecision;
    }
}
