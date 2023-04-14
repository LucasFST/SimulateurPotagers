package a_vegetable_garden.modele.legumes;

public class Ail extends Legume {

    public static final float PRICE = 1f;

    public Ail() {
        super();
        famille = FamillesLegume.LEGUME_A_BULBES;
    }

    @Override
    public Varietes getVariete() {
        return Varietes.AIL;
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
