/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class SimulateurPotager {
    private ArrayList<Potager> listePotagers = new ArrayList<Potager>();
    private SimulateurMeteo simulateurMeteo;

    // private HashMap<Case, Point> map = new  HashMap<Case, Point>(); // permet de récupérer la position d'une entité à partir de sa référence

    public SimulateurPotager() {
        simulateurMeteo = new SimulateurMeteo(this);
        //par défaut, on a un potager
        ajouterPotager();
    }

    public ArrayList<Potager> getListePotagers() {
        return listePotagers;
    }

    //Pour l'instant public, mais devrait être privé quand on aura un système d'achat de potager
    //Est-ce qu'on fixe une taille maximale de potagers ?
    //TODO : ajouter un système d'achat de potager
    public void ajouterPotager() {
        Potager _potager = new Potager();
        listePotagers.add(_potager);
    }

    public void supprimerPotager(Potager _potager) {
        listePotagers.remove(_potager);
    }

    //Supprime le dernier potager ajouté
    public void supprimerPotager() {
        listePotagers.remove(listePotagers.size()-1);
    }
    //TODO : ajouter une fonction pour les actions de la météo
}
