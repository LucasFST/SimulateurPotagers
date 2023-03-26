package modele.player;

import modele.Singleton;

public class Inventory implements Singleton {
    private static final float DEFAULT_COINS_NB = 0;

    private static Inventory instance;
    private float nbCoins;

    public Inventory() {
        nbCoins = DEFAULT_COINS_NB;
    }

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public float getNbCoins() {
        return nbCoins;
    }

    public void addCoins(float nbCoins) {
        if (nbCoins < 0) {
            throw new IllegalArgumentException("nbCoins must be positive");
        }

        this.nbCoins += nbCoins;
    }

    public void removeCoins(float nbCoins) {
        if (nbCoins < 0) {
            throw new IllegalArgumentException("nbCoins must be positive");
        }
        this.nbCoins -= nbCoins;
    }
}
