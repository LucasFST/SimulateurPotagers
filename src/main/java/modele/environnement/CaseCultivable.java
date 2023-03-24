package modele.environnement;

import modele.Potager;
import modele.environnement.varietes.Legume;
import modele.environnement.varietes.Salade;

public class CaseCultivable extends Case {

    private Legume legume;
    private float tauxHumidite = 0.5f; // TODO : mis à jour par le simulateur de météo pour chaque case ()
    private float tauxEnsoleillement = 0.5f;

    public CaseCultivable(Potager _potager) {
        super(_potager);
    }

    @Override
    public void actionUtilisateur() {
        if (legume == null) {
            legume = new Salade();

        } else {
            legume = null;
        }
    }

    public Legume getLegume() {
        return legume;
    }

    public float getTauxHumidite() {
        return tauxHumidite;
    }

    public void setTauxHumidite(float _tauxHumidite) {
        if (_tauxHumidite < 0)
            _tauxHumidite = 0;
        else if (_tauxHumidite > 1)
            _tauxHumidite = 1;

        tauxHumidite = _tauxHumidite;

        System.out.println("Humidité : " + tauxHumidite);
    }

    public float getTauxEnsoleillement() {
        return tauxEnsoleillement;
    }

    public void setTauxEnsoleillement(float _tauxEnsoleillement) {
        if (_tauxEnsoleillement < 0)
            _tauxEnsoleillement = 0;
        else if (_tauxEnsoleillement > 1)
            _tauxEnsoleillement = 1;

        tauxEnsoleillement = _tauxEnsoleillement;
        System.out.println("Ensoleillement : " + tauxEnsoleillement);
    }

    @Override
    public void run() {
        if (legume != null) {
            legume.nextStep();
        }
    }
}
