package modele.meteo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

class SaisonTest {

    Saison saison = Saison.PRINTEMPS;

    @BeforeEach
    void setUp() {
        saison = Saison.PRINTEMPS;
    }

    @Test
    @DisplayName("Test de la méthode next() : doit retourner la saison suivante")
    void next() {
        assert saison.next() == Saison.ETE;
        saison = saison.next();
        assert saison.next() == Saison.AUTOMNE;
        saison = saison.next();
        assert saison.next() == Saison.HIVER;
        saison = saison.next();
        assert saison.next() == Saison.PRINTEMPS;
    }

    @Test
    @DisplayName("Test de la méthode getChanceEnsoleillement() : la valeur cumulée des chances doit être égale à 1.0")
    void getChanceEnsoleillement() {
        // for each saison, the sum of the chances must be equal to 1.0
        for (Saison saison : Saison.values()) {
            Map<Ensoleillement, Float> chanceEnsoleillement = saison.getChanceEnsoleillement();
            float cumul = 0.0f;
            for (float chance : chanceEnsoleillement.values()) {
                cumul += chance;
            }
            assert cumul == 1.0f;
        }
    }

    @Test
    @DisplayName("Test de la méthode getChanceHumidite() : la valeur cumulée des chances doit être égale à 1.0")
    void getChanceHumidite() {
        for (Saison saison : Saison.values()) {
            Map<Humidite, Float> chanceHumidite = saison.getChanceHumidite();
            float cumul = 0.0f;
            for (float chance : chanceHumidite.values()) {
                cumul += chance;
            }
            assert cumul == 1.0f;
        }
    }

    @Test
    @DisplayName("Test de la méthode getRandomTemperature() : la valeur doit être comprise entre -10 et +10 comparée à la moyenne de la saison")
    void getRandomTemperature() {
        int temperature = saison.getRandomTemperature();
        int average = saison.getAverageTemperature();
        assert temperature >= average - 10 && temperature <= average + 10;
    }
}