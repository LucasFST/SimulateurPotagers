package modele;

import java.util.Observable;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class Ordonnanceur extends Observable implements Runnable {

    private static Ordonnanceur ordonnanceur;
    private final Vector<Runnable> lst = new Vector<>(); // liste synchronisée
    private SimulateurPotager simulateurPotager;
    private long pause;

    // design pattern singleton
    public static Ordonnanceur getInstance() {
        if (ordonnanceur == null) {
            ordonnanceur = new Ordonnanceur();
        }
        return ordonnanceur;
    }

    public void start(long _pause) {
        pause = _pause;
        new Thread(this).start();
    }

    public void add(Runnable r) {
        lst.add(r);
    }

    @Override
    public void run() {
        boolean update = true;

        while (true) {

            for (Runnable r : lst) {
                r.run();
            }

            if (update) {
                setChanged();
                notifyObservers();
                //update = false;
            }

            //update = true; // TODO : variable à déporter pour découpler le rafraichissement de la simulation
            try {
                sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
