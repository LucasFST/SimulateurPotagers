package a_vegetable_garden.vue_controleur.vues.windows.edit_potager;

import a_vegetable_garden.modele.Ordonnanceur;
import a_vegetable_garden.modele.potagers.Potager;
import a_vegetable_garden.modele.potagers.SimulateurPotager;

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

    private JButton getEditColorButton(int potagerId) {
        JButton editColorButton = new JButton("Éditer la couleur");
        editColorButton.addActionListener(e -> {
            //color picker
            Color color = JColorChooser.showDialog(null, "Choisir une couleur", this.simulateurPotager.getPotager(potagerId).getButtonColor());
            if (color != null) {
                this.simulateurPotager.getPotager(potagerId).setButtonColor(color);
                JOptionPane.showMessageDialog(null, "Couleur modifiée");
            }
        });
        return editColorButton;
    }

    private JButton setDeleteButton(int potagerId) {
        JButton deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(e -> {
            int input = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr ? Le potager sera définitivement supprimé.", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
            if (input == 0) {
                this.simulateurPotager.supprimerPotager(potagerId);
                JOptionPane.showMessageDialog(null, "Potager supprimé");
                Ordonnanceur.getInstance().removeRunnable((Runnable) simulateurPotager.getPotager(potagerId));
                drawMainPanel();
            }
        });

        return deleteButton;
    }

    private void initWindow() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
        drawMainPanel();
    }

    private void drawMainPanel() {
        //set window background color
        getContentPane().removeAll();

        JPanel panel = new JPanel();
        if (simulateurPotager.getNbPotagers() == 0) {
            panel.add(new JLabel("Vous n'avez pas encore de potager"));
            add(panel);
            revalidate();
            return;
        }

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

            JButton editColorButton = getEditColorButton(potager.getId());
            potagerPanel.add(editColorButton);

            JButton deleteButton = setDeleteButton(potager.getId());
            potagerPanel.add(deleteButton);

            potagersPanel.add(potagerPanel);
            panel.add(potagersPanel);

            add(panel);
            revalidate();
        }
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
