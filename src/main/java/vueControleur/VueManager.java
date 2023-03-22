package vueControleur;

import vueControleur.vues.VueControleurPotager;

import javax.swing.*;

public class VueManager extends JFrame {
    private static VueManager instance = null;
    private VueControleurPotager vueControleurPotager;

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

        setVisible(true);
    }

    public void setVueControleurPotager(VueControleurPotager vueControleurPotager) {
        this.vueControleurPotager = vueControleurPotager;
        getContentPane().removeAll();
        getContentPane().add(vueControleurPotager);
    }
}
