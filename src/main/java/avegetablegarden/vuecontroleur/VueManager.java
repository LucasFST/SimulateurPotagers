package avegetablegarden.vuecontroleur;

import avegetablegarden.modele.Singleton;
import avegetablegarden.modele.save_load.SaveAndLoad;
import avegetablegarden.vuecontroleur.vues.VueControleurEnsemblePotagers;
import avegetablegarden.vuecontroleur.vues.VueControleurPotager;
import avegetablegarden.vuecontroleur.vues.windows.LoadWindow;
import avegetablegarden.vuecontroleur.vues.windows.SaveWindow;
import avegetablegarden.vuecontroleur.vues.windows.editpotager.EditPotagersWindow;

import javax.swing.*;

public final class VueManager extends JFrame implements Singleton {
    private static VueManager instance = null;
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
        addNewSaveMenu(fichierMenu);
        JMenu editMenu = new JMenu("Édition");
        menuBar.add(editMenu);
        addEditPotagersMenu(editMenu);
        setJMenuBar(menuBar);
    }

    private void addLoadMenu(JMenu fichierMenu) {
        JMenuItem chargerMenuItem = new JMenuItem("Charger");
        chargerMenuItem.addActionListener(e -> new LoadWindow());
        fichierMenu.add(chargerMenuItem);
    }

    private void addNewSaveMenu(JMenu fichierMenu) {
        JMenuItem sauvegarderMenuItem = new JMenuItem("Nouvelle sauvegarde");
        sauvegarderMenuItem.addActionListener(e -> {
            int input = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr ? Votre sauvegarde actuelle sera perdue.", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (input != 0) {
                return;
            }
            SaveAndLoad.loadNewSave();
        });
        fichierMenu.add(sauvegarderMenuItem);
    }

    private void addEditPotagersMenu(JMenu fichierMenu) {
        JMenuItem editPotagersMenuItem = new JMenuItem("Éditer les potagers");
        editPotagersMenuItem.addActionListener(e -> {
            EditPotagersWindow editPotagersWindow = new EditPotagersWindow(VueManager.getInstance().vueControleurEnsemblePotagers.simulateurPotager);
            editPotagersWindow.setVisible(true);
        });
        fichierMenu.add(editPotagersMenuItem);
    }

    public void reset() {
        getContentPane().removeAll();
        initWindow();
    }


    public void setVueControleurPotager(VueControleurPotager vueControleurPotager) {
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

    public void showErrorWindow(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public void showWarningWindow(String warningMessage) {
        JOptionPane.showMessageDialog(this, warningMessage, "Attention", JOptionPane.WARNING_MESSAGE);
    }

    public void UpdatePotagerList() {
        vueControleurEnsemblePotagers.updatePotagersName();
    }
}
