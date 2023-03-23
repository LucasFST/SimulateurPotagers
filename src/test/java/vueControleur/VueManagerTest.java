package vueControleur;

import modele.SimulateurPotager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vueControleur.vues.VueControleurPotager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class VueManagerTest {

    private VueManager vueManager;

    @BeforeEach
    void setUp() {
        vueManager = VueManager.getInstance();
        vueManager.setVisible(false);
    }

    @Test
    @DisplayName("Test du singleton de VueManager")
    void testSingleton() {
        VueManager vueManager2 = VueManager.getInstance();
        assertSame(vueManager, vueManager2, "Les deux instances de VueManager doivent être identiques.");
    }

    @Test
    void testSetVueControleurPotager() {
        SimulateurPotager simulateurPotager = new SimulateurPotager();
        VueControleurPotager vueControleurPotager = new VueControleurPotager(simulateurPotager);
        VueManager.getInstance().setVueControleurPotager(vueControleurPotager);
        assertEquals(vueControleurPotager, vueManager.getContentPane().getComponent(0), "La vue du potager doit être ajoutée à la fenêtre.");
        assertEquals(1, vueManager.getContentPane().getComponentCount(), "La fenêtre ne doit contenir qu'une seule vue.");
    }
}