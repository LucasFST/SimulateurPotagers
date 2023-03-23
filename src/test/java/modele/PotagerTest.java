package modele;

import modele.environnement.Case;
import modele.environnement.CaseCultivable;
import modele.environnement.CaseNonCultivable;

import java.awt.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class PotagerTest {
    Potager potager;

    @BeforeEach
    public void setUp() {
        potager = new Potager();
    }

    @Test
    @DisplayName("Test the size of potager")
    void testTaillePotager() {
        assertEquals(20, potager.getPlateau().length);
        assertEquals(10, potager.getPlateau()[0].length);
    }

    @Test
    @DisplayName("getPlateau() should return a 2D array of Case")
    void testGetPlateauReturn2DArrayOfCase() {
        for (int x = 0; x < SimulateurPotager.SIZE_X; x++) {
            for (int y = 0; y < SimulateurPotager.SIZE_Y; y++) {
                assertTrue(potager.getPlateau()[x][y] instanceof Case);
            }
        }
    }

    @Test
    @DisplayName("Test for the presence of CaseNonCultivable in the borders")
    void testPresenceMurs() {
        for (int x = 0; x < SimulateurPotager.SIZE_X; x++) {
            assertTrue(potager.getPlateau()[x][0] instanceof CaseNonCultivable);
            assertTrue(potager.getPlateau()[x][9] instanceof CaseNonCultivable);
        }
        for (int y = 1; y < SimulateurPotager.SIZE_Y - 1; y++) {
            assertTrue(potager.getPlateau()[0][y] instanceof CaseNonCultivable);
            assertTrue(potager.getPlateau()[19][y] instanceof CaseNonCultivable);
        }
    }

    @Test
    @DisplayName("Test for the presence of CaseNonCultivable in the middle")
    public void testPresenceMursInterieurs() {
        assertTrue(potager.getPlateau()[2][6] instanceof CaseNonCultivable);
        assertTrue(potager.getPlateau()[3][6] instanceof CaseNonCultivable);
    }

    @Test
    @DisplayName("Test for the presence of CaseCultivable")
    void testPresenceCasesCultivables() {
        for (int x = 5; x < 15; x++) {
            for (int y = 3; y < 7; y++) {
                assertTrue(potager.getPlateau()[x][y] instanceof CaseCultivable);
            }
        }
    }

    @Test
    @DisplayName("getCase() should return a Case if the point is valid")
    void testGetCaseReturnCase() {
        assertTrue(potager.getCase(new Point(0, 0)) instanceof Case);
    }

    @Test
    @DisplayName("getCase() should return null if the point is invalid")
    void testGetCaseReturnNull() {
        assertNull(potager.getCase(new Point(20, 10)));
    }

}
