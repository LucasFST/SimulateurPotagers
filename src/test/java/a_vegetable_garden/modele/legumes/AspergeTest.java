package a_vegetable_garden.modele.legumes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AspergeTest {
    Asperge asperge;

    @BeforeEach
    void setUp() {
        asperge = new Asperge();
    }

    @Test
    @DisplayName("Asperge.getVariete() should return Varietes.asperge")
    void getVariete() {
        assertEquals(Varietes.ASPERGE, asperge.getVariete());
    }

    @Test
    @DisplayName("Asperge.croissance() should do something")
    void croissance() {
        // TODO
    }
}
