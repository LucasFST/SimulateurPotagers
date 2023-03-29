package vueControleur.vues.components;

import modele.player.Inventory;

import javax.swing.*;
import java.awt.*;

public class InfoPanel {
    private JLabel nbCoins;

    private static JLabel initTitle() {
        JLabel panelTitle = new JLabel("Informations :");
        panelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTitle.setFont(new Font("Arial", Font.BOLD, 14));
        panelTitle.setMinimumSize(new Dimension(100, 20));
        return panelTitle;
    }

    private static JPanel initPanel() {
        JPanel infos = new JPanel();
        infos.setBackground(Color.WHITE);
        infos.setPreferredSize(new Dimension(130, 200));
        infos.setLayout(new BoxLayout(infos, BoxLayout.Y_AXIS));
        return infos;
    }

    private static void addPaddingBetweenComponents(JPanel panel) {
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private static JLabel initCoinsLabel() {
        JLabel coinsLabel = new JLabel("Pièces : ");
        coinsLabel.setFont(new Font("Arial", Font.BOLD, 12));
        coinsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return coinsLabel;
    }

    public JPanel getInfoPanel() {
        JPanel panel = initPanel();

        JLabel panelTitle = initTitle();
        panel.add(panelTitle);

        addPaddingBetweenComponents(panel);

        JLabel coinsLabel = initCoinsLabel();
        panel.add(coinsLabel);

        initNbCoins();
        panel.add(nbCoins);

        addPaddingBetweenComponents(panel);

        JLabel meteo = new JLabel("Météo : ");
        meteo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(meteo);


        return panel;
    }

    private void initNbCoins() {
        nbCoins = new JLabel();
        updateNbCoins();
        nbCoins.setAlignmentX(Component.CENTER_ALIGNMENT);
        nbCoins.setMaximumSize(new Dimension(100, 20));
    }

    public void updateInfos() {
        updateNbCoins();
    }

    private void updateNbCoins() {
        String coins = String.valueOf(Inventory.getInstance().getNbCoins());
        nbCoins.setText(coins);
    }
}
