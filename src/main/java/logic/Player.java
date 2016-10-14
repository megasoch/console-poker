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
    private Hand hand;
    private PokerAI ai;

    public Player() {
        this.moneyStack = 1000;
    }

    public Player(String name, PlayerType playerType) {
        this.name = name;
        this.playerType = playerType;
        this.moneyStack = 1000;
    }

    public int bet(int betSize) {
        if (this.moneyStack >= betSize) {
            this.moneyStack -= betSize;
            return betSize;
        }
        int ret = this.moneyStack;
        this.moneyStack = 0;
        return ret;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
