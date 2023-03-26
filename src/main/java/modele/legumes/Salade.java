package modele.legumes;

public class Salade extends Legume {
    @Override
    public Varietes getVariete() {
        return Varietes.SALADE;
    }

    @Override
    protected void croissance() {
        // TODO
    }

    @Override
    public float getCoinValue() {
        return 2;
    }
}
