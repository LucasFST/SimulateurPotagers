package modele.environnement;

import modele.Potager;
import modele.environnement.varietes.Legume;
import modele.environnement.varietes.Salade;

public class CaseCultivable extends Case {

    private Legume legume;
    private float tauxHumidite; // TODO : mis à jour par le simulateur de météo pour chaque case ()
    private float tauxEnsoleillement;

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

    public float getTauxEnsoleillement() {
        return tauxEnsoleillement;
    }

    public void setTauxHumidite(float _tauxHumidite) {
        tauxHumidite = _tauxHumidite;
    }

    public void setTauxEnsoleillement(float _tauxEnsoleillement) {
        tauxEnsoleillement = _tauxEnsoleillement;
    }

    @Override
    public void run() {
        if (legume != null) {
            legume.nextStep();
        }
    }
}
