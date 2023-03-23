package modele;

public class SimulateurMeteo implements Runnable {
    private SimulateurPotager simPot;

    public SimulateurMeteo(SimulateurPotager _simPot) {
        Ordonnanceur.getInstance().addRunnable(this);
        simPot = _simPot;

    }

    @Override
    public void run() {
        // TODO
    }
}
