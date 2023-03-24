package vueControleur.vues;

import modele.Potager;
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
public class VueControleurPotager extends JPanel implements Observer {
    // Window properties

    private final IconRepository icones = IconRepository.getInstance();
    private final Potager potager; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)

    // taille de la grille affichée
    private final int sizeX;
    private final int sizeY;

    private JLabel[][] cases; // cases graphiques (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)


    public VueControleurPotager(Potager pota) {
        sizeX = Potager.SIZE_X;
        sizeY = Potager.SIZE_Y;
        potager = pota;
        this.setLayout(new BorderLayout());

        addComponents();
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


    private void addComponents() {
        add(getGridPanel(), BorderLayout.CENTER);
        add(new TimeSlider(), BorderLayout.SOUTH);
        add(getInfoPanel(), BorderLayout.EAST);
    }

    private JPanel getInfoPanel() {
        JPanel infos = new JPanel();

        JTextField jtf = new JTextField("infos diverses"); // TODO inclure dans mettreAJourAffichage ...
        jtf.setEditable(false);
        infos.add(jtf);
        return infos;
    }

    private void addListenerCases() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                final int xx = x; // constantes utiles au fonctionnement de la classe anonyme
                final int yy = y;
                cases[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        potager.actionUtilisateur(new Point(xx, yy));
                    }
                });
            }
        }
    }

    private JComponent getGridPanel() {
        GridLayout gridLayout = new GridLayout(sizeY, sizeX);
        gridLayout.setHgap(0);
        gridLayout.setVgap(0);
        JComponent grilleJLabels = new JPanel(gridLayout); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille


        cases = new JLabel[sizeX][sizeY];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                JLabel _case = new JLabel();
                _case.setIcon(icones.getIcone(VIDE));
                cases[x][y] = _case; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(_case);
            }
        }

        addListenerCases();
        return grilleJLabels;
    }

    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    private void updateDisplay() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                updateCase(x, y);
            }
        }
    }

    private void updateCase(int x, int y) {
        if (potager.getPlateau()[x][y] instanceof CaseCultivable) {
            setLegumeCase(x, y);
        } else if (potager.getPlateau()[x][y] instanceof CaseNonCultivable) {
            cases[x][y].setIcon(icones.getIcone(MUR));
        } else {
            cases[x][y].setIcon(icones.getIcone(VIDE));
        }
    }

    private void setLegumeCase(int x, int y) {
        Legume legume = ((CaseCultivable) potager.getPlateau()[x][y]).getLegume();

        if (legume != null) {
            switch (legume.getVariete()) {
                case SALADE:
                    cases[x][y].setIcon(icones.getIcone(SALADE));
                    break;
                case CAROTTE:
                    cases[x][y].setIcon(icones.getIcone(CAROTTE));
                    break;
            }
        } else {
            cases[x][y].setIcon(icones.getIcone(TERRE));
        }

        // si transparence : images avec canal alpha + dessins manuels (voir ci-dessous + créer composant qui redéfinie paint(Graphics g)), se documenter
        //BufferedImage bi = getImage("Images/smick.png", 0, 0, 20, 20);
        //tabJLabel[x][y].getGraphics().drawImage(bi, 0, 0, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateDisplay();
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
