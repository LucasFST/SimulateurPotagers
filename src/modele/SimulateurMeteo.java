package modele;

import modele.Ordonnanceur;
import modele.SimulateurPotager;

public class SimulateurMeteo implements Runnable {
    private SimulateurPotager simPot;
    public SimulateurMeteo(SimulateurPotager _simPot) {
        Ordonnanceur.getOrdonnanceur().add(this);
        simPot = _simPot;

    }

    @Override
    public void run() {
        // TODO
    }
}
