/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.environnement;

import modele.Potager;

public abstract class Case implements Runnable {
    protected Potager potager;
    private float tauxHumidite; // TODO : mis à jour par le simulateur de météo pour chaque case ()
    private float tauxEnsoleillement;

    protected Case(Potager _potager) {
        potager = _potager;
    }

    public float getTauxHumidite() {
        return tauxHumidite;
    }

    public float getTauxEnsoleillement() {
        return tauxEnsoleillement;
    }

    public void setTauxHumidite(float _tauxHumidite) {
        tauxHumidite = _tauxHumidite;
    }

    public void setTauxEnsoleillement(float _tauxEnsoleillement) {
        tauxEnsoleillement = _tauxEnsoleillement;
    }
    public abstract void actionUtilisateur();


}
