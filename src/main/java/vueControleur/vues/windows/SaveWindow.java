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
        initWindow(simulateurPotager);

        pack();
        setVisible(true);
    }

    private JButton addSaveButton(SimulateurPotager simulateurPotager) {
        JButton saveButton = new JButton("Sauvegarder");
        saveButton.addActionListener(new SaveButtonListener(simulateurPotager));
        return saveButton;
    }

    private void initWindow(SimulateurPotager simulateurPotager) {
        JLabel filenameLabel = new JLabel("Nom du fichier :");
        filenameTextField = new JTextField();
        int padding = 10;

        JButton saveButton = addSaveButton(simulateurPotager);
        saveButton.setPreferredSize(new Dimension(0, saveButton.getPreferredSize().height));

        JPanel panel = new JPanel(new GridLayout(3, 2, padding, padding));
        panel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        panel.add(filenameLabel);
        panel.add(new JLabel());
        panel.add(filenameTextField);
        panel.add(new JLabel(".avg"));
        panel.add(saveButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(300, 150));
        this.setLocationRelativeTo(null);
    }

    private class SaveButtonListener implements ActionListener {
        final SimulateurPotager simulateurPotager;

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
