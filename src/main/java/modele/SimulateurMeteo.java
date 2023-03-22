package modele;

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
