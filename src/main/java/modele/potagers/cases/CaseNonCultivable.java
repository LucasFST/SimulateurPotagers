package modele.potagers.cases;

import modele.legumes.Varietes;
import modele.potagers.Potager;
import vueControleur.vues.components.Actions;

import java.io.Serializable;

public class CaseNonCultivable extends Case implements Serializable {
    public CaseNonCultivable(Potager _potager) {
        super(_potager);
    }

    @Override
    public void actionUtilisateur(Actions action, Varietes varietes) {
        // TODO
    }

    @Override
    public void run() {
        // TODO
    }
}
