package modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class SingletonTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Test de getInstance()")
    void getInstance() {
        assertNull(Singleton.getInstance(), "La méthode doit retourner null");
    }
}