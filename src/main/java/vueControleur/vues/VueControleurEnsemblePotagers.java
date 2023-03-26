package vueControleur.vues;

import modele.potagers.SimulateurPotager;
import vueControleur.VueManager;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueControleurEnsemblePotagers extends JPanel implements Observer, VueControleur {

    SimulateurPotager simulateurPotager;

    private JButton[] listeBoutonsPotagers;

    public VueControleurEnsemblePotagers(SimulateurPotager simulateurPotager) {
        super();
        this.simulateurPotager = simulateurPotager;
        this.setLayout(new BorderLayout());
        addComponents();
    }

    public void addComponents() {
        add(new JLabel("Liste des potagers"), BorderLayout.NORTH);
        add(getButtonsPotagers(), BorderLayout.CENTER);
        add(new TimeSlider(), BorderLayout.SOUTH);

    }

    @Override
    public void addEventListeners() {
        addEventListenerButtonsPotagers();
    }

    @Override
    public void updateDisplay() {

    }

    public void addEventListenerButtonsPotagers() {
        for (int i = 0; i < listeBoutonsPotagers.length; i++) {
            int potagerIndex = i;
            listeBoutonsPotagers[i].addActionListener(e -> {
                VueControleurPotager vueControleurPotager = new VueControleurPotager(simulateurPotager.getListePotagers().get(potagerIndex), this);
                VueManager.getInstance().setVueControleurPotager(vueControleurPotager);
            });
        }
    }

    public JComponent getButtonsPotagers() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        listeBoutonsPotagers = new JButton[simulateurPotager.getNbPotagers()];
        for (int i = 0; i < simulateurPotager.getNbPotagers(); i++) {
            listeBoutonsPotagers[i] = new JButton("Potager " + i);
            panel.add(listeBoutonsPotagers[i]);
        }
        addEventListeners();
        return panel;
    }


    @Override
    public void update(Observable observable, Object o) {
        updateDisplay();
    }
}
