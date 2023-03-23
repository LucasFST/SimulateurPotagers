package modele.environnement;

import modele.Potager;
import modele.environnement.varietes.Legume;
import modele.environnement.varietes.Salade;

public class CaseCultivable extends Case {

    private Legume legume;

    public CaseCultivable(Potager _potager) {
        super(_potager);
    }

    @Override
    public void actionUtilisateur() {
        if (legume == null) {
            legume = new Salade();

        } else {
            legume = null;
        }
    }

    public Legume getLegume() {
        return legume;
    }

    @Override
    public void run() {
        if (legume != null) {
            legume.nextStep();
        }
    }
}
