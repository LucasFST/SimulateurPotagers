package modele.potagers;

import modele.Ordonnanceur;
import modele.environnement.Case;
import modele.environnement.CaseCultivable;
import modele.environnement.CaseNonCultivable;

import java.awt.*;
import java.util.Random;

public class Potager {

    public static final int MAX_SIZE_X = 20;
    public static final int MAX_SIZE_Y = 10;
    private static int compteurID = 0; // permet de donner un ID unique à chaque potager
    private int sizeX;
    private int sizeY;
    private int id;
    private Case[][] grilleCases; // permet de récupérer une entité à partir de ses coordonnées

    public Potager() {
        id = compteurID++;
        sizeX = MAX_SIZE_X;
        sizeY = MAX_SIZE_Y;
        grilleCases = new Case[sizeX][sizeY];
        initialisationDesCases();
    }

    public Potager(int size_x, int size_y) {
        //size_x et size_y doivent être supérieurs à 0 et inférieurs à MAX_SIZE_X et MAX_SIZE_Y
        if (size_x > MAX_SIZE_X || size_y > MAX_SIZE_Y)
            throw new IllegalArgumentException("Taille du potager trop grande");
        if (size_x < 0 || size_y < 0)
            throw new IllegalArgumentException("Taille du potager trop petite");
        id = compteurID++;
        sizeX = size_x;
        sizeY = size_y;
        grilleCases = new Case[size_x][size_y];
        initialisationDesCases();
    }

    public int getId() {
        return id;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Case[][] getPlateau() {
        return grilleCases;
    }

    private void initialisationDesCases() {
        // murs extérieurs horizontaux
        for (int x = 0; x < sizeX; x++) {
            setCase(new CaseNonCultivable(this), new Point(x, 0));
            setCase(new CaseNonCultivable(this), new Point(x, sizeY - 1));
        }

        // murs extérieurs verticaux
        for (int y = 1; y < sizeY - 1; y++) {
            setCase(new CaseNonCultivable(this), new Point(0, y));
            setCase(new CaseNonCultivable(this), new Point(sizeX - 1, y));
        }

        Random rnd = new Random();

        for (int x = 1; x < sizeX - 1; x++) {
            for (int y = 1; y < sizeY - 1; y++) {
                CaseCultivable cc = new CaseCultivable(this);
                setCase(cc, new Point(x, y));
                if (rnd.nextBoolean()) {
                    cc.actionUtilisateur();
                }
                Ordonnanceur.getInstance().addRunnable(cc);
            }
        }

        setCase(new CaseNonCultivable(this), new Point(2, 6));
        setCase(new CaseNonCultivable(this), new Point(3, 6));
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
        if (p.x < 0 || p.x >= sizeX || p.y < 0 || p.y >= sizeY) {
            return null;
        }
        return grilleCases[p.x][p.y];
    }
}
