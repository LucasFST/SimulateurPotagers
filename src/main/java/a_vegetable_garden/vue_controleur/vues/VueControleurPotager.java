package a_vegetable_garden.vue_controleur.vues;

import a_vegetable_garden.modele.Ordonnanceur;
import a_vegetable_garden.modele.potagers.Potager;
import a_vegetable_garden.vue_controleur.VueManager;
import a_vegetable_garden.vue_controleur.vues.components.ActionsButtonsPanel;
import a_vegetable_garden.vue_controleur.vues.components.InfoPanel;
import a_vegetable_garden.vue_controleur.vues.components.PotagerGrid;
import a_vegetable_garden.vue_controleur.vues.components.TimeSlider;

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
    public final ActionsButtonsPanel buttonsPanel;
    private final Potager potager; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)
    private final PotagerGrid potagerGrid;
    public InfoPanel infoPanel;


    public VueControleurPotager(Potager pota, VueControleurEnsemblePotagers vueControleurEnsemblePotagers) {
        super();
        buttonsPanel = new ActionsButtonsPanel();
        this.potagerGrid = new PotagerGrid(pota, buttonsPanel);
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
        addPanels();
        add(getGoBackButton(), BorderLayout.WEST);
        add(getPotagerNb(), BorderLayout.NORTH);
    }

    private void addPanels() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        infoPanel = new InfoPanel();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.weighty = 1;
        panel.add(infoPanel.getPanel(), c);
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 2;
        c.weighty = 0;
        panel.add(buttonsPanel.getPanel(), c);
        add(panel, BorderLayout.EAST);
    }

    @Override
    public void addEventListeners() {
        // PotagerGrid et InfoPanel s'occupent de leurs propres listeners
    }


    private JComponent getPotagerNb() {
        return new JLabel(potager.getName());
    }


    public JComponent getGoBackButton() {
        JButton goBack = new JButton("Retour");
        goBack.addActionListener(e -> {
            VueManager.getInstance().setVueControleurEnsemblePotagers(vueControleurEnsemblePotagers);
            VueManager.getInstance().setDefaultCursor();
        });
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
