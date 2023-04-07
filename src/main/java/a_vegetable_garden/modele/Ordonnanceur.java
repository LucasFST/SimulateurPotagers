package a_vegetable_garden.modele;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Logger;

public class Ordonnanceur extends Observable implements Runnable, Singleton {

    public static final long DEFAULT_DELAY = 500;
    private static final long UI_DELAY = 1000 / 60; // 1000 / fps
    private static Ordonnanceur ordonnanceur; // singleton
    private final Vector<Runnable> runnables = new Vector<>(); // liste synchronisée
    Timer timer;
    private long delayMs;

    // design pattern singleton
    public static Ordonnanceur getInstance() {
        if (ordonnanceur == null) {
            ordonnanceur = new Ordonnanceur();
            ordonnanceur.setDelay(DEFAULT_DELAY);
        }
        return ordonnanceur;
    }

    private static void sleepUiUpdate() {
        try {
            Thread.sleep(UI_DELAY);
        } catch (InterruptedException e) {
            Logger.getLogger("Ordonnanceur").warning("InterruptedException");
            Thread.currentThread().interrupt();
        }
    }

    public void resetRunnables() {
        getInstance().runnables.clear();
    }

    public void start() {
        new Thread(this).start();
        setAutoUpdateObservers();
    }

    public void addRunnable(Runnable r) {
        runnables.add(r);
    }

    @Override
    public void run() {
        for (Runnable r : runnables) {
            r.run();
        }
    }

    private void setTimer() {
        cancelTimer();
        createAndStartTimer();
    }

    private void createAndStartTimer() {
        timer = new Timer();

        // si pause, on ne lance pas le timer
        if (delayMs == 0) {
            Logger.getLogger("Ordonnanceur").info("Pause");
            return;
        }

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
            if (Thread.currentThread().isInterrupted()) {
                Logger.getLogger("Ordonnanceur").info("Thread interrupted");
                break;
            }
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
