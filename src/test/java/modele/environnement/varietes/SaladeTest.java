package modele.environnement.varietes;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;

class SaladeTest {
    Salade salade;

    @Before
    void setUp() {
        salade = new Salade();
    }

    @Test
    @DisplayName("Salade.getVariete() should return Varietes.salade")
    void getVariete() {
        assertEquals(Varietes.SALADE, salade.getVariete());
    }

    @Test
    @DisplayName("Salade.croissance() should do something")
    void croissance() {
        // TODO
    }
}