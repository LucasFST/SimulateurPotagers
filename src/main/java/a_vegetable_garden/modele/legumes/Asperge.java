package a_vegetable_garden.modele.legumes;

public class Asperge extends Legume {

    public static final float PRICE = 2f;

    public Asperge() {
        super();
        famille = FamillesLegume.LEGUME_A_TIGES;
    }

    @Override
    public Varietes getVariete() {
        return Varietes.ASPERGE;
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
