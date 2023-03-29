package modele.meteo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SaisonTest {

    Saison saison = Saison.PRINTEMPS;

    @BeforeEach
    void setUp() {
        saison = Saison.PRINTEMPS;
    }

    @Test
    @DisplayName("Test de la méthode next() : doit retourner la saison suivante")
    void next() {
        assertEquals(Saison.ETE, saison.next(), "La saison suivante au printemps doit être l'été");
        saison = saison.next();
        assertSame(Saison.AUTOMNE, saison.next(), "La saison suivante à l'été doit être l'automne");
        saison = saison.next();
        assertSame(Saison.HIVER, saison.next(), "La saison suivante à l'automne doit être l'hiver");
        saison = saison.next();
        assertSame(Saison.PRINTEMPS, saison.next(), "La saison suivante à l'hiver doit être le printemps");
    }

    @Test
    @DisplayName("Test de la méthode getChanceEnsoleillement()")
    void getChanceEnsoleillement() {
        // for each saison, the sum of the chances must be equal to 1.0
        for (Saison saison : Saison.values()) {
            Map<Ensoleillement, Float> chanceEnsoleillement = saison.getChanceEnsoleillement();
            float cumul = 0.0f;
            for (float chance : chanceEnsoleillement.values()) {
                cumul += chance;
            }
            assertEquals(1.0f, cumul, "La somme des chances doit être égale à 1.0");
        }
    }

    @Test
    @DisplayName("Test de la méthode getChanceHumidite()")
    void getChanceHumidite() {
        for (Saison saison : Saison.values()) {
            Map<Humidite, Float> chanceHumidite = saison.getChanceHumidite();
            float cumul = 0.0f;
            for (float chance : chanceHumidite.values()) {
                cumul += chance;
            }
            assertEquals(1.0f, cumul, "La somme des chances doit être égale à 1.0");
        }
    }

    @Test
    @DisplayName("Test de la méthode getRandomTemperature() : la valeur doit être comprise entre -10 et +10 comparée à la moyenne de la saison")
    void getRandomTemperature() {
        int temperature = saison.getRandomTemperature();
        int average = saison.getAverageTemperature();
        assertAll(
                () -> assertTrue(temperature >= average - 10, "La température doit être supérieure ou égale à la moyenne - 10"),
                () -> assertTrue(temperature <= average + 10, "La température doit être inférieure ou égale à la moyenne + 10")
        );
    }
}