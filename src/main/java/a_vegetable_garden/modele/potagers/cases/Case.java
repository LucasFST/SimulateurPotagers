/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a_vegetable_garden.modele.potagers.cases;

import a_vegetable_garden.modele.legumes.Varietes;
import a_vegetable_garden.modele.potagers.Actions;
import a_vegetable_garden.modele.potagers.Potager;

import java.io.Serializable;

public abstract class Case implements Runnable, Serializable {
    protected final Potager potager;

    protected Case(Potager potager) {
        this.potager = potager;
    }

    public abstract String actionUtilisateur(Actions action, Varietes varietes);
}
