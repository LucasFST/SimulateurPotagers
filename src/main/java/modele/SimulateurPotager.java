/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import modele.Potager;
import modele.environnement.Case;
import modele.environnement.CaseCultivable;
import modele.environnement.CaseNonCultivable;

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
        ajouterPotager(new Potager());
    }

    //Pour l'instant public, mais devrait être privé quand on aura un système d'achat de potager
    //Est-ce qu'on fixe une taille maximale de potagers ?
    //TODO : ajouter un système d'achat de potager
    public void ajouterPotager(Potager _potager) {
        listePotagers.add(_potager);
    }

    public void supprimerPotager(Potager _potager) {
        listePotagers.remove(_potager);
    }

    //TODO : ajouter une fonction pour les actions de la météo


}
