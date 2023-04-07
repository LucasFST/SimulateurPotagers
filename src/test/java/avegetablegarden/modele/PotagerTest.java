package avegetablegarden.modele;

import avegetablegarden.modele.potagers.Potager;
import avegetablegarden.modele.potagers.cases.Case;
import avegetablegarden.modele.potagers.cases.CaseCultivable;
import avegetablegarden.modele.potagers.cases.CaseNonCultivable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

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
        assertEquals(potager.getSizeX(), potager.getPlateau().length);
        assertEquals(potager.getSizeY(), potager.getPlateau()[0].length);
    }

    @Test
    @DisplayName("getPlateau() should return a 2D array of Case")
    void testGetPlateauReturn2DArrayOfCase() {
        for (int x = 0; x < potager.getSizeX(); x++) {
            for (int y = 0; y < potager.getSizeY(); y++) {
                assertTrue(potager.getPlateau()[x][y] instanceof Case);
            }
        }
    }

    @Test
    @DisplayName("Test for the presence of CaseNonCultivable in the borders")
    void testPresenceMurs() {
        for (int x = 0; x < potager.getSizeX(); x++) {
            assertTrue(potager.getPlateau()[x][0] instanceof CaseNonCultivable);
            assertTrue(potager.getPlateau()[x][9] instanceof CaseNonCultivable);
        }
        for (int y = 1; y < potager.getSizeY() - 1; y++) {
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
        assertNull(potager.getCase(new Point(potager.getSizeX(), potager.getSizeY())));
    }

}
