package avegetablegarden.modele;

import avegetablegarden.modele.player.Inventory;
import avegetablegarden.modele.potagers.SimulateurPotager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class SimulateurPotagerTest {
    SimulateurPotager simulateurPotager;

    @BeforeEach
    public void setUp() {
        simulateurPotager = new SimulateurPotager();
    }

    @Test
    @DisplayName("getListePotagers() should return an ArrayList of Potager")
    void testGetListePotagerReturnAnArrayListOfPotager() {
        assertInstanceOf(ArrayList.class, simulateurPotager.getListePotagers());
    }

    @Test
    @DisplayName("getListePotagers() should return an ArrayList of Potager with size 1")
    void testGetListePotagerReturnAnArrayListOfPotagerWithSize1() {
        assertEquals(1, simulateurPotager.getListePotagers().size());
    }

    @Test
    @DisplayName("buyPotager() should add a potager to the list")
    void testAjouterPotagerAddAPotagerToTheList() {
        Inventory.getInstance().addCoins(200);
        simulateurPotager.buyPotager();
        assertEquals(2, simulateurPotager.getListePotagers().size());
    }

    @Test
    @DisplayName("supprimerPotager() should remove a potager from the list")
    void testSupprimerPotagerRemoveAPotagerFromTheList() {
        simulateurPotager.supprimerPotager();
        assertEquals(0, simulateurPotager.getListePotagers().size());
    }

    @Test
    @DisplayName("supprimerPotager(id) should remove the potager with the id from the list")
    void testSupprimerPotagerRemoveAPotagerFromTheListWithId() {
        int id = simulateurPotager.getListePotagers().get(0).getId();
        simulateurPotager.supprimerPotager(id);
        assertEquals(0, simulateurPotager.getListePotagers().size());
    }
}