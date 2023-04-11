package a_vegetable_garden.modele.potagers.cases;

import a_vegetable_garden.modele.legumes.Carotte;
import a_vegetable_garden.modele.legumes.Legume;
import a_vegetable_garden.modele.legumes.Salade;
import a_vegetable_garden.modele.legumes.Varietes;
import a_vegetable_garden.modele.player.Inventory;
import a_vegetable_garden.modele.potagers.Actions;
import a_vegetable_garden.modele.potagers.Potager;

import java.io.Serializable;

public class CaseCultivable extends Case implements Serializable {

    private int temperature = 20;
    private Legume legume;
    private float tauxHumidite = 0.5f;
    private float tauxEnsoleillement = 0.5f;

    public CaseCultivable(Potager potager) {
        super(potager);
    }

    private static String notEnoughCoinsMessage(float price) {
        return "Pas assez de pièces, il vous faut " + price + " pièces";
    }

    @Override
    public String actionUtilisateur(Actions action, Varietes varietes) {
        if (action == null) return null;
        switch (action) {
            case RECOLTER -> {
                cultiverLegume();
                return null;
            }
            case ARROSER -> {
                doWatering();
                return null;
            }
            case PLANTER -> {
                return planterLegume(varietes);
            }
            default -> {
                return null;
            }
        }
    }

    private void doWatering() {
        float wateringIncrement = 0.3f;
        setTauxHumidite(tauxHumidite + wateringIncrement);
    }

    private void cultiverLegume() {
        if (legume == null) return;
        Inventory.getInstance().addCoins(legume.getCoinValue());
        legume = null;
    }

    private String planterLegume(Varietes variete) {
        if ((legume == null) && (variete != null)) {
            switch (variete) {
                case CAROTTE -> {
                    if (Inventory.getInstance().removeCoinsIfEnough(Carotte.PRICE)) {
                        legume = new Carotte();
                        return null;
                    } else {
                        return notEnoughCoinsMessage(Carotte.PRICE);
                    }
                }
                case SALADE -> {
                    if (Inventory.getInstance().removeCoinsIfEnough(Salade.PRICE)) {
                        legume = new Salade();
                        return null;
                    } else {
                        return notEnoughCoinsMessage(Salade.PRICE);
                    }
                }
                default -> {
                    return "Variété non implémentée, veuillez contacter le développeur";
                }
            }
        }
        if (legume != null)
            return "Case déjà occupée";
        else return "Variété non sélectionnée";
    }

    public Legume getLegume() {
        return legume;
    }

    public float getTauxHumidite() {
        return tauxHumidite;
    }

    public void setTauxHumidite(float tauxHumidite) {
        if (tauxHumidite < 0)
            tauxHumidite = 0;
        else if (tauxHumidite > 1)
            tauxHumidite = 1;

        this.tauxHumidite = tauxHumidite;
    }

    public float getTauxEnsoleillement() {
        return tauxEnsoleillement;
    }

    public void setTauxEnsoleillement(float tauxEnsoleillement) {
        if (tauxEnsoleillement < 0)
            this.tauxEnsoleillement = 0;
        else if (tauxEnsoleillement > 1)
            this.tauxEnsoleillement = 1;
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

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public void run() {
        if (legume != null) {
            legume.nextStep(tauxHumidite, tauxEnsoleillement, temperature);
        }
    }
}
