package modele.save_load;

import modele.Ordonnanceur;
import modele.player.Inventory;
import modele.potagers.SimulateurPotager;

import java.io.Serializable;

public class SaveData implements Serializable {
    private final SimulateurPotager simulateurPotager;
    private final Inventory inventory;

    public SaveData(SimulateurPotager simulateurPotager) {
        this.simulateurPotager = simulateurPotager;
        System.out.println("SimulateurPotager : " + simulateurPotager);
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
