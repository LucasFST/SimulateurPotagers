package modele.environnement;

import modele.SimulateurPotager;
import modele.environnement.varietes.Legume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseCultivableTest {
    CaseCultivable caseCultivable;

    @BeforeEach
    public void setUp() {
        SimulateurPotager simulateurPotager = new SimulateurPotager();
        caseCultivable = new CaseCultivable(simulateurPotager);
    }

    @Test
    @DisplayName("getLegume() should return null when legume is null")
    void testGetLegumeReturnNullWhenLegumeIsNull() {
        assertNull(caseCultivable.getLegume());
    }

    @Test
    @DisplayName("getLegume() should return legume when legume is not null")
    void testGetLegumeReturnLegumeWhenLegumeIsNotNull() {
        caseCultivable.actionUtilisateur();
        assertNotNull(caseCultivable.getLegume());
        assertEquals(Legume.class, caseCultivable.getLegume().getClass().getSuperclass());
    }

    @Test
    @DisplayName("run() should do nothing when legume is null")
    void testRunDoNothingWhenLegumeIsNull() {
        caseCultivable.run();
        assertNull(caseCultivable.getLegume());
    }
}