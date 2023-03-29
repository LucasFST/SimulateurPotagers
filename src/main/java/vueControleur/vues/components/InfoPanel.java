package vueControleur.vues.components;

import modele.meteo.SimulateurMeteo;
import modele.player.Inventory;

import javax.swing.*;
import java.awt.*;

public class InfoPanel {
    private static final Font panelFont = new Font("Arial", Font.BOLD, 14);
    private static final Font subPanelFont = new Font("Arial", Font.BOLD, 12);
    private JLabel nbCoins;
    private JPanel meteoInfos;
    private JLabel actualSaisonLabel;
    private JLabel actualDayLabel;
    private JLabel actualEnsoleillement;

    private JLabel actualHumidite;
    private JLabel actualTempLabel;

    private static JLabel initTitle() {
        JLabel panelTitle = new JLabel("Informations");
        panelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTitle.setFont(panelFont);
        panelTitle.setMinimumSize(new Dimension(100, 20));
        return panelTitle;
    }

    private static JPanel initPanel() {
        JPanel infos = new JPanel();
        infos.setBackground(Color.WHITE);
        infos.setPreferredSize(new Dimension(130, 200));
        int padding = 10;
        infos.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        infos.setLayout(new BoxLayout(infos, BoxLayout.Y_AXIS));
        return infos;
    }

    private static void addPaddingBetweenComponents(JPanel panel) {
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

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

        JLabel meteo = initMeteoLabel();
        panel.add(meteo);

        initMeteoInfos();
        panel.add(meteoInfos);


        return panel;
    }

    private void initMeteoInfos() {
        meteoInfos = new JPanel();
        meteoInfos.setLayout(new BoxLayout(meteoInfos, BoxLayout.Y_AXIS));
        meteoInfos.setAlignmentX(Component.CENTER_ALIGNMENT);
        meteoInfos.setBackground(Color.WHITE);
        addPaddingBetweenSubComponents(meteoInfos);

        JLabel saisonLabel = new JLabel("Saison :");
        meteoInfos.add(saisonLabel);

        actualSaisonLabel = new JLabel("Printemps");
        meteoInfos.add(actualSaisonLabel);

        addPaddingBetweenSubComponents(meteoInfos);

        JLabel dayLabel = new JLabel("Jour :");
        meteoInfos.add(dayLabel);

        actualDayLabel = new JLabel("3");
        meteoInfos.add(actualDayLabel);

        addPaddingBetweenSubComponents(meteoInfos);

        JLabel ensoleillementLabel = new JLabel("Ensoleillement :");
        meteoInfos.add(ensoleillementLabel);

        actualEnsoleillement = new JLabel("Ensoleillé");
        meteoInfos.add(actualEnsoleillement);

        addPaddingBetweenSubComponents(meteoInfos);


        JLabel humiditeLabel = new JLabel("Humidité :");
        meteoInfos.add(humiditeLabel);

        actualHumidite = new JLabel("Normal");
        meteoInfos.add(actualHumidite);

        addPaddingBetweenSubComponents(meteoInfos);

        JLabel tempLabel = new JLabel("Température :");
        meteoInfos.add(tempLabel);
        actualTempLabel = new JLabel("25°C");
        meteoInfos.add(actualTempLabel);
    }

    private void addPaddingBetweenSubComponents(JPanel panel) {
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
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
        actualTempLabel.setText(String.valueOf(meteo.getTemperature()) + "°C");
    }

    private void updateNbCoins() {
        String coins = String.valueOf(Inventory.getInstance().getNbCoins());
        nbCoins.setText(coins);
    }
}
