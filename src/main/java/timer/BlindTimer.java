package timer;

import java.util.TimerTask;

/**
 * Created by megasoch on 19.10.2016.
 */
public class BlindTimer extends TimerTask {
    private final int START_SMALL_BLIND = 10;
    private final int START_BIG_BLIND = 20;
    private final static int BLIND_TIME = 5 * 60 * 1000;

    private static int smallBlind;
    private static int bigBlind;

    public BlindTimer() {
        this.smallBlind = START_SMALL_BLIND;
        this.bigBlind = START_BIG_BLIND;
    }

    @Override
    public void run() {
        smallBlind = smallBlind * 2;
        bigBlind = bigBlind * 2;
    }

    public static int getSmallBlind() {
        return smallBlind;
    }

    public static int getBigBlind() {
        return bigBlind;
    }

    public static int getBlindTime() {
        return BLIND_TIME;
    }
}
