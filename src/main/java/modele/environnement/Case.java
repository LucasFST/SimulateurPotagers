/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.environnement;

import modele.Potager;

public abstract class Case implements Runnable {
    protected Potager potager;

    protected Case(Potager _potager) {
        potager = _potager;
    }

    public abstract void actionUtilisateur();


}
