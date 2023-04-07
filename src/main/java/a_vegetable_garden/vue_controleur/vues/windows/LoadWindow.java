package a_vegetable_garden.vue_controleur.vues.windows;

import a_vegetable_garden.modele.Ordonnanceur;
import a_vegetable_garden.modele.potagers.Potager;
import a_vegetable_garden.modele.save_load.SaveAndLoad;

import javax.swing.*;
import java.io.File;
import java.util.logging.Logger;

public class LoadWindow {
    public LoadWindow() {
        openFileChooser();
    }

    private static void createFolderIfNotExists(File savesFolder) {
        if (!savesFolder.mkdir()) {
            Logger.getGlobal().info("Le dossier de sauvegarde existe déjà");
        }
    }

    private static void filterFileByAvgExtension(JFileChooser fileChooser) {
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().toLowerCase().endsWith(".avg") || file.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Fichiers de sauvegarde";
            }
        });
    }

    private void openFileChooser() {
        File savesFolder = new File("saves");
        createFolderIfNotExists(savesFolder);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(savesFolder);
        fileChooser.setDialogTitle("Sélectionner un fichier de sauvegarde");

        filterFileByAvgExtension(fileChooser);

        processReturnVal(fileChooser);
    }

    private void processReturnVal(JFileChooser fileChooser) {
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // Récupération du fichier de sauvegarde sélectionné
            String filePath = fileChooser.getSelectedFile().getPath();

            // réinitialisation du simulateur
            Ordonnanceur.getInstance().resetRunnables();
            Potager.resetCompteurID();

            // Chargement du fichier de sauvegarde
            SaveAndLoad.loadIfFileExists(filePath);

            // Fermeture de la fenêtre de chargement
        }
    }
}
