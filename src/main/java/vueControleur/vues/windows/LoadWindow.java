package vue;

import modele.save_load.SaveAndLoad;
import vueControleur.VueManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LoadWindow extends JFrame implements ActionListener {

    private final JButton loadButton;

    private final VueManager vueManager;

    public LoadWindow(VueManager vueManager) {
        super("Charger un simulateur de potager");
        this.vueManager = vueManager;
        initWindow();

        // Ajout du bouton de chargement
        loadButton = addButton();

        // Affichage de la fenêtre
        this.pack();
        this.setVisible(true);
    }

    private static void createFolderIfNotExists(File savesFolder) {
        if (!savesFolder.exists()) {
            savesFolder.mkdir();
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

    private JButton addButton() {
        final JButton loadButton;
        loadButton = new JButton("Charger");
        loadButton.addActionListener(this);
        add(loadButton);
        return loadButton;
    }

    private void initWindow() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 200));
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton) {
            // Ouverture de la boîte de dialogue pour sélectionner le fichier de sauvegarde

            java.io.File savesFolder = new java.io.File("saves");
            createFolderIfNotExists(savesFolder);

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(savesFolder);
            fileChooser.setDialogTitle("Sélectionner un fichier de sauvegarde");

            filterFileByAvgExtension(fileChooser);

            processReturnVal(fileChooser);
        }
    }

    private void processReturnVal(JFileChooser fileChooser) {
        int returnVal = fileChooser.showOpenDialog(LoadWindow.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // Récupération du fichier de sauvegarde sélectionné
            String filePath = fileChooser.getSelectedFile().getPath();

            // Chargement du fichier de sauvegarde
            SaveAndLoad.loadIfFileExists(filePath);

            // Fermeture de la fenêtre de chargement
            dispose();
        }
    }
}
