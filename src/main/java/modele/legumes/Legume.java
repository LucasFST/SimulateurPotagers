package modele.legumes;

import java.io.Serializable;

public abstract class Legume implements Serializable {

    protected int temperatureMin;
    protected int temperatureMax;
    private double etatVie = 0.5;
    private double etatCroissance = 0;

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
            etatCroissance += 0.02;
        } else if (etatVie > rateGood) {
            etatCroissance += 0.01;
        } else if (etatVie > rateBad) {
            etatCroissance += 0.005;
        } else {
            etatCroissance += 0.001;
        }

        if (etatCroissance > 1) {
            etatCroissance = 1;
        }
    }

    private void updateEtatVie(float tauxHumidite, float tauxEnsoleillement, int temperature) {
        if (tauxHumidite < 0.3 || tauxHumidite > 0.7) {
            // Si le taux d'humidité est trop faible ou trop élevé, le fruit se porte mal
            etatVie -= 0.1;
        } else if (tauxEnsoleillement < 0.3 || tauxEnsoleillement > 0.7) {
            // Si le taux d'ensoleillement est trop faible ou trop élevé, le fruit se porte mal
            etatVie -= 0.1;
        } else if (temperature < temperatureMin || temperature > temperatureMax) {
            // Si la température est trop faible ou trop élevée, le fruit se porte mal
            etatVie -= 0.1;
        } else if (temperature < 0)
        {   // Si la température est négative, le fruit se porte mal à cause du gel
            etatVie -= 0.2;
        } else if ( temperature >= getTemperatureMinOptimale() && temperature <= getTemperatureMaxOptimale())
        {   // Si la température est optimale, le fruit se porte très bien
            etatVie += 0.2;
        } else {
            // Si les conditions sont bonnes, le fruit se porte bien
            etatVie += 0.1;
        }


        if (etatVie > 1) {
            etatVie = 1;
        } else if (etatVie < 0) {
            etatVie = 0;
        }
    }

    private int getTemperatureMinOptimale()
    {
        //calcul temperature moyenne
        int temperatureMoyenne = (temperatureMin + temperatureMax) / 2;
        //calcul de la taille de l'intervalle
        int intervalleSizeOptimale = (temperatureMax - temperatureMin + 1) / 3;
        return temperatureMoyenne - intervalleSizeOptimale / 2;
    }

    private int getTemperatureMaxOptimale()
    {
        //calcul temperature moyenne
        int temperatureMoyenne = (temperatureMin + temperatureMax) / 2;
        //calcul de la taille de l'intervalle
        int intervalleSizeOptimale = (temperatureMax - temperatureMin + 1) / 3;
        return temperatureMoyenne + intervalleSizeOptimale / 2;
    }

    public double getEtatVie() {
        return etatVie;
    }

    public abstract Varietes getVariete();

    protected abstract void croissance(); // définir selon les conditions

    public abstract float getCoinValue();
}
