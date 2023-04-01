package vueControleur.vues;

import modele.Ordonnanceur;
import modele.legumes.Legume;
import modele.potagers.Potager;
import modele.potagers.cases.CaseCultivable;
import modele.potagers.cases.CaseNonCultivable;
import vueControleur.VueManager;
import vueControleur.icon.IconRepository;
import vueControleur.vues.components.ButtonsPanel;
import vueControleur.vues.components.InfoPanel;
import vueControleur.vues.components.TimeSlider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import static vueControleur.icon.IconNames.*;


/**
 * Cette classe a deux fonctions :
 * (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 * (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle
 */
public class VueControleurPotager extends JPanel implements Observer, VueControleur {
    private final IconRepository icones = IconRepository.getInstance();
    private final Potager potager; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)
    // taille de la grille affichée
    private final int sizeX;
    private final int sizeY;
    private final VueControleurEnsemblePotagers vueControleurEnsemblePotagers;
    private JLabel[][] cases; // cases graphiques (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)

    private InfoPanel infoPanel;
    private ButtonsPanel buttonsPanel;

    public VueControleurPotager(Potager pota, VueControleurEnsemblePotagers vueControleurEnsemblePotagers) {
        super();
        sizeX = pota.getSizeX();
        sizeY = pota.getSizeY();
        potager = pota;

        this.vueControleurEnsemblePotagers = vueControleurEnsemblePotagers;

        this.setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addComponents();

        Ordonnanceur.getInstance().addObserver(this);
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


    public void addComponents() {
        add(getGridPanel(), BorderLayout.CENTER);
        add(new TimeSlider(), BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        infoPanel = new InfoPanel();
        panel.add(infoPanel.getInfoPanel());

        buttonsPanel = new ButtonsPanel();
        panel.add(buttonsPanel.getButtonsPanel());

        add(panel, BorderLayout.EAST);
        add(getGoBackButton(), BorderLayout.WEST);
        add(getPotagerNb(), BorderLayout.NORTH);
    }

    @Override
    public void addEventListeners() {
        addListenerCases();
    }

    private JComponent getPotagerNb() {
        return new JLabel("Potager n°" + potager.getId());
    }

    private void addListenerCases() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                final int xx = x; // constantes utiles au fonctionnement de la classe anonyme
                final int yy = y;
                cases[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        potager.actionUtilisateur( buttonsPanel.getAction(), buttonsPanel.getCurrentVariete() ,new Point(xx, yy));
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

        addEventListeners();
        return grilleJLabels;
    }

    public JComponent getGoBackButton() {
        JButton goBack = new JButton("Retour");
        goBack.addActionListener(e -> VueManager.getInstance().setVueControleurEnsemblePotagers(vueControleurEnsemblePotagers));
        return goBack;
    }

    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    @Override
    public void updateDisplay() {
        infoPanel.updateInfos(vueControleurEnsemblePotagers.simulateurPotager.simulateurMeteo);
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
                case SALADE -> setIconCase(x, y, icones.getIcone(SALADE));
                case CAROTTE -> setIconCase(x, y, icones.getIcone(CAROTTE));
                default -> cases[x][y].setIcon(icones.getIcone(TERRE));
            }
        } else {
            cases[x][y].setIcon(icones.getIcone(TERRE));
        }
    }

    private void setIconCase(int x, int y, ImageIcon icon) {
        float tintValue = 1 - ((CaseCultivable) potager.getPlateau()[x][y]).getLegumeVie();
        Color tintColor = new Color(1, 0, 0, tintValue);
        ImageIcon tintedIcon = applyTintColor(icon, tintColor);
        cases[x][y].setIcon(tintedIcon);
    }

    private ImageIcon applyTintColor(ImageIcon icon, Color tintColor) {
        BufferedImage tintedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = tintedImage.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(icon.getImage(), 0, 0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(tintColor);
        g.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
        g.dispose();
        return new ImageIcon(tintedImage);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateDisplay();
    }
}
