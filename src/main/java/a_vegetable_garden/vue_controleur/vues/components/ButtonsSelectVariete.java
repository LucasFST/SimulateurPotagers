package a_vegetable_garden.vue_controleur.vues.components;

import a_vegetable_garden.modele.Singleton;
import a_vegetable_garden.modele.legumes.*;
import a_vegetable_garden.modele.player.Inventory;
import a_vegetable_garden.vue_controleur.icon.IconNames;
import a_vegetable_garden.vue_controleur.icon.IconRepository;

import javax.swing.*;
import java.io.Serializable;

public class ButtonsSelectVariete implements Singleton, Serializable {
    private static ButtonsSelectVariete instance = null;
    private final JButton[] buttons = new JButton[Varietes.values().length];
    private final ImageIcon[] icones = new ImageIcon[Varietes.values().length];
    private final String[] noms = new String[Varietes.values().length];
    private final float[] prix = new float[Varietes.values().length];

    private float lastTimeCoinsNb = -1;

    public ButtonsSelectVariete() {
        chargerButtons();
    }

    public static ButtonsSelectVariete getInstance() {
        if (instance == null) {
            instance = new ButtonsSelectVariete();
        }
        return instance;
    }

    public JButton[] getButtons() {
        updateButtonsIfCoinsNbChanged();
        return buttons;
    }

    private void updateButtonsIfCoinsNbChanged() {
        if (lastTimeCoinsNb != Inventory.getInstance().getNbCoins()) {
            updateButtonsEnabling();
            lastTimeCoinsNb = Inventory.getInstance().getNbCoins();
        }
    }

    private void updateButtonsEnabling() {
        for (int i = 0; i < Varietes.values().length; i++) {
            buttons[i].setEnabled(Inventory.getInstance().getNbCoins() >= prix[i]);
        }
    }

    private void chargerIcones() {
        //Ordre des varietes dans l'enum Varietes = ordre des icones dans l'enum IconNames
        for (int i = 0; i < Varietes.values().length; i++) {
            icones[i] = IconRepository.getInstance().getIcone(IconNames.values()[i]);
        }
    }

    private void chargerNoms() {
        //Ordre = ordre des variétés dans l'enum Varietes
        for (int i = 0; i < Varietes.values().length; i++) {
            noms[i] = Varietes.values()[i].toString();
        }
    }

    private void chargerPrix() {
        //Ordre = ordre des variétés dans l'enum Varietes
        prix[0] = Salade.PRICE;
        prix[1] = Carotte.PRICE;
        prix[2] = Tomate.PRICE;
        prix[3] = Mais.PRICE;
        prix[4] = Ail.PRICE;
        prix[5] = Asperge.PRICE;
    }

    private void chargerButtons() {
        chargerIcones();
        chargerNoms();
        chargerPrix();
        // Ordre des boutons dans le tableau = Ordre des variétés dans l'enum Varietes
        for (int i = 0; i < Varietes.values().length; i++) {
            buttons[i] = new JButton(noms[i] + " : " + prix[i] + " pièces", icones[i]);

            buttons[i].setEnabled(Inventory.getInstance().getNbCoins() >= prix[i]);

            buttons[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }
    }
}

