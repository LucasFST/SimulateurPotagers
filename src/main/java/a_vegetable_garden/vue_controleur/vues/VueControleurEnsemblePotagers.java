package a_vegetable_garden.vue_controleur.vues;

import a_vegetable_garden.modele.Ordonnanceur;
import a_vegetable_garden.modele.player.Inventory;
import a_vegetable_garden.modele.potagers.Potager;
import a_vegetable_garden.modele.potagers.SimulateurPotager;
import a_vegetable_garden.vue_controleur.VueManager;
import a_vegetable_garden.vue_controleur.vues.components.InfoPanel;
import a_vegetable_garden.vue_controleur.vues.components.TimeSlider;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueControleurEnsemblePotagers extends JPanel implements Observer, VueControleur {

    public final SimulateurPotager simulateurPotager;

    private JButton[] listePotagersButton;
    private JButton buyPotagerButton;

    private transient InfoPanel infoPanel;


    public VueControleurEnsemblePotagers(SimulateurPotager simulateurPotager) {
        super();
        this.simulateurPotager = simulateurPotager;
        this.setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        listePotagersButton = new JButton[simulateurPotager.getNbPotagers()];
        addComponents();
        Ordonnanceur.getInstance().addObserver(this);
        updateBuyButton();
    }

    public void addComponents() {
        add(new JLabel("Liste des potagers"), BorderLayout.NORTH);
        add(getButtonsPotagers(), BorderLayout.CENTER);
        infoPanel = new InfoPanel();
        add(infoPanel.getInfoPanel(), BorderLayout.EAST);
        add(new TimeSlider(), BorderLayout.SOUTH);
    }

    @Override
    public void addEventListeners() {
        addEventListenerButtonsPotagers();
        addEventListenerBuyPotagerButton();
    }

    private void addEventListenerBuyPotagerButton() {
        buyPotagerButton.addActionListener(e -> {
            boolean isBuySuccessful = simulateurPotager.buyPotager();
            updatePotagersDisplay();

            if (!isBuySuccessful) {
                VueManager.getInstance().showWarningWindow("Vous n'avez pas assez d'argent pour acheter un potager");
            }
        });
    }

    public void updatePotagersDisplay() {
        this.removeAll();
        addComponents();
        this.revalidate();
        this.repaint();
        updateDisplay();
    }


    @Override
    public void updateDisplay() {
        infoPanel.updateInfos(simulateurPotager.simulateurMeteo);
        updateBuyButton();
        checkIfPotagerListChanged();
        checkIfColorChanged();
        updatePotagersName();
    }

    private void checkIfColorChanged() {
        for (int i = 0; i < listePotagersButton.length; i++) {
            Potager potager = simulateurPotager.getListePotagers().get(i);
            if (!potager.getButtonColor().equals(listePotagersButton[i].getBackground())) {
                setButtonColor(i, potager);
            }
        }
    }

    private void checkIfPotagerListChanged() {
        if (listePotagersButton.length != simulateurPotager.getNbPotagers()) {
            updatePotagersDisplay();
        }
    }

    private void updateBuyButton() {
        boolean hasEnoughCoins = !(Inventory.getInstance().getNbCoins() < SimulateurPotager.POTAGER_PRICE);
        buyPotagerButton.setEnabled(hasEnoughCoins);
    }

    public void updatePotagersName() {
        for (int i = 0; i < listePotagersButton.length; i++) {
            Potager potager = simulateurPotager.getListePotagers().get(i);
            listePotagersButton[i].setText(potager.getName());
        }
    }

    public void addEventListenerButtonsPotagers() {
        for (int i = 0; i < listePotagersButton.length; i++) {
            int potagerIndex = i;
            listePotagersButton[i].addActionListener(e -> {
                VueControleurPotager vueControleurPotager = new VueControleurPotager(simulateurPotager.getListePotagers().get(potagerIndex), this);
                VueManager.getInstance().setVueControleurPotager(vueControleurPotager);
            });
        }
    }

    public JComponent getButtonsPotagers() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        listePotagersButton = new JButton[simulateurPotager.getNbPotagers()];
        for (int i = 0; i < simulateurPotager.getNbPotagers(); i++) {
            Potager potager = simulateurPotager.getListePotagers().get(i);
            listePotagersButton[i] = new JButton(potager.getName());
            setButtonColor(i, potager);
            panel.add(listePotagersButton[i]);
        }

        buyPotagerButton = new JButton("Acheter un potager : " + SimulateurPotager.POTAGER_PRICE + " pièces");
        buyPotagerButton.setBackground(Color.cyan);
        panel.add(buyPotagerButton);
        addEventListeners();
        return panel;
    }

    private void setButtonColor(int i, Potager potager) {
        listePotagersButton[i].setBackground(potager.getButtonColor());
        listePotagersButton[i].setForeground(new Color(255 - potager.getButtonColor().getRed(), 255 - potager.getButtonColor().getGreen(), 255 - potager.getButtonColor().getBlue()));
    }

    @Override
    public void update(Observable observable, Object o) {
        updateDisplay();
    }
}
