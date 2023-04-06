/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AVegetableGarden.modele.potagers;


import AVegetableGarden.modele.meteo.SimulateurMeteo;
import AVegetableGarden.modele.player.Inventory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SimulateurPotager implements Serializable {
    public static final int POTAGER_PRICE = 100;
    public SimulateurMeteo simulateurMeteo;
    private ArrayList<Potager> listePotagers = new ArrayList<>();

    // private HashMap<Case, Point> map = new  HashMap<Case, Point>(); // permet de récupérer la position d'une entité à partir de sa référence

    public SimulateurPotager() {
        simulateurMeteo = new SimulateurMeteo(this);
        //par défaut, on a un potager
        ajouterPotager();
    }

    public List<Potager> getListePotagers() {
        return listePotagers;
    }

    //Pour l'instant public, mais devrait être privé quand on aura un système d'achat de potager
    //Est-ce qu'on fixe une taille maximale de potagers ?
    //TODO : ajouter un système d'achat de potager
    public void ajouterPotager() {
        Potager _potager = new Potager();
        listePotagers.add(_potager);
        System.getLogger("SimulateurPotager").log(System.Logger.Level.INFO, "Potager ajouté");
    }

    public void supprimerPotager(int _id) {
        //trouver le potager correspondant à l'id
        for (Potager potager : listePotagers) {
            if (potager.getId() == _id) {
                listePotagers.remove(potager);
                break;
            }
        }
    }

    public boolean buyPotager() {
        if (Inventory.getInstance().getNbCoins() >= POTAGER_PRICE) {
            Inventory.getInstance().removeCoinsIfEnough(POTAGER_PRICE);
            ajouterPotager();
            return true;
        }
        return false;
    }

    public int getNbPotagers() {
        return listePotagers.size();
    }

    //Supprime le dernier potager ajouté
    public void supprimerPotager() {
        listePotagers.remove(listePotagers.size() - 1);
    }

    public void loadNewInstance(SimulateurPotager simulateurPotager) {
        this.listePotagers = (ArrayList<Potager>) simulateurPotager.getListePotagers();
        this.simulateurMeteo = simulateurPotager.getSimulateurMeteo();
    }

    private SimulateurMeteo getSimulateurMeteo() {
        return simulateurMeteo;
    }
}
