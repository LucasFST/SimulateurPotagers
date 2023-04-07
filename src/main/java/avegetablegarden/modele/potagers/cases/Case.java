/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avegetablegarden.modele.potagers.cases;

import avegetablegarden.modele.legumes.Varietes;
import avegetablegarden.modele.potagers.Potager;
import avegetablegarden.vuecontroleur.vues.components.Actions;

import java.io.Serializable;

public abstract class Case implements Runnable, Serializable {
    protected final Potager potager;

    protected Case(Potager potager) {
        this.potager = potager;
    }

    public abstract String actionUtilisateur(Actions action, Varietes varietes);
}
