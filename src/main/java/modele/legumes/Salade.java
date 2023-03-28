package modele.legumes;

public class Salade extends Legume {
    @Override
    public Varietes getVariete() {
        return Varietes.SALADE;
    }

    @Override
    public float getCoinValue() {
        return 1;
    }

    @Override
    protected void croissance() {
        updateCroissance(0.8, 0.5, 0.2);
    }
}
