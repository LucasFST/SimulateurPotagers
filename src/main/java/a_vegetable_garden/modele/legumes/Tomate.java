package a_vegetable_garden.modele.legumes;

public class Tomate extends Legume {

    public static final float PRICE = 2f;

    public Tomate() {
        super();
        famille = FamillesLegume.LEGUME_A_FRUITS;
    }

    @Override
    public Varietes getVariete() {
        return Varietes.TOMATE;
    }

    @Override
    protected float getCoinBaseValue() {
        return PRICE;
    }

    @Override
    protected void croissance() {
        updateCroissance(0.9, 0.6, 0.3);
    }
}

