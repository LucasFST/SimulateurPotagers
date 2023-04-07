package a_vegetable_garden.modele.save_load;

import a_vegetable_garden.modele.Ordonnanceur;
import a_vegetable_garden.modele.player.Inventory;
import a_vegetable_garden.modele.potagers.SimulateurPotager;

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
