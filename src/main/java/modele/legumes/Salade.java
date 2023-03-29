package modele.legumes;

public class Salade extends Legume {

    public Salade() {
        super();
        temperatureMin = 10;
        temperatureMax = 35;
    }
    @Override
    public Varietes getVariete() {
        return Varietes.SALADE;
    }

    @Override
    public float getCoinValue() {
        return 2 * (float)getQualite();
    }

    @Override
    protected void croissance() {
        updateCroissance(0.8, 0.5, 0.2);
    }
}
