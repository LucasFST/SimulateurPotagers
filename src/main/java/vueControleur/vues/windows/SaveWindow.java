package vueControleur.vues.windows;

import modele.potagers.SimulateurPotager;
import modele.save_load.SaveAndLoad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveWindow extends JFrame {
    private JTextField filenameTextField;

    public SaveWindow(SimulateurPotager simulateurPotager) {
        super("Sauvegarder les données");
        JLabel filenameLabel = new JLabel("Nom du fichier :");
        filenameTextField = new JTextField();

        JButton saveButton = new JButton("Sauvegarder");
        saveButton.addActionListener(new SaveButtonListener(simulateurPotager));

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(filenameLabel);
        panel.add(filenameTextField);
        panel.add(saveButton);

        getContentPane().add(panel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // centre la fenêtre sur l'écran
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private class SaveButtonListener implements ActionListener {
        SimulateurPotager simulateurPotager;

        SaveButtonListener(SimulateurPotager simulateurPotager) {
            this.simulateurPotager = simulateurPotager;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String filename = filenameTextField.getText();
            if (filename.isEmpty()) {
                JOptionPane.showMessageDialog(SaveWindow.this, "Veuillez saisir un nom de fichier valide", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String path = "saves/" + filename + ".avg";
            try {
                SaveAndLoad.save(simulateurPotager, path);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(SaveWindow.this, "Erreur lors de la sauvegarde du fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        }
    }
}
