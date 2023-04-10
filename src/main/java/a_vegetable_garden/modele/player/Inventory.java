package a_vegetable_garden.modele.player;

import a_vegetable_garden.modele.Singleton;

import java.io.Serializable;

public class Inventory implements Singleton, Serializable {
    private static final float DEFAULT_NB_COINS = 80;

    private static Inventory instance;
    private float nbCoins;

    public Inventory() {
        nbCoins = DEFAULT_NB_COINS;
    }

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public static void loadNewInstance(Inventory newInventory) {
        instance = newInventory;
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

    public boolean removeCoinsIfEnough(float nbCoins) {
        if (!hasEnoughCoins(nbCoins)) {
            return false;
        }
        this.nbCoins -= nbCoins;
        return true;
    }

    private boolean hasEnoughCoins(float nbCoins) {
        if (nbCoins < 0) {
            return false;
        }
        return this.nbCoins >= nbCoins;
    }

}
