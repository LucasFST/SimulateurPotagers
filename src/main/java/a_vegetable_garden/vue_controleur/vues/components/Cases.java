package a_vegetable_garden.vue_controleur.vues.components;

import a_vegetable_garden.modele.potagers.Potager;
import a_vegetable_garden.modele.potagers.cases.CaseCultivable;
import a_vegetable_garden.vue_controleur.icon.IconNames;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Cases {
    // a map of all the cases, accessible by their coordinates, values are Case
    private final Map<Point, Case> casesList;

    public Cases(int sizeX, int sizeY) {
        casesList = new HashMap<>();

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                casesList.put(new Point(x, y), new Case());
            }
        }
    }

    public Case getCase(Point point) {
        return casesList.get(point);
    }

    public void setIconCase(Point point, IconNames icon, float legumeVie, float legumeCroissance) {
        casesList.get(point).setIconLegume(icon, legumeVie, legumeCroissance);
    }

    public void setIconCase(Point point, IconNames icon) {
        // pour les cases non cultivables
        casesList.get(point).setIcon(icon);
    }

    public void updateAllCasesBackground(Potager potager) {
        for (int x = 0; x < potager.getSizeX(); x++) {
            for (int y = 0; y < potager.getSizeY(); y++) {
                updateCaseBackground(potager, x, y);
            }
        }
    }

    private void updateCaseBackground(Potager potager, int x, int y) {
        Point point = new Point(x, y);
        if (potager.getCase(point) == null) return;

        a_vegetable_garden.modele.potagers.cases.Case _case = potager.getCase(point);

        if (_case instanceof CaseCultivable caseCultivable)
            casesList.get(point).updateBackgroundAlpha(caseCultivable.getTauxHumidite());

    }
}
