package modele.save_load;

import modele.player.Inventory;
import modele.potagers.SimulateurPotager;

import java.io.Serializable;

public class SaveData implements Serializable {
    private SimulateurPotager simulateurPotager;
    private Inventory inventory;

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
}
