package vue;

import modele.save_load.SaveAndLoad;
import vueControleur.VueManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JButton loadButton;

    private VueManager vueManager;

    public LoadWindow(VueManager vueManager) {
        super("Charger un simulateur de potager");
        this.vueManager = vueManager;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 200));
        this.setLocationRelativeTo(null);

        // Ajout du bouton de chargement
        JPanel panel = new JPanel(new FlowLayout());
        loadButton = new JButton("Charger");
        loadButton.addActionListener(this);
        panel.add(loadButton);
        this.getContentPane().add(panel);

        // Affichage de la fenêtre
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton) {
            // Ouverture de la boîte de dialogue pour sélectionner le fichier de sauvegarde
            JFileChooser fileChooser = new JFileChooser();
            //create saves folder if it doesn't exist
            java.io.File savesFolder = new java.io.File("saves");
            if (!savesFolder.exists()) {
                savesFolder.mkdir();
            }
            fileChooser.setCurrentDirectory(savesFolder); // dossier courant
            fileChooser.setDialogTitle("Sélectionner un fichier de sauvegarde");
            //uniquement les fichiers .avg
            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(java.io.File file) {
                    return file.getName().toLowerCase().endsWith(".avg") || file.isDirectory();
                }

                @Override
                public String getDescription() {
                    return "Fichiers de sauvegarde";
                }
            });
            int returnVal = fileChooser.showOpenDialog(LoadWindow.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                // Récupération du fichier de sauvegarde sélectionné
                String filePath = fileChooser.getSelectedFile().getPath();

                // Chargement du fichier de sauvegarde
                SaveAndLoad.loadIfFileExists(filePath);

                // Fermeture de la fenêtre de chargement
                LoadWindow.this.dispose();
            }
        }
    }
}
