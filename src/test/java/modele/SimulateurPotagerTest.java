package modele;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SimulateurPotagerTest {
    SimulateurPotager simulateurPotager;

    @BeforeEach
    public void setUp() {
        simulateurPotager = new SimulateurPotager();
    }

    @Test
    @DisplayName("getListePotagers() should return an ArrayList of Potager")
    void testGetListePotagerReturnAnArrayListOfPotager(){ assertInstanceOf( ArrayList.class, simulateurPotager.getListePotagers()); }

    @Test
    @DisplayName("getListePotagers() should return an ArrayList of Potager with size 1")
    void testGetListePotagerReturnAnArrayListOfPotagerWithSize1(){ assertEquals(1, simulateurPotager.getListePotagers().size()); }

    @Test
    @DisplayName("ajouterPotager() should add a potager to the list")
    void testAjouterPotagerAddAPotagerToTheList(){
        simulateurPotager.ajouterPotager();
        assertEquals(2, simulateurPotager.getListePotagers().size());
    }

    @Test
    @DisplayName("supprimerPotager() should remove a potager from the list")
    void testSupprimerPotagerRemoveAPotagerFromTheList(){
        simulateurPotager.supprimerPotager();
        assertEquals(0, simulateurPotager.getListePotagers().size());
    }
}