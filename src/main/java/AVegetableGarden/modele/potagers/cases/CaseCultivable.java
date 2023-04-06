package AVegetableGarden.modele.potagers.cases;

import AVegetableGarden.modele.legumes.Carotte;
import AVegetableGarden.modele.legumes.Legume;
import AVegetableGarden.modele.legumes.Salade;
import AVegetableGarden.modele.legumes.Varietes;
import AVegetableGarden.modele.player.Inventory;
import AVegetableGarden.modele.potagers.Potager;
import AVegetableGarden.vueControleur.vues.components.Actions;

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
    public String actionUtilisateur(Actions action, Varietes varietes) {
        switch (action) {
            case RECOLTER:
                cultiverLegume();
                return null;
            case ARROSER:
                setTauxHumidite(tauxHumidite + 0.1f);
                return null;
            case PLANTER:
                return planterLegume(varietes);
            default:
                return null;
        }
    }

    private void cultiverLegume() {
        if (legume == null) return;
        Inventory.getInstance().addCoins(legume.getCoinValue());
        legume = null;
    }

    private String planterLegume(Varietes variete) {
        if ((legume == null) && (variete != null)) {
            switch (variete) {
                case CAROTTE:
                    if (Inventory.getInstance().removeCoinsIfEnough(Carotte.PRICE)) {
                        legume = new Carotte();
                        return null;
                    } else {
                        return "Pas assez de pièces, il vous faut " + Carotte.PRICE + " pièces";
                    }
                case SALADE:
                    if (Inventory.getInstance().removeCoinsIfEnough(Salade.PRICE)) {
                        legume = new Salade();
                        return null;
                    } else {
                        return "Pas assez de pièces, il vous faut " + Salade.PRICE + " pièces";
                    }
                default:
                    return "Variété non implémentée, veuillez contacter le développeur";
            }
        }
        return "Case déjà occupée ou aucune variété sélectionnée";
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
