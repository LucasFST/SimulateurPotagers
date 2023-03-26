package modele.environnement.varietes;

public class Salade extends Legume {
    @Override
    public Varietes getVariete() {
        return Varietes.SALADE;
    }

    @Override
    protected void croissance() {
        // TODO
        System.out.println("Une salade pousse !!");
    }

    @Override
    public float getCoinValue() {
        return 2;
    }
}
