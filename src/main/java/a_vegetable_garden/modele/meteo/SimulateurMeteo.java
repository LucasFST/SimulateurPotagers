package a_vegetable_garden.modele.meteo;

import a_vegetable_garden.modele.Ordonnanceur;
import a_vegetable_garden.modele.potagers.Potager;
import a_vegetable_garden.modele.potagers.SimulateurPotager;
import a_vegetable_garden.modele.potagers.cases.Case;
import a_vegetable_garden.modele.potagers.cases.CaseCultivable;

import java.io.Serializable;
import java.util.Map;
import java.util.Random;

import static a_vegetable_garden.modele.meteo.Ensoleillement.PEU_NUAGEUX;
import static a_vegetable_garden.modele.meteo.Humidite.NORMAL;

public class SimulateurMeteo implements Runnable, Serializable {
    private final SimulateurPotager simulateurPotager;

    private final Random random = new Random();
    private int currentDay = 0;
    private Ensoleillement ensoleillement = PEU_NUAGEUX;
    private Humidite humidite = NORMAL;
    private int temperature = 20;
    private Saison saison = Saison.PRINTEMPS;

    public SimulateurMeteo(SimulateurPotager simPot) {
        Ordonnanceur.getInstance().addRunnable(this);
        simulateurPotager = simPot;
    }

    @Override
    public void run() {
        updateMeteo();

        for (Potager potager : simulateurPotager.getListePotagers()) {
            Case[][] plateau = potager.getPlateau();
            for (Case[] cases : plateau) {
                for (int j = 0; j < plateau[0].length; j++) {
                    if (cases[j] instanceof CaseCultivable caseCultivable)
                        applyMeteoOnCase(caseCultivable);
                }
            }
        }
    }

    private void updateMeteo() {
        updateSaison();
        updateEnsoleillement();
        updateHumidite();
        updateTemperature();
    }

    private void updateSaison() {
        currentDay++;
        int daysPerSeason = 30;
        if (currentDay >= daysPerSeason) {
            currentDay = 0;
            saison = saison.next();
        }
    }

    private void updateTemperature() {
        if (checkIfChanceToChange(0.7f)) return;

        temperature = saison.getRandomTemperature();
    }

    private void updateHumidite() {
        if (checkIfChanceToChange(0.6f)) return;

        // calcule la nouvelle humidité en fonction de la saison
        Map<Humidite, Float> chanceHumidite = this.saison.getChanceHumidite();

        // Calcule la nouvelle valeur d'humidité en fonction d'une valeur aléatoire
        float randomValue = random.nextFloat();
        float cumulativeChance = 0;
        for (Map.Entry<Humidite, Float> entry : chanceHumidite.entrySet()) {
            cumulativeChance += entry.getValue();
            if (randomValue <= cumulativeChance) {
                humidite = entry.getKey();
                break;
            }
        }
    }


    private void updateEnsoleillement() {
        if (checkIfChanceToChange(0.6f)) return;

        // Obtient la liste des valeurs d'ensoleillement
        Map<Ensoleillement, Float> chanceEnsoleillement = this.saison.getChanceEnsoleillement();

        // Calcule la nouvelle valeur d'ensoleillement en fonction d'une valeur aléatoire
        float randomValue = random.nextFloat();
        float cumulativeChance = 0;
        for (Map.Entry<Ensoleillement, Float> entry : chanceEnsoleillement.entrySet()) {
            cumulativeChance += entry.getValue();
            if (randomValue <= cumulativeChance) {
                ensoleillement = entry.getKey();
                break;
            }
        }
    }

    private boolean checkIfChanceToChange(float chanceToChange) {
        return random.nextFloat() >= chanceToChange;
    }


    private void applyMeteoOnCase(CaseCultivable caseCultivable) {
        applyEnsoleillement(caseCultivable);
        applyHumidite(caseCultivable);
        applyTemperature(caseCultivable);
    }

    private void applyHumidite(CaseCultivable caseCultivable) {
        float tauxHumidite = caseCultivable.getTauxHumidite();
        if (humidite == Humidite.FORTE_PLUIE) {
            tauxHumidite += 0.2f;
        } else if (humidite == Humidite.PLUIE) {
            tauxHumidite += 0.1f;
        } else if (humidite == Humidite.HUMIDE) {
            tauxHumidite += 0.05f;
        } else if (humidite == Humidite.SEC) {
            tauxHumidite -= 0.05f;
        } else if (humidite == Humidite.TRES_SEC) {
            tauxHumidite -= 0.1f;
        } else if (humidite == Humidite.SECHERESSE) {
            tauxHumidite -= 0.2f;
        }

        caseCultivable.setTauxHumidite(tauxHumidite);
    }

    private void applyEnsoleillement(CaseCultivable caseCultivable) {
        float tauxEnsoleillement = caseCultivable.getTauxEnsoleillement();
        if (ensoleillement == Ensoleillement.TRES_NUAGEUX) {
            tauxEnsoleillement -= 0.1f;
        } else if (ensoleillement == Ensoleillement.NUAGEUX) {
            tauxEnsoleillement -= 0.05f;
        } else if (ensoleillement == Ensoleillement.PEU_NUAGEUX) {
            tauxEnsoleillement += 0.05f;
        } else if (ensoleillement == Ensoleillement.ENSOLEILLE) {
            tauxEnsoleillement += 0.1f;
        }

        caseCultivable.setTauxEnsoleillement(tauxEnsoleillement);
    }

    private void applyTemperature(CaseCultivable caseCultivable) {
        caseCultivable.setTemperature(temperature);
    }

    public String getSaison() {
        switch (saison) {
            case PRINTEMPS -> {
                return "Printemps";
            }
            case ETE -> {
                return "Été";
            }
            case AUTOMNE -> {
                return "Automne";
            }
            case HIVER -> {
                return "Hiver";
            }
            default -> throw new IllegalStateException("Unexpected value: " + saison);
        }
    }

    public int getJour() {
        return currentDay;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getEnsoleillement() {
        return switch (ensoleillement) {
            case TRES_NUAGEUX -> "Très nuageux";
            case NUAGEUX -> "Nuageux";
            case PEU_NUAGEUX -> "Peu nuageux";
            case ENSOLEILLE -> "Ensoleillé";
            default -> throw new IllegalStateException("Unexpected value: " + ensoleillement);
        };
    }

    public String getHumidite() {
        switch (humidite) {
            case FORTE_PLUIE -> {
                return "Forte pluie";
            }
            case PLUIE -> {
                return "Pluie";
            }
            case HUMIDE -> {
                return "Humide";
            }
            case SEC -> {
                return "Sec";
            }
            case TRES_SEC -> {
                return "Très sec";
            }
            case SECHERESSE -> {
                return "Sécheresse";
            }
            case NORMAL -> {
                return "Normal";
            }
            default -> throw new IllegalStateException("Unexpected value: " + humidite);
        }
    }
}
