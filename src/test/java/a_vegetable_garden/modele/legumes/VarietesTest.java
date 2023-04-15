package a_vegetable_garden.modele.legumes;

import a_vegetable_garden.vue_controleur.icon.IconNames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class VarietesTest {
    Varietes salade;
    Varietes carotte;
    Varietes ail;
    Varietes asperge;
    Varietes mais;
    Varietes tomate;

    @BeforeEach
    void setUp() {
        salade = new Salade().getVariete();
        carotte = new Carotte().getVariete();
        ail = new Ail().getVariete();
        asperge = new Asperge().getVariete();
        mais = new Mais().getVariete();
        tomate = new Tomate().getVariete();

    }

    @Test
    @DisplayName("Varietes.SALADE should be at the same position as IconNames.SALADE")
    void testSalade() {
        assertEquals(IconNames.SALADE.ordinal(), salade.ordinal());
    }

    @Test
    @DisplayName("Varietes.CAROTTE should be at the same position as IconNames.CAROTTE")
    void testCarotte() {
        assertEquals(IconNames.CAROTTE.ordinal(), carotte.ordinal());
    }

    @Test
    @DisplayName("Varietes.AIL should be at the same position as IconNames.AIL")
    void testAil() {
        assertEquals(IconNames.AIL.ordinal(), ail.ordinal());
    }

    @Test
    @DisplayName("Varietes.ASPERGE should be at the same position as IconNames.ASPERGE")
    void testAsperge() {
        assertEquals(IconNames.ASPERGE.ordinal(), asperge.ordinal());
    }

    @Test
    @DisplayName("Varietes.MAIS should be at the same position as IconNames.MAIS")
    void testMais() {
        assertEquals(IconNames.MAIS.ordinal(), mais.ordinal());
    }

    @Test
    @DisplayName("Varietes.TOMATE should be at the same position as IconNames.TOMATE")
    void testTomate() {
        assertEquals(IconNames.TOMATE.ordinal(), tomate.ordinal());
    }
}
