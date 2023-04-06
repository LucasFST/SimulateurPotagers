package AVegetableGarden;

import AVegetableGarden.modele.Ordonnanceur;
import AVegetableGarden.modele.save_load.SaveAndLoad;
import AVegetableGarden.vueControleur.VueManager;
import com.formdev.flatlaf.FlatLightLaf;

public class AVegetableGarden {
    public static void main(String[] args) {
        FlatLightLaf.setup(); // look and feel
        System.setProperty("apple.laf.useScreenMenuBar", "true"); // sur mac, pour que le menu soit dans la barre du haut

        VueManager.getInstance(); // init vue manager
        Ordonnanceur.getInstance().setDelay(10000); // init ordonnanceur

        SaveAndLoad.loadIfFileExists("saves/default.avg");

        Ordonnanceur.getInstance().start();

    }
}
