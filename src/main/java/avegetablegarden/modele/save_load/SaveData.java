package avegetablegarden.modele.save_load;

import avegetablegarden.modele.Ordonnanceur;
import avegetablegarden.modele.player.Inventory;
import avegetablegarden.modele.potagers.SimulateurPotager;

import java.io.Serializable;
import java.util.logging.Logger;

public class SaveData implements Serializable {
    private final SimulateurPotager simulateurPotager;
    private final Inventory inventory;

    public SaveData(SimulateurPotager simulateurPotager) {
        this.simulateurPotager = simulateurPotager;
        Logger.getLogger("Save").info("SimulateurPotager : " + simulateurPotager);
        this.inventory = Inventory.getInstance();
    }

    public SimulateurPotager getSimulateurPotager() {
        return simulateurPotager;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Ordonnanceur getOrdonnanceur() {
        return Ordonnanceur.getInstance();
    }
}
