package modele;

import java.util.Date;
import java.util.Observable;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class Ordonnanceur extends Observable implements Runnable {

    private static Ordonnanceur ordonnanceur;

    private final Vector<Runnable> lst = new Vector<>(); // liste synchronisée

    private SimulateurPotager simulateurPotager;

    private long delayMs;
    private Date lastUpdate = new Date();

    // design pattern singleton
    public static Ordonnanceur getInstance() {
        if (ordonnanceur == null) {
            ordonnanceur = new Ordonnanceur();
        }
        return ordonnanceur;
    }

    public void start(long _delayMs) {
        delayMs = _delayMs;
        new Thread(this).start();
    }

    public void add(Runnable r) {
        lst.add(r);
    }

    @Override
    public void run() {
        while (true) {
            for (Runnable r : lst) {
                r.run();
            }

            if (canUpdate()) {
                update();
            }

            waitDelay();
        }
    }

    private boolean canUpdate() {
        return lastUpdate.getTime() + delayMs < new Date().getTime();
    }

    private void waitDelay() {
        try {
            sleep(delayMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        setChanged();
        notifyObservers();
        lastUpdate = new Date();
    }
}
