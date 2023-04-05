package modele.legumes;

public class Carotte extends Legume {

    public Carotte() {
        super();
        temperatureMin = 15;
        temperatureMax = 25;
    }

    public static final float PRICE = 4f;
    @Override
    public Varietes getVariete() {
        return Varietes.CAROTTE;
    }

    @Override
    public float getCoinValue() {
        return getCoinBaseValue() * (float)getQualite();
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
