package modele.environnement;

import modele.SimulateurPotager;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class CaseNonCultivableTest {
    CaseNonCultivable caseNonCultivable;

    @Before
    public void setUp() {
        SimulateurPotager simulateurPotager = new SimulateurPotager();
        caseNonCultivable = new CaseNonCultivable(simulateurPotager);
    }

    @Test
    void testActionUtilisateur() {
        // TODO
    }

    @Test
    void testRun() {
        // TODO
    }

}