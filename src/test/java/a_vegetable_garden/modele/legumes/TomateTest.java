package a_vegetable_garden.modele.legumes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TomateTest {
        Tomate tomate;

        @BeforeEach
        void setUp() {
            tomate = new Tomate();
        }

        @Test
        @DisplayName("Tomate.getVariete() should return Varietes.tomate")
        void getVariete() {
            assertEquals(Varietes.TOMATE, tomate.getVariete());
        }

        @Test
        @DisplayName("Tomate.croissance() should do something")
        void croissance() {
            // TODO
        }
}
