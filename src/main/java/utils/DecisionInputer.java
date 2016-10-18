package utils;

import enums.PlayerDecision;

import java.util.Scanner;

/**
 * Created by megasoch on 18.10.2016.
 */
public class DecisionInputer {
    public static PlayerDecision decision(int addBet, int moneyStack) {
        Scanner in = new Scanner(System.in);
        int dec = 0;
        if (addBet > 0 && moneyStack >= addBet) {
            while (true) {
                System.out.println("FOLD(1) / CALL(2)");
                dec = in.nextInt();
                if (dec < 1 || dec > 2) {
                    System.out.println("Input 1 or 2");
                } else {
                    break;
                }
            }
        }
        switch (dec) {
            case 1:
                return PlayerDecision.FOLD;
            case 2:
                return PlayerDecision.CALL;
            default:
                return PlayerDecision.FOLD;
        }
    }
}
