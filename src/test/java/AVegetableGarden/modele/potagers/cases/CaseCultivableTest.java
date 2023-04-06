package AVegetableGarden.modele.potagers.cases;

import AVegetableGarden.modele.legumes.Legume;
import AVegetableGarden.modele.legumes.Varietes;
import AVegetableGarden.modele.potagers.Potager;
import AVegetableGarden.vueControleur.vues.components.Actions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseCultivableTest {
    CaseCultivable caseCultivable;

    @BeforeEach
    public void setUp() {
        Potager potager = new Potager();
        caseCultivable = new CaseCultivable(potager);
    }

    @Test
    @DisplayName("getLegume() should return null when legume is null")
    void testGetLegumeReturnNullWhenLegumeIsNull() {
        assertNull(caseCultivable.getLegume());
    }

    @Test
    @DisplayName("getLegume() should return legume when legume is not null")
    void testGetLegumeReturnLegumeWhenLegumeIsNotNull() {
        caseCultivable.actionUtilisateur(Actions.PLANTER, Varietes.CAROTTE);
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