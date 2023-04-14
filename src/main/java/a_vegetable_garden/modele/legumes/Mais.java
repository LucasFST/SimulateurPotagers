package a_vegetable_garden.modele.legumes;

public class Mais extends Legume {

    public static final float PRICE = 3f;

    public Mais() {
        super();
        famille = FamillesLegume.LEGUME_GRIMPANT;
    }

    @Override
    public Varietes getVariete() {
        return Varietes.MAIS;
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
