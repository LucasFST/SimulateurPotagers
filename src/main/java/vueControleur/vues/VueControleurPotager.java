package vueControleur.vues;

import modele.Ordonnanceur;
import modele.potagers.Potager;
import vueControleur.VueManager;
import vueControleur.vues.components.InfoPanel;
import vueControleur.vues.components.PotagerGrid;
import vueControleur.vues.components.TimeSlider;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;


/**
 * Cette classe a deux fonctions :
 * (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 * (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle
 */
public class VueControleurPotager extends JPanel implements Observer, VueControleur {
    // taille de la grille affichée
    public final VueControleurEnsemblePotagers vueControleurEnsemblePotagers;
    private final Potager potager; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)
    public InfoPanel infoPanel;
    private PotagerGrid potagerGrid;


    public VueControleurPotager(Potager pota, VueControleurEnsemblePotagers vueControleurEnsemblePotagers) {
        super();
        this.potagerGrid = new PotagerGrid(pota);
        potager = pota;

        this.vueControleurEnsemblePotagers = vueControleurEnsemblePotagers;

        this.setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addComponents();

        Ordonnanceur.getInstance().addObserver(this);
    }

    public void addComponents() {
        add(potagerGrid.getGridPanel(), BorderLayout.CENTER);
        add(new TimeSlider(), BorderLayout.SOUTH);
        infoPanel = new InfoPanel();
        add(infoPanel.getInfoPanel(), BorderLayout.EAST);
        add(getGoBackButton(), BorderLayout.WEST);
        add(getPotagerNb(), BorderLayout.NORTH);
    }

    @Override
    public void addEventListeners() {
        // PotagerGrid et InfoPanel s'occupent de leurs propres listeners
    }


    private JComponent getPotagerNb() {
        return new JLabel("Potager n°" + potager.getId());
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
        potagerGrid.updatePotagerVue(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        updateDisplay();
    }
}
