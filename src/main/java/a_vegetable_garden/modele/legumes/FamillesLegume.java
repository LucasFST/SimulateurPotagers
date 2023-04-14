package a_vegetable_garden.modele.legumes;

public enum FamillesLegume {

    //FAMILLE (humidite_min, humidite_max, ensoleillement_min, ensoleillement_max, temperature_min, temperature_max)
    LEGUME_FEUILLES(0.5f, 1f, 0f, 0.5f, 10f, 25f), //salade
    LEGUME_RACINES(0f, 0.5f, 0f, 0.5f, 5f, 25f), //carotte
    LEGUME_A_FRUITS(0.5f, 1f, 0.5f, 1f, 15f, 35f), //tomate
    LEGUME_GRIMPANT(0.5f, 1f, 0.5f, 1f, 15f, 35f), //maïs
    LEGUME_A_BULBES(0f, 0.5f, 0f, 0.5f, 10f, 25f), //ail
    LEGUME_A_TIGES(0f, 0.5f, 0.5f, 1f, 10f, 30f); //asperge

    private final float humidite_min;
    private final float humidite_max;
    private final float ensoleillement_min;
    private final float ensoleillement_max;
    private final float temperature_min;
    private final float temperature_max;
    FamillesLegume(float humidite_min, float humidite_max, float ensoleillement_min, float ensoleillement_max, float temperature_min, float temperature_max) {
        this.humidite_min = humidite_min;
        this.humidite_max = humidite_max;
        this.ensoleillement_min = ensoleillement_min;
        this.ensoleillement_max = ensoleillement_max;
        this.temperature_min = temperature_min;
        this.temperature_max = temperature_max;
    }

    float getHumiditeMin() {
        return humidite_min;
    }

    float getHumiditeMax() {
        return humidite_max;
    }

    float getEnsoleillementMin() {
        return ensoleillement_min;
    }

    float getEnsoleillementMax() {
        return ensoleillement_max;
    }

    float getTemperatureMin() {
        return temperature_min;
    }

    float getTemperatureMax() {
        return temperature_max;
    }
}
