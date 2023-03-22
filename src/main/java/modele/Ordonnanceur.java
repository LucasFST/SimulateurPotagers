package modele;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class Ordonnanceur extends Observable implements Runnable {

    private static Ordonnanceur ordonnanceur;

    private final Vector<Runnable> lst = new Vector<>(); // liste synchronisée
    Timer timer;
    private SimulateurPotager simulateurPotager;
    private long delayMs;

    // design pattern singleton
    public static Ordonnanceur getInstance() {
        if (ordonnanceur == null) {
            ordonnanceur = new Ordonnanceur();
            if (ordonnanceur.delayMs == 0)
                ordonnanceur.delayMs = 10000;
            ordonnanceur.setTimer();
        }
        return ordonnanceur;
    }

    public void start() {
        new Thread(this).start();
    }

    public void add(Runnable r) {
        lst.add(r);
    }

    @Override
    public void run() {
        for (Runnable r : lst) {
            r.run();
        }

        update();
    }

    private void setTimer() {
        if (this.timer != null) {
            timer.cancel();
        }

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Ordonnanceur.this.run();
            }
        }, delayMs, delayMs);
    }

    private void update() {
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
