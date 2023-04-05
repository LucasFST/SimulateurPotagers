package modele.player;

import modele.Singleton;

import java.io.Serializable;

public class Inventory implements Singleton, Serializable {
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

    public void loadNewInstance(Inventory newInventory) {
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
        if(!hasEnoughCoins(nbCoins)) {
            return false;
        }
        this.nbCoins -= nbCoins;
        return true;
    }

    private boolean hasEnoughCoins(float nbCoins) {
        return !((nbCoins < 0) && (this.nbCoins < nbCoins));
    }
}
