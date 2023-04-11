package a_vegetable_garden.modele.potagers.cases;

import a_vegetable_garden.modele.legumes.Varietes;
import a_vegetable_garden.modele.potagers.Actions;
import a_vegetable_garden.modele.potagers.Potager;

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
