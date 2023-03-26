package vueControleur;

import modele.Singleton;
import vueControleur.vues.VueControleurEnsemblePotagers;
import vueControleur.vues.VueControleurPotager;
import vueControleur.vues.windows.SaveWindow;

import javax.swing.*;

public final class VueManager extends JFrame implements Singleton {
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

    private static void addSaveMenu(JMenu fichierMenu) {
        JMenuItem sauvegarderMenuItem = new JMenuItem("Sauvegarder");
        sauvegarderMenuItem.addActionListener(e -> {
            // Ouvrir la fenêtre de sauvegarde ici
            SaveWindow saveWindow = new SaveWindow(VueManager.getInstance().vueControleurEnsemblePotagers.simulateurPotager);
            saveWindow.setVisible(true);
        });
        fichierMenu.add(sauvegarderMenuItem);
    }

    private void initWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initMenuBar();

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fichierMenu = new JMenu("Fichier");
        menuBar.add(fichierMenu);
        addSaveMenu(fichierMenu);
        addLoadMenu(fichierMenu);
        setJMenuBar(menuBar);
    }

    private void addLoadMenu(JMenu fichierMenu) {
        JMenuItem chargerMenuItem = new JMenuItem("Charger");
        chargerMenuItem.addActionListener(e -> {
            vue.LoadWindow loadWindow = new vue.LoadWindow(this);
        });
        fichierMenu.add(chargerMenuItem);
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
