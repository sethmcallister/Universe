package xyz.sethy.universe.utils;

import xyz.sethy.universe.Universe;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by seth on 26/06/17.
 */
public abstract class Thread {
    private boolean registered;
    private long lastExecution;

    public Thread() {
        this.registered = false;
    }

    public Thread register(Long delay) {
        if (this.registered) {
            getUniverse().throwExpection(getClass().getCanonicalName() + ": Thread already registered.");
            return this;
        }
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Thread.this.run();
                lastExecution = System.nanoTime();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, delay);
        this.registered = true;
        return this;
    }

    public abstract void run();

    protected Universe getUniverse() {
        return Universe.getInstance();
    }
}
