package modele.meteo;

import modele.Ordonnanceur;
import modele.potagers.Potager;
import modele.potagers.SimulateurPotager;
import modele.potagers.cases.Case;
import modele.potagers.cases.CaseCultivable;

import java.io.Serializable;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import static modele.meteo.Ensoleillement.PEU_NUAGEUX;
import static modele.meteo.Humidite.NORMAL;

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
                    if (cases[j] instanceof CaseCultivable _case)
                        applyMeteoOnCase(_case);
                }
            }
        }
    }

    private void updateMeteo() {
        Ensoleillement oldEnsoleillement = ensoleillement;
        Humidite oldHumidite = humidite;
        int oldTemperature = temperature;
        updateSaison();
        updateEnsoleillement();
        updateHumidite();
        updateTemperature();
        if (oldEnsoleillement != ensoleillement || oldHumidite != humidite || oldTemperature != temperature) {
            Logger.getLogger("SimulateurMeteo").info("Météo changée: " + saison + ", " + ensoleillement + ", " + humidite + ", " + temperature + "°C");
        }
    }

    private void updateSaison() {
        currentDay++;
        int daysPerSeason = 30;
        if (currentDay >= daysPerSeason) {
            currentDay = 0;
            saison = saison.next();
            Logger.getLogger("SimulateurMeteo").info("Saison changée: " + saison);
        }
    }

    private void updateTemperature() {
        if (checkIfChanceToChange(0.7f)) return;

        temperature = saison.getRandomTemperature();
    }

    private void updateHumidite() {
        if (checkIfChanceToChange(0.1f)) return;

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
        if (checkIfChanceToChange(0.7f)) return;

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
        if (random.nextFloat() >= chanceToChange) {
            return true;
        }
        return false;
    }


    private void applyMeteoOnCase(CaseCultivable _case) {
        applyEnsoleillement(_case);
        applyHumidite(_case);
        applyTemperature(_case);
    }

    private void applyHumidite(CaseCultivable _case) {
        float tauxHumidite = _case.getTauxHumidite();
        if (humidite == Humidite.FORTE_PLUIE) {
            tauxHumidite += 0.1f;
        } else if (humidite == Humidite.PLUIE) {
            tauxHumidite += 0.05f;
        } else if (humidite == Humidite.HUMIDE) {
            tauxHumidite += 0.02f;
        } else if (humidite == Humidite.SEC) {
            tauxHumidite -= 0.02f;
        } else if (humidite == Humidite.TRES_SEC) {
            tauxHumidite -= 0.05f;
        } else if (humidite == Humidite.SECHERESSE) {
            tauxHumidite -= 0.1f;
        }

        _case.setTauxHumidite(tauxHumidite);
    }

    private void applyEnsoleillement(CaseCultivable _case) {
        float tauxEnsoleillement = _case.getTauxEnsoleillement();
        if (ensoleillement == Ensoleillement.TRES_NUAGEUX) {
            tauxEnsoleillement -= 0.1f;
        } else if (ensoleillement == Ensoleillement.NUAGEUX) {
            tauxEnsoleillement -= 0.05f;
        } else if (ensoleillement == Ensoleillement.PEU_NUAGEUX) {
            tauxEnsoleillement += 0.05f;
        } else if (ensoleillement == Ensoleillement.ENSOLEILLE) {
            tauxEnsoleillement += 0.1f;
        }

        _case.setTauxEnsoleillement(tauxEnsoleillement);
    }

    private void applyTemperature(CaseCultivable _case) {
        _case.setTemperature(temperature);
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
            default -> {
                return "Inconnu";
            }
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
            default -> {
                return "Inconnu";
            }
        }
    }
}
