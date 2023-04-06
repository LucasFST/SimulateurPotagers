package vueControleur.vues.components;

import modele.legumes.Varietes;
import vueControleur.icon.IconNames;
import vueControleur.icon.IconRepository;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ButtonsPanel {
    private static final Font panelFont = new Font("Arial", Font.BOLD, 14);
    private static final Font subPanelFont = new Font("Arial", Font.BOLD, 12);
    private JPanel buttonsPanel;

    private Actions currentAction = null;
    private Varietes currentVariete = null;


    private JLabel initTitle() {
        JLabel panelTitle = new JLabel("Actions");
        panelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTitle.setFont(panelFont);
        panelTitle.setMinimumSize(new Dimension(100, 20));
        return panelTitle;
    }

    private JPanel initPanel() {
        JPanel buttons = new JPanel();
        buttons.setBackground(Color.WHITE);
        buttons.setPreferredSize(new Dimension(130, 200));
        int padding = 10;
        buttons.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        return buttons;
    }

    private void addPaddingBetweenComponents(JPanel panel) {
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private JButton getPlantingButton() {
        JButton plantingButton = new JButton("Planter");
        plantingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        plantingButton.setFont(subPanelFont);
        plantingButton.setEnabled(true);
        plantingButton.addActionListener(e -> {
            // Passer en mode plantation
            currentAction = Actions.PLANTER;
            // Ouvrir la fenêtre de plantation ici
            ImageIcon[] icones = new ImageIcon[Varietes.values().length];
            for (int i = 0; i < Varietes.values().length; i++) {
                icones[i] = IconRepository.getInstance().getIcone(IconNames.values()[i]);
            }
            int optionSelected = JOptionPane.showOptionDialog(null, "Fenêtre de plantation",
                    "Planter", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, icones, null);
            switch (optionSelected) {
                //Les IconNames sont dans le même ordre que les Varietes dans l'enum
                case 0 -> currentVariete = Varietes.values()[0];
                case 1 -> currentVariete = Varietes.values()[1];
                default -> currentVariete = null;
            }
        });
        return plantingButton;
    }

    private JButton getWateringButton() {
        JButton wateringButton = new JButton("Arroser");
        wateringButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        wateringButton.setFont(subPanelFont);
        wateringButton.setEnabled(true);
        wateringButton.addActionListener(e -> {
            // Passer en mode arrosoir
            currentAction = Actions.ARROSER;
        });
        return wateringButton;
    }

    private JButton getHarvestingButton() {
        JButton harvestingButton = new JButton("Récolter");
        harvestingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        harvestingButton.setFont(subPanelFont);
        harvestingButton.setEnabled(true);
        harvestingButton.addActionListener(e -> {
            // Passer en mode récolte
            currentAction = Actions.RECOLTER;
        });
        return harvestingButton;
    }

    private void addPaddingBetweenSubComponents(JPanel panel) {
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    private void initButtonsPanel() {
        JButton plantingButton;
        JButton wateringButton;
        JButton harvestingButton;
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.setBackground(Color.WHITE);

        addPaddingBetweenSubComponents(buttonsPanel);

        plantingButton = getPlantingButton();
        buttonsPanel.add(plantingButton);

        addPaddingBetweenSubComponents(buttonsPanel);

        wateringButton = getWateringButton();
        buttonsPanel.add(wateringButton);

        addPaddingBetweenSubComponents(buttonsPanel);

        harvestingButton = getHarvestingButton();
        buttonsPanel.add(harvestingButton);
    }

    public JPanel getButtonsPanel() {
        JPanel panel = initPanel();

        JLabel panelTitle = initTitle();
        panel.add(panelTitle);

        addPaddingBetweenComponents(panel);

        initButtonsPanel();
        panel.add(buttonsPanel);

        return panel;
    }

    public Actions getAction() {
        return this.currentAction;
    }

    public Varietes getCurrentVariete() {
        return this.currentVariete;
    }
}
