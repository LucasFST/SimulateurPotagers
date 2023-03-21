/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.environnement;

import modele.SimulateurPotager;

public abstract class Case implements Runnable {
    protected SimulateurPotager simulateurPotager;

    private int précipitations; // TODO : mis à jour par le simulateur de météo pour chaque case ()
    private int ensolleillement;

    
    public Case(SimulateurPotager _simulateurPotager) {
        simulateurPotager = _simulateurPotager;
    }

    public abstract void actionUtilisateur();


  }
