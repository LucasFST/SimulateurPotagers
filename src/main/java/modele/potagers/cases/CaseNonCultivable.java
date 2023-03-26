package modele.potagers.cases;

import modele.potagers.Potager;

import java.io.Serializable;

public class CaseNonCultivable extends Case implements Serializable {
    public CaseNonCultivable(Potager _potager) {
        super(_potager);
    }

    @Override
    public void actionUtilisateur() {
        // TODO
    }

    @Override
    public void run() {
        // TODO
    }
}
