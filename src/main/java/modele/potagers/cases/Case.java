/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.potagers.cases;

import modele.legumes.Varietes;
import modele.potagers.Potager;
import vueControleur.vues.components.Actions;

import java.io.Serializable;

public abstract class Case implements Runnable, Serializable {
    protected final Potager potager;

    protected Case(Potager _potager) {
        potager = _potager;
    }

    public abstract String actionUtilisateur(Actions action, Varietes varietes);
}
