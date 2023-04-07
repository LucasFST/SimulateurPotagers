package a_vegetable_garden.vue_controleur;

import a_vegetable_garden.modele.Ordonnanceur;
import a_vegetable_garden.modele.potagers.Potager;
import a_vegetable_garden.modele.potagers.SimulateurPotager;
import a_vegetable_garden.vue_controleur.vues.VueControleurEnsemblePotagers;
import a_vegetable_garden.vue_controleur.vues.VueControleurPotager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("Test de la méthode setVueControleurEnsemblePotagers()")
    void testSetVueControleurPotager() {
        Ordonnanceur.getInstance().setDelay(500);
        VueControleurEnsemblePotagers vueControleurEnsemblePotagers = new VueControleurEnsemblePotagers(new SimulateurPotager());
        Potager potager = new Potager();
        VueControleurPotager vueControleurPotager = new VueControleurPotager(potager, vueControleurEnsemblePotagers);
        VueManager.getInstance().setVueControleurPotager(vueControleurPotager);
        assertEquals(vueControleurPotager, vueManager.getContentPane().getComponent(0), "La vue du potager doit être ajoutée à la fenêtre.");
        assertEquals(1, vueManager.getContentPane().getComponentCount(), "La fenêtre ne doit contenir qu'une seule vue.");
    }
}