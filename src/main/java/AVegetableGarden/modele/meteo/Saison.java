package AVegetableGarden.modele.meteo;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public enum Saison {
    PRINTEMPS,
    ETE,
    AUTOMNE,
    HIVER;

    Random random = new Random();

    public Map<Ensoleillement, Float> getChanceEnsoleillement() {
        EnumMap<Ensoleillement, Float> chanceEnsoleillement = new EnumMap<>(Ensoleillement.class);
        switch (this) {
            case PRINTEMPS -> {
                chanceEnsoleillement.put(Ensoleillement.TRES_NUAGEUX, 0.1f);
                chanceEnsoleillement.put(Ensoleillement.NUAGEUX, 0.2f);
                chanceEnsoleillement.put(Ensoleillement.PEU_NUAGEUX, 0.3f);
                chanceEnsoleillement.put(Ensoleillement.ENSOLEILLE, 0.4f);
            }
            case ETE -> {
                chanceEnsoleillement.put(Ensoleillement.TRES_NUAGEUX, 0.1f);
                chanceEnsoleillement.put(Ensoleillement.NUAGEUX, 0.1f);
                chanceEnsoleillement.put(Ensoleillement.PEU_NUAGEUX, 0.2f);
                chanceEnsoleillement.put(Ensoleillement.ENSOLEILLE, 0.6f);
            }
            case AUTOMNE -> {
                chanceEnsoleillement.put(Ensoleillement.TRES_NUAGEUX, 0.2f);
                chanceEnsoleillement.put(Ensoleillement.NUAGEUX, 0.3f);
                chanceEnsoleillement.put(Ensoleillement.PEU_NUAGEUX, 0.4f);
                chanceEnsoleillement.put(Ensoleillement.ENSOLEILLE, 0.1f);
            }
            case HIVER -> {
                chanceEnsoleillement.put(Ensoleillement.TRES_NUAGEUX, 0.4f);
                chanceEnsoleillement.put(Ensoleillement.NUAGEUX, 0.3f);
                chanceEnsoleillement.put(Ensoleillement.PEU_NUAGEUX, 0.2f);
                chanceEnsoleillement.put(Ensoleillement.ENSOLEILLE, 0.1f);
            }
        }
        return chanceEnsoleillement;
    }

    public Map<Humidite, Float> getChanceHumidite() {
        EnumMap<Humidite, Float> chanceHumidite = new EnumMap<>(Humidite.class);
        switch (this) {
            case PRINTEMPS, AUTOMNE -> {
                chanceHumidite.put(Humidite.FORTE_PLUIE, 0.1f);
                chanceHumidite.put(Humidite.PLUIE, 0.2f);
                chanceHumidite.put(Humidite.HUMIDE, 0.3f);
                chanceHumidite.put(Humidite.NORMAL, 0.2f);
                chanceHumidite.put(Humidite.SEC, 0.1f);
                chanceHumidite.put(Humidite.TRES_SEC, 0.1f);
                chanceHumidite.put(Humidite.SECHERESSE, 0.0f);
            }
            case ETE -> {
                chanceHumidite.put(Humidite.FORTE_PLUIE, 0.05f);
                chanceHumidite.put(Humidite.PLUIE, 0.1f);
                chanceHumidite.put(Humidite.HUMIDE, 0.1f);
                chanceHumidite.put(Humidite.NORMAL, 0.2f);
                chanceHumidite.put(Humidite.SEC, 0.3f);
                chanceHumidite.put(Humidite.TRES_SEC, 0.2f);
                chanceHumidite.put(Humidite.SECHERESSE, 0.05f);
            }
            case HIVER -> {
                chanceHumidite.put(Humidite.FORTE_PLUIE, 0.1f);
                chanceHumidite.put(Humidite.PLUIE, 0.3f);
                chanceHumidite.put(Humidite.HUMIDE, 0.3f);
                chanceHumidite.put(Humidite.NORMAL, 0.2f);
                chanceHumidite.put(Humidite.SEC, 0.1f);
                chanceHumidite.put(Humidite.TRES_SEC, 0.0f);
                chanceHumidite.put(Humidite.SECHERESSE, 0.0f);
            }
        }
        return chanceHumidite;
    }

    public int getAverageTemperature() {
        switch (this) {
            case PRINTEMPS, AUTOMNE -> {
                return 15;
            }
            case ETE -> {
                return 25;
            }
            case HIVER -> {
                return 5;
            }
            default -> {
                return 0;
            }
        }
    }

    public int getRandomTemperature() {
        // get a random temperature between -5 and 5 degrees of the average temperature
        return getAverageTemperature() + random.nextInt(11) - 5;
    }

    public Saison next() {
        return values()[(ordinal() + 1) % values().length];
    }
}
