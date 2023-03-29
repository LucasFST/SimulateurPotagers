package modele.legumes;

public class Carotte extends Legume {

    public Carotte() {
        super();
        temperatureMin = 15;
        temperatureMax = 25;
    }
    @Override
    public Varietes getVariete() {
        return Varietes.CAROTTE;
    }

    @Override
    public float getCoinValue() {
        return 4 * (float)getQualite();
    }

    @Override
    protected void croissance() {
        updateCroissance(0.9, 0.6, 0.3);
    }
}
