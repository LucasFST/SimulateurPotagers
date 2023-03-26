package modele.legumes;

public class Carotte extends Legume {

    @Override
    public Varietes getVariete() {
        return Varietes.CAROTTE;
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
