package logic;

import enums.PlayerDecision;
import enums.PlayerType;
import utils.DecisionInputer;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/**
 * Created by megasoch on 12.10.2016.
 */
public class Player {
    private String name;
    private PlayerType playerType;
    private int moneyStack;
    private int stagedBet;
    private Hand hand;
    private PlayerDecision playerDecision;
    private Combination highestCombination;

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
        return !playerDecision.equals(PlayerDecision.FOLD) && !playerDecision.equals(PlayerDecision.OUT);
    }

    public boolean isActive() {
        return !playerDecision.equals(PlayerDecision.OUT);
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

    public int decision(int currentBet) throws IOException {
        int addBet = currentBet - stagedBet;

        if (playerDecision.equals(PlayerDecision.HAS_NO_STACK_FOR_BET)) {
            return 0;
        }

        if (addBet == 0) {
            check();
            return 0;
        }

        if(playerType.equals(PlayerType.PLAYER)) {
            try {
                switch (DecisionInputer.decision(addBet, moneyStack)) {
                    case FOLD:
                        fold();
                        return 0;
                    case CHECK:
                        check();
                        return 0;
                    case CALL:
                        call(addBet);
                        return addBet;
                }
            } catch (InputMismatchException e) {
                decision(currentBet);
            } catch (NoSuchElementException e) {
                System.out.println("Process stopped!");
            }
        }


        if (addBet > 0 && moneyStack >= addBet) {
            call(addBet);
            return addBet;
        }
        fold();
        return 0;
    }

    public void preRoundInitialize() {
        stagedBet = 0;
        hand = null;
        playerDecision = PlayerDecision.PRE_BET;
        highestCombination = null;
    }

    public String getName() {
        return name;
    }

    public int getMoneyStack() {
        return moneyStack;
    }

    public void setMoneyStack(int moneyStack) {
        this.moneyStack = moneyStack;
    }

    public Hand getHand() {
        return hand;
    }

    public int getStagedBet() {
        return stagedBet;
    }

    public PlayerDecision getPlayerDecision() {
        return playerDecision;
    }

    public Combination getHighestCombination() {
        return highestCombination;
    }

    public void setHighestCombination(Combination highestCombination) {
        this.highestCombination = highestCombination;
    }

    public void setPlayerDecision(PlayerDecision playerDecision) {
        this.playerDecision = playerDecision;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
