package modele.meteo;

import modele.Ordonnanceur;
import modele.environnement.Case;
import modele.environnement.CaseCultivable;
import modele.potagers.Potager;
import modele.potagers.SimulateurPotager;

import java.util.Arrays;
import java.util.Random;

import static modele.meteo.Ensoleillement.PEU_NUAGEUX;
import static modele.meteo.Humidite.NORMAL;

public class SimulateurMeteo implements Runnable {
    private SimulateurPotager simulateurPotager;

    private Random random = new Random();

    private Ensoleillement ensoleillement = PEU_NUAGEUX;
    private Humidite humidite = NORMAL;

    public SimulateurMeteo(SimulateurPotager simPot) {
        Ordonnanceur.getInstance().addRunnable(this);
        simulateurPotager = simPot;
    }

    @Override
    public void run() {
        updateMeteo();

        for (Potager potager : simulateurPotager.getListePotagers()) {
            System.out.println("Potager");
            Case[][] plateau = potager.getPlateau();
            for (int i = 0; i < plateau.length; i++) {
                for (int j = 0; j < plateau[0].length; j++) {
                    if (plateau[i][j] instanceof CaseCultivable _case)
                        applyMeteoOnCase(_case);
                }
            }
        }
    }

    private void updateMeteo() {
        updateEnsoleillement();
        updateHumidite();
    }

    private void updateHumidite() {
        float chanceToChange = 0.1f;

        // Vérifie si la chance de changement est atteinte
        if (random.nextFloat() >= chanceToChange) {
            return;
        }

        // Obtient la liste des valeurs d'humidité
        Humidite[] humidityValues = Humidite.values();

        // Trouve l'index de l'humidité actuelle dans la liste des valeurs
        int currentHumidityIndex = Arrays.asList(humidityValues).indexOf(humidite);

        // Calcule l'index de la prochaine humidité en fonction d'une valeur aléatoire
        boolean isNextHumidityUp = random.nextBoolean();
        int nextHumidityIndex = isNextHumidityUp ? currentHumidityIndex + 1 : currentHumidityIndex - 1;

        // S'assure que l'index de l'humidité ne dépasse pas la taille de la liste des valeurs
        int indexWithinBounds = (nextHumidityIndex + humidityValues.length) % humidityValues.length;

        // Met à jour l'humidité avec la nouvelle valeur correspondant à l'index calculé
        humidite = humidityValues[indexWithinBounds];
    }


    private void updateEnsoleillement() {
        float chanceToChange = 0.1f;

        // Vérifie si la chance de changement est atteinte
        if (random.nextFloat() >= chanceToChange) {
            return;
        }

        // Obtient la liste des valeurs d'ensoleillement
        Ensoleillement[] sunshineValues = Ensoleillement.values();

        // Trouve l'index de l'ensoleillement actuel dans la liste des valeurs
        int currentSunshineIndex = Arrays.asList(sunshineValues).indexOf(ensoleillement);

        // Calcule l'index du prochain ensoleillement en fonction d'une valeur aléatoire
        boolean isNextSunshineUp = random.nextBoolean();
        int nextSunshineIndex = isNextSunshineUp ? currentSunshineIndex + 1 : currentSunshineIndex - 1;

        // S'assure que l'index de l'ensoleillement ne dépasse pas la taille de la liste des valeurs
        int indexWithinBounds = (nextSunshineIndex + sunshineValues.length) % sunshineValues.length;

        // Met à jour l'ensoleillement avec la nouvelle valeur correspondant à l'index calculé
        ensoleillement = sunshineValues[indexWithinBounds];
    }


    private void applyMeteoOnCase(CaseCultivable _case) {
        applyEnsoleillement(_case);
        applyHumidite(_case);
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
            System.out.println("TRES_NUAGEUX " + tauxEnsoleillement);
        } else if (ensoleillement == Ensoleillement.NUAGEUX) {
            tauxEnsoleillement -= 0.05f;
            System.out.println("NUAGEUX " + tauxEnsoleillement);
        } else if (ensoleillement == Ensoleillement.PEU_NUAGEUX) {
            tauxEnsoleillement += 0.05f;
            System.out.println("PEU_NUAGEUX " + tauxEnsoleillement);
        } else if (ensoleillement == Ensoleillement.ENSOLLEILLE) {
            tauxEnsoleillement += 0.1f;
            System.out.println("ENSOLLEILLE " + tauxEnsoleillement);
        }

        _case.setTauxEnsoleillement(tauxEnsoleillement);
    }
}
