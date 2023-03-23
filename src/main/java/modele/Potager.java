package modele;

import modele.environnement.Case;
import modele.environnement.CaseCultivable;
import modele.environnement.CaseNonCultivable;

import java.awt.*;
import java.util.Random;

public class Potager {
    private Case[][] grilleCases = new Case[SimulateurPotager.SIZE_X][SimulateurPotager.SIZE_Y]; // permet de récupérer une entité à partir de ses coordonnées

    public Potager() {
        initialisationDesCases();
    }

    private Case[][] getPlateau() {
        return grilleCases;
    }
    private void initialisationDesCases() {
        // murs extérieurs horizontaux
        for (int x = 0; x < SimulateurPotager.SIZE_X; x++) {
            setCase(new CaseNonCultivable(this), new Point(x, 0));
            setCase(new CaseNonCultivable(this), new Point(x, 9));
        }

        // murs extérieurs verticaux
        for (int y = 1; y < SimulateurPotager.SIZE_Y - 1; y++) {
            setCase(new CaseNonCultivable(this), new Point(0, y));
            setCase(new CaseNonCultivable(this), new Point(19, y));
        }

        setCase(new CaseNonCultivable(this), new Point(2, 6));
        setCase(new CaseNonCultivable(this), new Point(3, 6));

        Random rnd = new Random();

        for (int x = 5; x < 15; x++) {
            for (int y = 3; y < 7; y++) {
                CaseCultivable cc = new CaseCultivable(this);
                setCase(cc, new Point(x, y));
                if (rnd.nextBoolean()) {
                    cc.actionUtilisateur();
                }
                Ordonnanceur.getInstance().add(cc);
            }
        }
    }

    public void actionUtilisateur(Point p) {
        if (grilleCases[p.x][p.y] != null) {
            grilleCases[p.x][p.y].actionUtilisateur();
        }
    }

    private void setCase(Case e, Point p) {
        grilleCases[p.x][p.y] = e;
        //map.put(e, new Point(x, y));
    }

    public Case getCase(Point p) {
        //Case retour = null;
        if (p.x < 0 || p.x >= SimulateurPotager.SIZE_X || p.y < 0 || p.y >= SimulateurPotager.SIZE_Y) {
            return null;
        }
        return grilleCases[p.x][p.y];
    }
}
