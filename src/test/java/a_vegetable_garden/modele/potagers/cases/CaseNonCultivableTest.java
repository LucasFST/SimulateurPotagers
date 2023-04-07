package a_vegetable_garden.modele.potagers.cases;

import a_vegetable_garden.modele.potagers.Potager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaseNonCultivableTest {
    CaseNonCultivable caseNonCultivable;

    @BeforeEach
    public void Before() {
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