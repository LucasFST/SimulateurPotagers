package a_vegetable_garden.modele.potagers.cases;

import a_vegetable_garden.modele.legumes.*;
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
                return cultiverLegume();
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
        float wateringIncrement = 0.2f;
        setTauxHumidite(tauxHumidite + wateringIncrement);
    }

    private String cultiverLegume() {
        if (legume == null) return "Case vide : rien à récolter";
        Inventory.getInstance().addCoins(legume.getCoinValue());
        legume = null;
        return null;
    }

    private String planterLegume(Varietes variete) {
        if ((legume == null) && (variete != null)) {
            switch (variete) {
                case CAROTTE -> {
                    return plantLegumeIfEnoughCoins(new Carotte());
                }
                case SALADE -> {
                    return plantLegumeIfEnoughCoins(new Salade());
                }
                case TOMATE -> {
                    return plantLegumeIfEnoughCoins(new Tomate());
                }
                case MAIS -> {
                    return plantLegumeIfEnoughCoins(new Mais());
                }
                case AIL -> {
                    return plantLegumeIfEnoughCoins(new Ail());
                }
                case ASPERGE -> {
                    return plantLegumeIfEnoughCoins(new Asperge());
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

    private String plantLegumeIfEnoughCoins(Legume legume) {
        if (Inventory.getInstance().removeCoinsIfEnough(legume.getCoinPrice())) {
            this.legume = legume;
            return null;
        } else {
            return notEnoughCoinsMessage(legume.getCoinPrice());
        }
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
        else this.tauxEnsoleillement = tauxEnsoleillement;
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
