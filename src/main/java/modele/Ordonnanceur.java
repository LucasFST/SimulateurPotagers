package modele;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class Ordonnanceur extends Observable implements Runnable {

    public static final long DEFAULT_DELAY = 10000;
    private static final long UI_DELAY = 1000 / 60; // 1000 / fps
    private static Ordonnanceur ordonnanceur; // singleton
    private final Vector<Runnable> lst = new Vector<>(); // liste synchronisée
    Timer timer;
    private long delayMs;

    // design pattern singleton
    public static Ordonnanceur getInstance() {
        if (ordonnanceur == null) {
            ordonnanceur = new Ordonnanceur();
            if (ordonnanceur.delayMs == 0)
                ordonnanceur.delayMs = DEFAULT_DELAY;
            ordonnanceur.setTimer();
        }
        return ordonnanceur;
    }

    private static void sleepUiUpdate() {
        try {
            Thread.sleep(UI_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        new Thread(this).start();
        setAutoUpdateObservers();
    }

    public void addRunnable(Runnable r) {
        lst.add(r);
    }

    @Override
    public void run() {
        for (Runnable r : lst) {
            r.run();
        }
    }

    private void setTimer() {
        cancelTimer();
        createAndStartTimer();
    }

    private void createAndStartTimer() {
        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Ordonnanceur.this.run();
            }
        }, delayMs, delayMs);
    }

    private void cancelTimer() {
        if (this.timer != null) {
            timer.cancel();
        }
    }

    private void setAutoUpdateObservers() {
        while (true) {
            updateObservers();
            sleepUiUpdate();
        }
    }

    private void updateObservers() {
        setChanged();
        notifyObservers();
    }

    public long getDelay() {
        return delayMs;
    }

    public void setDelay(long delayMs) {
        this.delayMs = delayMs;
        setTimer();
    }
}
