package modele.potagers;

import modele.Ordonnanceur;
import modele.legumes.Varietes;
import modele.potagers.cases.Case;
import modele.potagers.cases.CaseCultivable;
import modele.potagers.cases.CaseNonCultivable;
import vueControleur.vues.components.Actions;

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class Potager implements Serializable {

    public static final int MAX_SIZE_X = 20;
    public static final int MAX_SIZE_Y = 10;
    private static int compteurID = 0; // permet de donner un ID unique à chaque potager
    private final int sizeX;
    private final int sizeY;
    private final int id;
    private Map<Point, Case> grilleCases = new LinkedHashMap<>(); // permet de récupérer une entité à partir de ses coordonnées

    public Potager() {
        id = compteurID++;
        sizeX = MAX_SIZE_X;
        sizeY = MAX_SIZE_Y;
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
        initialisationDesCases();
    }

    public static void resetCompteurID() {
        compteurID = 0;
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
        Case[][] plateau = new Case[sizeX][sizeY];
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                plateau[x][y] = getCase(new Point(x, y));
            }
        }
        return plateau;
    }

    private void initialisationDesCases() {
        grilleCases = new LinkedHashMap<>();
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
                    cc.actionUtilisateur(Actions.PLANTER, Varietes.values()[rnd.nextInt(Varietes.values().length)]);
                }
                Ordonnanceur.getInstance().addRunnable(cc);
            }
        }

        setCase(new CaseNonCultivable(this), new Point(2, 6));
        setCase(new CaseNonCultivable(this), new Point(3, 6));
    }

    public void actionUtilisateur(Actions action, Varietes variete, Point p) {
        if (getCase(p) != null) {
            getCase(p).actionUtilisateur(action, variete);
        }
    }

    private void setCase(Case e, Point p) {
        grilleCases.put(p, e);
    }

    public Case getCase(Point p) {
        if (p.x < 0 || p.x >= sizeX || p.y < 0 || p.y >= sizeY) {
            return null;
        }
        return grilleCases.get(p);
    }
}
