package vueControleur;

import vueControleur.vues.VueControleurEnsemblePotagers;
import vueControleur.vues.VueControleurPotager;

import javax.swing.*;

public final class VueManager extends JFrame {
    private static VueManager instance = null;
    private VueControleurPotager vueControleurPotager;
    private VueControleurEnsemblePotagers vueControleurEnsemblePotagers;

    private VueManager() {
        super("A vegetable garden");

        initWindow();
    }

    public static VueManager getInstance() {
        if (instance == null) {
            instance = new VueManager();
        }
        return instance;
    }

    private void initWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    public void reset() {
        getContentPane().removeAll();
        initWindow();
    }


    public void setVueControleurPotager(VueControleurPotager vueControleurPotager) {
        this.vueControleurPotager = vueControleurPotager;
        reset();
        getContentPane().add(vueControleurPotager);
        revalidate();
        repaint();
        setVisible(true);
    }

    public void setVueControleurEnsemblePotagers(VueControleurEnsemblePotagers vueControleurEnsemblePotagers) {
        this.vueControleurEnsemblePotagers = vueControleurEnsemblePotagers;
        reset();
        getContentPane().add(vueControleurEnsemblePotagers);
        revalidate();
        repaint();
        setVisible(true);
    }
}
