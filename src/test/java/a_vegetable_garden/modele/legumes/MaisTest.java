package a_vegetable_garden.modele.legumes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaisTest {
    Mais mais;

    @BeforeEach
    void setUp() {
        mais = new Mais();
    }

    @Test
    @DisplayName("Mais.getVariete() should return Varietes.mais")
    void getVariete() {
        assertEquals(Varietes.MAIS, mais.getVariete());
    }

    @Test
    @DisplayName("Mais.croissance() should do something")
    void croissance() {
        // TODO
    }

    @Test
    @DisplayName("Mais.getCoinPrice() should return Mais.PRICE")
    void getCoinPrice() {
        assertEquals(Mais.PRICE, mais.getCoinPrice());
    }
}
