package modele.potagers.cases;

import modele.legumes.Legume;
import modele.legumes.Salade;
import modele.player.Inventory;
import modele.potagers.Potager;

import java.io.Serializable;

public class CaseCultivable extends Case implements Serializable {

    private Legume legume;
    private float tauxHumidite = 0.5f;
    private float tauxEnsoleillement = 0.5f;

    public CaseCultivable(Potager _potager) {
        super(_potager);
    }

    @Override
    public void actionUtilisateur() {
        if (legume == null) {
            legume = new Salade();
        } else {
            cultiverLegume();
        }
    }

    private void cultiverLegume() {
        if (legume == null) return;

        Inventory.getInstance().addCoins(legume.getCoinValue());
        legume = null;
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
    }

    @Override
    public void run() {
        if (legume != null) {
            legume.nextStep(tauxHumidite, tauxEnsoleillement);
        }
    }
}
