package modele.legumes;

public class Carotte extends Legume {

    @Override
    public Varietes getVariete() {
        return Varietes.CAROTTE;
    }

    @Override
    public float getCoinValue() {
        return 2;
    }

    @Override
    protected void croissance() {
        updateCroissance(0.9, 0.6, 0.3);
    }
}
