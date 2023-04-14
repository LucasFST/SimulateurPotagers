package a_vegetable_garden.modele.legumes;

public class Carotte extends Legume {

    public static final float PRICE = 2f;

    public Carotte() {
        super();
        famille = FamillesLegume.LEGUME_RACINES;
    }

    @Override
    public Varietes getVariete() {
        return Varietes.CAROTTE;
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
