package avegetablegarden.modele.potagers.cases;

import avegetablegarden.modele.potagers.Potager;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class CaseNonCultivableTest {
    CaseNonCultivable caseNonCultivable;

    @Before
    public void setUp() {
        Potager potager = new Potager();
        caseNonCultivable = new CaseNonCultivable(potager);
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