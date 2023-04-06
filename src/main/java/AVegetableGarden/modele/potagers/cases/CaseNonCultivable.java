package AVegetableGarden.modele.potagers.cases;

import AVegetableGarden.modele.legumes.Varietes;
import AVegetableGarden.modele.potagers.Potager;
import AVegetableGarden.vueControleur.vues.components.Actions;

import java.io.Serializable;

public class CaseNonCultivable extends Case implements Serializable {
    public CaseNonCultivable(Potager _potager) {
        super(_potager);
    }

    @Override
    public String actionUtilisateur(Actions action, Varietes varietes) {
        // TODO
        return "Case non cultivable, vous ne pouvez rien faire ici";
    }

    @Override
    public void run() {
        // TODO
    }
}
