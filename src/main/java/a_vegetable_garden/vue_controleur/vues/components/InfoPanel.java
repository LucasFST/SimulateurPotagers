package a_vegetable_garden.vue_controleur.vues.components;

import a_vegetable_garden.modele.meteo.SimulateurMeteo;
import a_vegetable_garden.modele.player.Inventory;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends Panel {
    private JLabel nbCoins;
    private JLabel actualSaisonLabel;
    private JLabel actualDayLabel;
    private JLabel actualEnsoleillement;
    private JLabel actualHumidite;
    private JLabel actualTempLabel;

    private static JLabel initCoinsLabel() {
        JLabel coinsLabel = new JLabel("Pièces : ");
        coinsLabel.setFont(subPanelFont);
        coinsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return coinsLabel;
    }

    private static JLabel initMeteoLabel() {
        JLabel meteo = new JLabel("Météo : ");
        meteo.setAlignmentX(Component.CENTER_ALIGNMENT);
        meteo.setFont(subPanelFont);
        return meteo;
    }

    public JPanel getPanel() {
        JPanel panel = initPanel();

        JLabel panelTitle = initTitle("Informations");
        panel.add(panelTitle);

        addPaddingBetweenComponents(panel);

        JLabel coinsLabel = initCoinsLabel();
        panel.add(coinsLabel);

        initNbCoins();
        panel.add(nbCoins);

        addPaddingBetweenComponents(panel);

        JLabel meteo = initMeteoLabel();
        panel.add(meteo);

        initMainPanel();
        panel.add(mainPanel);

        return panel;
    }

    protected void initMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.setBackground(Color.WHITE);
        addPaddingBetweenSubComponents(mainPanel);

        JLabel saisonLabel = new JLabel("Saison :");
        mainPanel.add(saisonLabel);

        actualSaisonLabel = new JLabel("Printemps");
        mainPanel.add(actualSaisonLabel);

        addPaddingBetweenSubComponents(mainPanel);

        JLabel dayLabel = new JLabel("Jour :");
        mainPanel.add(dayLabel);

        actualDayLabel = new JLabel("3");
        mainPanel.add(actualDayLabel);

        addPaddingBetweenSubComponents(mainPanel);

        JLabel ensoleillementLabel = new JLabel("Ensoleillement :");
        mainPanel.add(ensoleillementLabel);

        actualEnsoleillement = new JLabel("Ensoleillé");
        mainPanel.add(actualEnsoleillement);

        addPaddingBetweenSubComponents(mainPanel);


        JLabel humiditeLabel = new JLabel("Humidité :");
        mainPanel.add(humiditeLabel);

        actualHumidite = new JLabel("Normal");
        mainPanel.add(actualHumidite);

        addPaddingBetweenSubComponents(mainPanel);

        JLabel tempLabel = new JLabel("Température :");
        mainPanel.add(tempLabel);
        actualTempLabel = new JLabel("25°C");
        mainPanel.add(actualTempLabel);
    }

    private void initNbCoins() {
        nbCoins = new JLabel();
        updateNbCoins();
        nbCoins.setAlignmentX(Component.CENTER_ALIGNMENT);
        nbCoins.setMaximumSize(new Dimension(100, 20));
    }

    public void updateInfos(SimulateurMeteo meteo) {
        updateNbCoins();
        updateMeteoInfos(meteo);
    }

    private void updateMeteoInfos(SimulateurMeteo meteo) {
        actualEnsoleillement.setText(meteo.getEnsoleillement());
        actualHumidite.setText(meteo.getHumidite());
        actualSaisonLabel.setText(meteo.getSaison());
        actualDayLabel.setText(String.valueOf(meteo.getJour()));
        actualTempLabel.setText(meteo.getTemperature() + "°C");
    }

    private void updateNbCoins() {
        String coins = String.valueOf(Inventory.getInstance().getNbCoins());
        nbCoins.setText(coins);
    }
}
