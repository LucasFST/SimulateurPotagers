package a_vegetable_garden.modele.legumes;

public enum FamillesLegume {

    //FAMILLE (humidite_min, humidite_max, ensoleillement_min, ensoleillement_max, temperature_min, temperature_max)
    LEGUME_FEUILLES(0.5f, 1f, 0f, 0.5f, 10f, 25f), //salade
    LEGUME_RACINES(0f, 0.5f, 0f, 0.5f, 5f, 25f), //carotte
    LEGUME_A_FRUITS(0.5f, 1f, 0.5f, 1f, 15f, 35f), //tomate
    LEGUME_GRIMPANT(0.5f, 1f, 0.5f, 1f, 15f, 35f), //maïs
    LEGUME_A_BULBES(0f, 0.5f, 0f, 0.5f, 10f, 25f), //ail
    LEGUME_A_TIGES(0f, 0.5f, 0.5f, 1f, 10f, 30f); //asperge

    private final float humiditeMin;
    private final float humiditeMax;
    private final float ensoleillementMin;
    private final float ensoleillementMax;
    private final float temperatureMin;
    private final float temperatureMax;

    FamillesLegume(float humiditeMin, float humiditeMax, float ensoleillementMin, float ensoleillementMax, float temperatureMin, float temperatureMax) {
        this.humiditeMin = humiditeMin;
        this.humiditeMax = humiditeMax;
        this.ensoleillementMin = ensoleillementMin;
        this.ensoleillementMax = ensoleillementMax;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
    }

    float getHumiditeMin() {
        return humiditeMin;
    }

    float getHumiditeMax() {
        return humiditeMax;
    }

    float getEnsoleillementMin() {
        return ensoleillementMin;
    }

    float getEnsoleillementMax() {
        return ensoleillementMax;
    }

    float getTemperatureMin() {
        return temperatureMin;
    }

    float getTemperatureMax() {
        return temperatureMax;
    }
}
