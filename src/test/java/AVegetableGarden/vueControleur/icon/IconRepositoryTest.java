package AVegetableGarden.vueControleur.icon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class IconRepositoryTest {

    IconRepository iconRepository;

    @BeforeEach
    void setUp() {
        iconRepository = IconRepository.getInstance();
    }

    @Test
    @DisplayName("Test du singleton d'IconRepository")
    void testSingleton() {
        IconRepository iconRepository2 = IconRepository.getInstance();
        assertSame(iconRepository, iconRepository2, "Les deux instances d'IconRepository doivent être identiques.");
    }

    @Test
    @DisplayName("Test de la récupération d'un icône")
    void testGetIcone() {
        for (IconNames iconNames : IconNames.values()) {
            ImageIcon icone = iconRepository.getIcone(iconNames);
            assertNotNull(icone);
        }
    }


}