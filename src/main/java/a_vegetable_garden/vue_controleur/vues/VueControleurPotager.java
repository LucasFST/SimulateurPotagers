package a_vegetable_garden.vue_controleur.vues;

import a_vegetable_garden.modele.Ordonnanceur;
import a_vegetable_garden.modele.potagers.Potager;
import a_vegetable_garden.vue_controleur.VueManager;
import a_vegetable_garden.vue_controleur.vues.components.ButtonsPanel;
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
    public final ButtonsPanel buttonsPanel;
    private final Potager potager; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)
    private final PotagerGrid potagerGrid;
    public InfoPanel infoPanel;


    public VueControleurPotager(Potager pota, VueControleurEnsemblePotagers vueControleurEnsemblePotagers) {
        super();
        buttonsPanel = new ButtonsPanel();
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
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        infoPanel = new InfoPanel();
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 50;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(infoPanel.getPanel(),c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(buttonsPanel.getPanel(),c);
        add(panel, BorderLayout.EAST);
        add(getGoBackButton(), BorderLayout.WEST);
        add(getPotagerNb(), BorderLayout.NORTH);
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
