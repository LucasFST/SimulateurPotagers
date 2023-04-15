package a_vegetable_garden.modele.legumes;

import java.io.Serializable;

public abstract class Legume implements Serializable {

    private static final int TEMPERATURE_GEL = 0;
    private static final float ETAT_VIE_INCREMENT = 0.05f;
    private static final float ETAT_VIE_DECREMENT = 0.025f;
    private static final float ETAT_VIE_DECREMENT_GEL = 0.1f;
    private double etatVie = 0.5;
    private double etatCroissance = 0;

    protected FamillesLegume famille;

    public double getQualite() {
        if (etatCroissance < 0.3 || etatVie <= 0.3) {
            // Si le fruit est encore petit ou pourri, sa qualité est nulle
            return 0;
        } else if (etatCroissance >= 0.3 && etatCroissance < 0.5 && etatVie >= 0.3 && etatVie < 0.5) {
            // Si le fruit est en croissance et pas encore mûr ou trop mûr ou pourri, sa qualité est moyenne
            return 0.3;
        } else if (etatCroissance >= 0.5 && etatCroissance < 0.8 && etatVie >= 0.5 && etatVie < 0.8) {
            // Si le fruit est mûr et pas trop mûr ou pourri, sa qualité est bonne
            return 0.7;
        } else if (etatCroissance >= 0.8 && etatVie >= 0.8) {
            // Si le fruit est mûr et pas pourri, sa qualité est excellente
            return 1;
        } else {
            // Si on est dans aucun autre cas, la qualité est moyenne
            return 0.5;
        }
    }

    public void nextStep(float tauxHumidite, float tauxEnsoleillement, int temperature) {
        if (etatVie <= 0) {
            // Si le fruit est pourri, il ne peut plus croître
            return;
        }
        updateEtatVie(tauxHumidite, tauxEnsoleillement, temperature);
        croissance();
    }

    protected void updateCroissance(double ratePerfect, double rateGood, double rateBad) {
        if (etatVie > ratePerfect) {
            etatCroissance += 0.05;
        } else if (etatVie > rateGood) {
            etatCroissance += 0.02;
        } else if (etatVie > rateBad) {
            etatCroissance += 0.01;
        } else {
            etatCroissance += 0.005;
        }

        if (etatCroissance > 1) {
            etatCroissance = 1;
        }
    }

    private void updateEtatVie(float tauxHumidite, float tauxEnsoleillement, int temperature) {
        updateEtatVieAccordingToTauxHumiditeAndEnsoleillement(tauxHumidite, tauxEnsoleillement);
        updateEtatVieAccordingToTemperature(temperature);

        keepEtatVieBetween0And1();
    }

    private void updateEtatVieAccordingToTauxHumiditeAndEnsoleillement(float tauxHumidite, float tauxEnsoleillement) {
        int numBadConditions = 0;

        if (!isValueInRange(tauxHumidite, famille.getHumiditeMin(), famille.getHumiditeMax())) {
            etatVie -= ETAT_VIE_DECREMENT;
            numBadConditions++;
        }
        if (!isValueInRange(tauxEnsoleillement, famille.getEnsoleillementMin(), famille.getEnsoleillementMax())) {
            etatVie -= ETAT_VIE_DECREMENT;
            numBadConditions++;
        }

        if (numBadConditions == 0) {
            etatVie += ETAT_VIE_INCREMENT;
        }
    }

    private void updateEtatVieAccordingToTemperature(int temperature) {
        if (temperature < TEMPERATURE_GEL) { // Si la température est en dessous du gel, le fruit se gèle
            etatVie -= ETAT_VIE_DECREMENT_GEL;
        } else if (!isValueInRange(temperature, famille.getTemperatureMin(), famille.getTemperatureMax())) {
            etatVie -= ETAT_VIE_DECREMENT;
        } else { // Si la température est dans la moyenne, le fruit se porte bien
            etatVie += ETAT_VIE_INCREMENT;
        }
    }

    private boolean isValueInRange(float value, float min, float max) { // min et max inclus
        return value >= min && value <= max;
    }

    private void keepEtatVieBetween0And1() {
        if (etatVie > 1) {
            etatVie = 1;
        } else if (etatVie < 0) {
            etatVie = 0;
        }
    }

    public double getEtatVie() {
        return etatVie;
    }

    public double getEtatCroissance() {
        return etatCroissance;
    }

    public abstract Varietes getVariete();

    protected abstract void croissance(); // définir selon les conditions

    protected abstract float getCoinBaseValue();

    public float getCoinPrice() {
        return getCoinBaseValue();
    }

    public float getCoinValue() {
        return (2f * getCoinBaseValue() * (float) getQualite());
    }
}
