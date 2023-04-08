package a_vegetable_garden.vue_controleur.vues.components;

import a_vegetable_garden.modele.legumes.Legume;
import a_vegetable_garden.modele.potagers.Potager;
import a_vegetable_garden.modele.potagers.cases.Case;
import a_vegetable_garden.modele.potagers.cases.CaseCultivable;
import a_vegetable_garden.modele.potagers.cases.CaseNonCultivable;
import a_vegetable_garden.vue_controleur.VueManager;
import a_vegetable_garden.vue_controleur.icon.IconNames;
import a_vegetable_garden.vue_controleur.vues.VueControleurPotager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static a_vegetable_garden.vue_controleur.icon.IconNames.*;

public class PotagerGrid {

    private final int sizeX;
    private final int sizeY;
    private final Potager potager; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)
    private final ButtonsPanel buttonsPanel;
    private Cases cases;


    public PotagerGrid(Potager potager, ButtonsPanel buttonsPanel) {
        this.sizeX = potager.getSizeX();
        this.sizeY = potager.getSizeY();
        this.potager = potager;
        this.buttonsPanel = buttonsPanel;
    }

    public JComponent getGridPanel() {
        GridLayout gridLayout = new GridLayout(sizeY, sizeX);
        gridLayout.setHgap(2);
        gridLayout.setVgap(2);
        JComponent grilleCases = new JPanel(gridLayout);

        cases = new Cases(sizeX, sizeY);

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                grilleCases.add(cases.getCase(new Point(x, y)));
            }
        }

        addListenerCases();
        return grilleCases;
    }

    private void addListenerCases() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                final int xx = x; // constantes utiles au fonctionnement de la classe anonyme
                final int yy = y;
                cases.getCase(new Point(x, y)).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String actionLog = potager.actionUtilisateur(buttonsPanel.getAction(), buttonsPanel.getCurrentVariete(), new Point(xx, yy));
                        if (actionLog != null) {
                            VueManager.getInstance().showErrorWindow(actionLog);
                        }
                    }
                });
            }
        }
    }

    private void updateCase(int x, int y) {
        if (potager.getPlateau()[x][y] instanceof CaseCultivable) {
            setLegumeCase(x, y);
        } else if (potager.getPlateau()[x][y] instanceof CaseNonCultivable) {
            cases.setIconCase(new Point(x, y), MUR);
        } else {
            cases.setIconCase(new Point(x, y), VIDE);
        }

        setCaseTooltip(x, y);
    }

    private void setCaseTooltip(int x, int y) {
        Case _case = potager.getPlateau()[x][y];
        if (_case instanceof CaseNonCultivable) {
            setTooltipMur(x, y);
        } else if (_case instanceof CaseCultivable caseCultivable) {
            setTooltipCaseCultivable(x, y, caseCultivable);
        }
    }

    private void setTooltipCaseCultivable(int x, int y, CaseCultivable caseCultivable) {
        String tooltip = "<html>";
        tooltip += "<strong>Case cultivable</strong></br>";

        tooltip += setPercentTooltip("Humidité", caseCultivable.getTauxHumidite());
        tooltip += setPercentTooltip("Ensoleillement", caseCultivable.getTauxEnsoleillement());

        Legume legume = caseCultivable.getLegume();
        if (legume != null) {
            tooltip += "<p>Legume : " + legume.getVariete() + "</p>";
            tooltip += setPercentTooltip("Etat de vie", (float) legume.getEtatVie());
            tooltip += setPercentTooltip("Etat de croissance", (float) legume.getEtatCroissance());
        } else {
            tooltip += "<p>Pas de legume</p>";
        }

        tooltip += "</html>";
        cases.getCase(new Point(x, y)).setTooltipInfo(tooltip);
    }

    private void setTooltipMur(int x, int y) {
        String murTooltip = "<html><strong>Mur</strong></html>";
        cases.getCase(new Point(x, y)).setTooltipInfo(murTooltip);
    }

    private String setPercentTooltip(String name, float value) {
        return "<p>" + name + " : " + floatToPercent(value) + "%</p>";
    }

    private int floatToPercent(float value) {
        return (int) (value * 100);
    }

    public void updatePotagerVue(VueControleurPotager vueControleurPotager) {
        vueControleurPotager.infoPanel.updateInfos(vueControleurPotager.vueControleurEnsemblePotagers.simulateurPotager.simulateurMeteo);
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                updateCase(x, y);
            }
        }
    }

    private void setLegumeCase(int x, int y) {
        Legume legume = ((CaseCultivable) potager.getPlateau()[x][y]).getLegume();
        if (legume == null) {
            cases.setIconCase(new Point(x, y), TERRE);
            return;
        }

        IconNames icon;
        float legumeVie = (float) legume.getEtatVie();
        float legumeCroissance = (float) legume.getEtatCroissance();

        switch (legume.getVariete()) {
            case SALADE -> icon = SALADE;
            case CAROTTE -> icon = CAROTTE;
            default -> throw new IllegalStateException("Unexpected value: " + legume.getVariete());
        }

        cases.setIconCase(new Point(x, y), icon, legumeVie, legumeCroissance);

    }
}
