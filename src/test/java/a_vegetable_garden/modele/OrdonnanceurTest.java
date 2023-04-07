package a_vegetable_garden.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class OrdonnanceurTest {

    private Ordonnanceur ordonnanceur;

    @BeforeEach
    public void setUp() {
        ordonnanceur = Ordonnanceur.getInstance();
    }

    @Test
    @DisplayName("Test du singleton d'ordonnanceur")
    void testSingleton() {
        Ordonnanceur ordonnanceur2 = Ordonnanceur.getInstance();
        assertSame(ordonnanceur, ordonnanceur2, "Les deux instances d'ordonnanceur doivent être identiques.");
    }

    @Test
    @DisplayName("Test de la modification du délai de mise à jour")
    public void testSetDelay() {
        long newDelay = 5000;
        ordonnanceur.setDelay(newDelay);
        assertEquals(newDelay, ordonnanceur.getDelay(), "Le délai n'a pas été mis à jour.");
    }
}