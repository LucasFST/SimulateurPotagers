package vueControleur.vues;

import modele.SimulateurPotager;
import modele.environnement.CaseCultivable;
import modele.environnement.CaseNonCultivable;
import modele.environnement.varietes.Legume;
import vueControleur.icon.IconRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import static vueControleur.icon.IconNames.*;


/**
 * Cette classe a deux fonctions :
 * (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 * (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle
 */
public class VueControleurPotager extends JFrame implements Observer {
    // Window properties
    public static final int WIDTH = 540;
    public static final int HEIGHT = 250;
    public static final String WINDOW_TITLE = "A vegetable garden - Potager";

    private final IconRepository icones = IconRepository.getInstance();
    private final SimulateurPotager simulateurPotager; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)

    // taille de la grille affichée
    private final int sizeX;
    private final int sizeY;

    private JLabel[][] tabJLabel; // cases graphiques (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)


    public VueControleurPotager(SimulateurPotager simPota) {
        sizeX = SimulateurPotager.SIZE_X;
        sizeY = SimulateurPotager.SIZE_Y;
        simulateurPotager = simPota;

        placerLesComposantsGraphiques();
        //ajouterEcouteurClavier(); // si besoin
    }
/*
    private void ajouterEcouteurClavier() {
        addKeyListener(new KeyAdapter() { // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un objet qui correspond au controleur dans MVC
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {  // on regarde quelle touche a été pressée
                    case KeyEvent.VK_LEFT : Controle4Directions.getInstance().setDirectionCourante(Direction.gauche); break;
                    case KeyEvent.VK_RIGHT : Controle4Directions.getInstance().setDirectionCourante(Direction.droite); break;
                    case KeyEvent.VK_DOWN : Controle4Directions.getInstance().setDirectionCourante(Direction.bas); break;
                    case KeyEvent.VK_UP : Controle4Directions.getInstance().setDirectionCourante(Direction.haut); break;
                }
            }
        });
    }
*/


    private void placerLesComposantsGraphiques() {
        initWindow();

        initInfoPanel();

        initCases();
    }

    private void initInfoPanel() {
        JPanel infos = new JPanel();

        JTextField jtf = new JTextField("infos diverses"); // TODO inclure dans mettreAJourAffichage ...
        jtf.setEditable(false);
        infos.add(jtf);

        add(infos, BorderLayout.EAST);
    }

    private void addListenerCases() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                final int xx = x; // constantes utiles au fonctionnement de la classe anonyme
                final int yy = y;
                tabJLabel[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        simulateurPotager.actionUtilisateur(xx, yy);
                    }
                });
            }
        }
    }

    private void initCases() {
        JComponent grilleJLabels = new JPanel(new GridLayout(sizeY, sizeX)); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille

        tabJLabel = new JLabel[sizeX][sizeY];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                JLabel jlab = new JLabel();

                tabJLabel[x][y] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(jlab);
            }
        }
        add(grilleJLabels, BorderLayout.CENTER);

        addListenerCases();
    }

    private void initWindow() {
        setTitle(WINDOW_TITLE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // centre la fenêtre
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre
    }


    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    private void mettreAJourAffichage() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                updateCase(x, y);
            }
        }
    }

    private void updateCase(int x, int y) {
        if (simulateurPotager.getPlateau()[x][y] instanceof CaseCultivable) {
            setLegumeCase(x, y);
        } else if (simulateurPotager.getPlateau()[x][y] instanceof CaseNonCultivable) {
            tabJLabel[x][y].setIcon(icones.getIcone(MUR));
        } else {
            tabJLabel[x][y].setIcon(icones.getIcone(VIDE));
        }
    }

    private void setLegumeCase(int x, int y) {
        Legume legume = ((CaseCultivable) simulateurPotager.getPlateau()[x][y]).getLegume();

        if (legume != null) {
            switch (legume.getVariete()) {
                case SALADE:
                    tabJLabel[x][y].setIcon(icones.getIcone(SALADE));
                    break;
                case CAROTTE:
                    tabJLabel[x][y].setIcon(icones.getIcone(CAROTTE));
                    break;
            }
        } else {
            tabJLabel[x][y].setIcon(icones.getIcone(TERRE));
        }

        // si transparence : images avec canal alpha + dessins manuels (voir ci-dessous + créer composant qui redéfinie paint(Graphics g)), se documenter
        //BufferedImage bi = getImage("Images/smick.png", 0, 0, 20, 20);
        //tabJLabel[x][y].getGraphics().drawImage(bi, 0, 0, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        mettreAJourAffichage();
        /*
        SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mettreAJourAffichage();
                    }
                }); 
        */

    }
}
