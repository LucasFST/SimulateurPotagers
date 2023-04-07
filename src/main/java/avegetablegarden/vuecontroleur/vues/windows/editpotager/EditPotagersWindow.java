package avegetablegarden.vuecontroleur.vues.windows.editpotager;

import avegetablegarden.modele.potagers.Potager;
import avegetablegarden.modele.potagers.SimulateurPotager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditPotagersWindow extends JFrame {
    final SimulateurPotager simulateurPotager;
    private JLabel potagerNameLabel;

    public EditPotagersWindow(SimulateurPotager simulateurPotager) {
        super("Éditer les potagers");
        this.simulateurPotager = simulateurPotager;
        initWindow();
    }

    private void initWindow() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);

        // each line is a potager
        // different columns : edit name, edit color, delete
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        List<Potager> potagers = simulateurPotager.getListePotagers();

        for (Potager potager : potagers) {
            JPanel potagersPanel = new JPanel();
            potagersPanel.setLayout(new GridLayout(1, simulateurPotager.getNbPotagers()));
            JPanel potagerPanel = new JPanel();
            potagerPanel.setLayout(new GridLayout(2, 3));

            potagerNameLabel = new JLabel(potager.getName());
            potagerPanel.add(potagerNameLabel);
            potagerPanel.add(new JLabel(""));
            potagerPanel.add(new JLabel(""));
            JButton editNameButton = setEditNameButton(potager);
            potagerPanel.add(editNameButton);
            potagerPanel.add(new JButton("Éditer la couleur"));
            potagerPanel.add(new JButton("Supprimer"));
            potagersPanel.add(potagerPanel);
            panel.add(potagersPanel);
        }

        add(panel);
    }

    private JButton setEditNameButton(Potager potager) {
        JButton editNameButton = new JButton("Éditer le nom");
        editNameButton.addActionListener(e -> {
            EditName editNameWindow = new EditName(potager);
            editNameWindow.setVisible(true);
            editNameWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    potagerNameLabel.setText(potager.getName());
                }
            });
        });
        return editNameButton;
    }
}
