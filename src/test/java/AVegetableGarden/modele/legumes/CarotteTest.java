package AVegetableGarden.modele.legumes;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CarotteTest {
    Carotte carotte;

    @BeforeEach
    void setUp() {
        carotte = new Carotte();
    }

    @Test
    @DisplayName("Carotte.getVariete() should return Varietes.carotte")
    void getVariete() {
        assertEquals(Varietes.CAROTTE, carotte.getVariete());
    }

    @Test
    @DisplayName("Carotte.croissance() should do something")
    void croissance() {
        // TODO
    }
}