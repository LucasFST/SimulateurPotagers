package a_vegetable_garden.vue_controleur.vues.components;

import a_vegetable_garden.modele.legumes.Varietes;
import a_vegetable_garden.vue_controleur.icon.IconNames;
import a_vegetable_garden.vue_controleur.icon.IconRepository;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends Panel{
    private Actions currentAction = null;
    private Varietes currentVariete = null;

    private JButton getPlantingButton() {
        ImageIcon plantingIcon = IconRepository.getInstance().getIcone(IconNames.PLANTER);
        JButton plantingButton = new JButton("Planter", plantingIcon);
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
        ImageIcon wateringIcon = IconRepository.getInstance().getIcone(IconNames.ARROSER);
        JButton wateringButton = new JButton("Arroser", wateringIcon);
        wateringButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        wateringButton.setFont(subPanelFont);
        wateringButton.setEnabled(true);
        wateringButton.addActionListener(e -> currentAction = Actions.ARROSER);
        return wateringButton;
    }

    private JButton getHarvestingButton() {
        ImageIcon harvestingIcon = IconRepository.getInstance().getIcone(IconNames.RECOLTER);
        JButton harvestingButton = new JButton("Récolter", harvestingIcon);
        harvestingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        harvestingButton.setFont(subPanelFont);
        harvestingButton.setEnabled(true);
        harvestingButton.addActionListener(e -> currentAction = Actions.RECOLTER);
        return harvestingButton;
    }

    protected void initMainPanel() {
        JButton plantingButton;
        JButton wateringButton;
        JButton harvestingButton;
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.setBackground(Color.WHITE);

        addPaddingBetweenSubComponents(mainPanel);

        plantingButton = getPlantingButton();
        c.gridy = 0;
        mainPanel.add(plantingButton, c);

        addPaddingBetweenSubComponents(mainPanel);

        wateringButton = getWateringButton();
        c.gridy = 1;
        mainPanel.add(wateringButton, c);

        addPaddingBetweenSubComponents(mainPanel);

        harvestingButton = getHarvestingButton();
        c.gridy = 2;
        mainPanel.add(harvestingButton, c);
    }

    public JPanel getPanel() {
        JPanel panel = initPanel(130,130);

        JLabel panelTitle = initTitle("Actions");
        panel.add(panelTitle);

        addPaddingBetweenComponents(panel);

        initMainPanel();
        panel.add(mainPanel);

        return panel;
    }

    public Actions getAction() {
        return this.currentAction;
    }

    public Varietes getCurrentVariete() {
        return this.currentVariete;
    }
}
