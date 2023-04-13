package a_vegetable_garden.vue_controleur;

import a_vegetable_garden.modele.Singleton;
import a_vegetable_garden.modele.potagers.Actions;
import a_vegetable_garden.modele.save_load.SaveAndLoad;
import a_vegetable_garden.vue_controleur.icon.IconNames;
import a_vegetable_garden.vue_controleur.icon.IconRepository;
import a_vegetable_garden.vue_controleur.vues.VueControleurEnsemblePotagers;
import a_vegetable_garden.vue_controleur.vues.VueControleurPotager;
import a_vegetable_garden.vue_controleur.vues.windows.LoadWindow;
import a_vegetable_garden.vue_controleur.vues.windows.SaveWindow;
import a_vegetable_garden.vue_controleur.vues.windows.edit_potager.EditPotagersWindow;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public final class VueManager extends JFrame implements Singleton {
    private static VueManager instance = null;
    private VueControleurEnsemblePotagers vueControleurEnsemblePotagers;

    private VueManager() {
        super("A vegetable garden");

        warnSaveOnClose();

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

    public VueControleurEnsemblePotagers getVueControleurEnsemblePotagers() {
        return vueControleurEnsemblePotagers;
    }

    public void setVueControleurEnsemblePotagers(VueControleurEnsemblePotagers vueControleurEnsemblePotagers) {
        this.vueControleurEnsemblePotagers = vueControleurEnsemblePotagers;
        reset();
        getContentPane().add(vueControleurEnsemblePotagers);
        revalidate();
        repaint();
        setVisible(true);
    }

    private void warnSaveOnClose() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int input = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr ? Les données non-enregistrées seront perdues.", "Confirmation de fermeture", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (input == JOptionPane.OK_OPTION) {
                    Logger.getGlobal().info("Fermeture de l'application");
                    dispose();
                    System.exit(0);
                } else {
                    Logger.getGlobal().info("Annulation de la fermeture de l'application");
                    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    private void initWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initMenuBar();

        setSize(800, 600);
        setLocationRelativeTo(null);

        setVisible(true);

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

    public void showErrorWindow(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public void showWarningWindow(String warningMessage) {
        JOptionPane.showMessageDialog(this, warningMessage, "Attention", JOptionPane.WARNING_MESSAGE);
    }

    public void updatePotagerList() {
        vueControleurEnsemblePotagers.updatePotagersName();
    }

    public void setDefaultCursor() {
        setCursor(Cursor.getDefaultCursor());
    }

    public void changeCursor(Actions action) {
        ImageIcon imageIcon = null;
        switch (action) {
            case PLANTER -> imageIcon = IconRepository.getInstance().getIcone(IconNames.PLANTER);
            case ARROSER -> imageIcon = IconRepository.getInstance().getIcone(IconNames.ARROSER);
            case RECOLTER -> imageIcon = IconRepository.getInstance().getIcone(IconNames.RECOLTER);
            default -> {
                setDefaultCursor();
                return;
            }
        }
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(imageIcon.getImage(), new Point(8, 8), "potager action cursor");
        setCursor(cursor);
    }
}
