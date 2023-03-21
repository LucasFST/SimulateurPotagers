package modele;

import java.util.Observable;
import java.util.Vector;

import static java.lang.Thread.*;

public class Ordonnanceur extends Observable implements Runnable {

    private static Ordonnanceur ordonnanceur;

    // design pattern singleton
    public static Ordonnanceur getOrdonnanceur() {
        if (ordonnanceur == null) {
            ordonnanceur = new Ordonnanceur();
        }
        return ordonnanceur;
    }

    private SimulateurPotager simulateurPotager;

    private long pause;
    private Vector<Runnable> lst = new Vector<Runnable>(); // liste synchronisée




    public void start(long _pause) {
        pause = _pause;
        new Thread(this).start();
    }

    public void add(Runnable r) {lst.add(r);}

    @Override
    public void run() {
        boolean update = true;

        while(true) {

            for (Runnable r : lst) {
                r.run();
            }

            if (update) {
                setChanged();
                notifyObservers();
                //update = false;
            }

            //update = true; // TODO : variable à déporter pour découpler le raffraichissement de la simulation
            try {
                sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
