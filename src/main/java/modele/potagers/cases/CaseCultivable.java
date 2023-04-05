package modele.potagers.cases;

import modele.legumes.Carotte;
import modele.legumes.Legume;
import modele.legumes.Salade;
import modele.legumes.Varietes;
import modele.player.Inventory;
import modele.potagers.Potager;
import vueControleur.vues.components.Actions;

import java.io.Serializable;

public class CaseCultivable extends Case implements Serializable {

    private int temperature = 20;
    private Legume legume;
    private float tauxHumidite = 0.5f;
    private float tauxEnsoleillement = 0.5f;

    public CaseCultivable(Potager _potager) {
        super(_potager);
    }

    @Override
    public void actionUtilisateur(Actions action, Varietes varietes) {
        /*if (legume == null) {
            legume = new Carotte();
        } else {
            cultiverLegume();
        }*/
        switch (action) {
            case RECOLTER:
                cultiverLegume();
                break;
            case ARROSER:
                setTauxHumidite(tauxHumidite + 0.1f);
                break;
            case PLANTER:
                planterLegume(varietes);
                break;
            default:
                break;
        }
    }

    private void cultiverLegume() {
        if (legume == null) return;
        Inventory.getInstance().addCoins(legume.getCoinValue());
        legume = null;
    }
    private void planterLegume(Varietes variete) {
        if ( (legume == null) && (variete != null) ) {
            switch (variete) {
                case CAROTTE:
                    legume = new Carotte();
                    Inventory.getInstance().removeCoins(legume.getCoinPrice());
                    break;
                case SALADE:
                    legume = new Salade();
                    Inventory.getInstance().removeCoins(legume.getCoinPrice());
                    break;
                default:
                    break;
            }
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
    }

    public float getTauxEnsoleillement() {
        return tauxEnsoleillement;
    }

    public void setTauxEnsoleillement(float _tauxEnsoleillement) {
        if (_tauxEnsoleillement < 0)
            tauxEnsoleillement = 0;
        else if (_tauxEnsoleillement > 1)
            tauxEnsoleillement = 1;
    }

    public float getLegumeVie() {
        if (legume != null) {
            return (float) legume.getEtatVie();
        }
        return 0;
    }

    public int getTemperature() {
        return temperature;
    }
    public void setTemperature(int _temperature) {
        temperature = _temperature;
    }

    @Override
    public void run() {
        if (legume != null) {
            legume.nextStep(tauxHumidite, tauxEnsoleillement, temperature);
        }
    }
}
