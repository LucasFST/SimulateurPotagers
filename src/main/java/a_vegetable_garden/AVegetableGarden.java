package a_vegetable_garden;

import a_vegetable_garden.modele.Ordonnanceur;
import a_vegetable_garden.modele.save_load.SaveAndLoad;
import a_vegetable_garden.vue_controleur.VueManager;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class AVegetableGarden {
    public static void main(String[] args) {
        FlatMacLightLaf.setup(); // look and feel
        System.setProperty("apple.laf.useScreenMenuBar", "true"); // sur mac, pour que le menu soit dans la barre du haut

        VueManager.getInstance(); // init vue manager
        Ordonnanceur.getInstance().setDelay(10000); // init ordonnanceur

        SaveAndLoad.loadIfFileExists("saves/default.avg");

        Ordonnanceur.getInstance().start();

    }
}
