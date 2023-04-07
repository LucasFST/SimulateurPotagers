package avegetablegarden.modele.potagers.cases;

import avegetablegarden.modele.legumes.Varietes;
import avegetablegarden.modele.potagers.Potager;
import avegetablegarden.vuecontroleur.vues.components.Actions;

import java.io.Serializable;

public class CaseNonCultivable extends Case implements Serializable {
    public CaseNonCultivable(Potager potager) {
        super(potager);
    }

    @Override
    public String actionUtilisateur(Actions action, Varietes varietes) {
        return "Case non cultivable, vous ne pouvez rien faire ici";
    }

    @Override
    public void run() {
        // Do nothing
    }
}
