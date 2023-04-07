package avegetablegarden.modele.potagers.cases;

import avegetablegarden.modele.legumes.Carotte;
import avegetablegarden.modele.legumes.Legume;
import avegetablegarden.modele.legumes.Varietes;
import avegetablegarden.modele.player.Inventory;
import avegetablegarden.modele.potagers.Potager;
import avegetablegarden.vuecontroleur.vues.components.Actions;
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
        Inventory.getInstance().addCoins(Carotte.PRICE);
        caseCultivable.actionUtilisateur(Actions.PLANTER, Varietes.CAROTTE);
        assertNotNull(caseCultivable.getLegume());
        assertEquals(Legume.class, caseCultivable.getLegume().getClass().getSuperclass());
    }

    @Test
    @DisplayName("Should return not enough coins if not enough to buy Legume")
    void testShouldReturnNotEnoughCoinsMessage() {
        Inventory.getInstance().removeCoinsIfEnough(Inventory.getInstance().getNbCoins());
        String returnMessage = caseCultivable.actionUtilisateur(Actions.PLANTER, Varietes.CAROTTE);
        assertEquals("Pas assez de pièces, il vous faut " + Carotte.PRICE + " pièces", returnMessage);
    }

    @Test
    @DisplayName("run() should do nothing when legume is null")
    void testRunDoNothingWhenLegumeIsNull() {
        caseCultivable.run();
        assertNull(caseCultivable.getLegume());
    }
}