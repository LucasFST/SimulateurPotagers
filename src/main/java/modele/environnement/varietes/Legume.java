package modele.environnement.varietes;

public abstract class Legume {

    private double etatVie = 1;
    private double etatCroissance = 0;

    public double getQualite() {
        if (etatCroissance < 0.3 || etatVie <= 0.3) {
            // Si le fruit est encore petit ou pourri, sa qualité est nulle
            return 0;
        } else if (etatCroissance >= 0.3 && etatCroissance < 0.5 && etatVie >= 0.3 && etatVie < 0.5) {
            // Si le fruit est en croissance et pas encore mûr ou trop mûr ou pourri, sa qualité est moyenne
            return 0.3;
        } else if (etatCroissance >= 0.5 && etatCroissance < 0.8 && etatVie >= 0.5 && etatVie < 0.8) {
            // Si le fruit est mûr et pas trop mûr ou pourri, sa qualité est bonne
            return 0.7;
        } else if (etatCroissance >= 0.8 && etatVie >= 0.8) {
            // Si le fruit est mûr et pas pourri, sa qualité est excellente
            return 1;
        } else {
            // Si on est dans aucun autres cas, la qualité est moyenne
            return 0.5;
        }
    }

    public void nextStep() {
        croissance();
    }

    public abstract Varietes getVariete();

    protected abstract void croissance(); // définir selon les conditions
}
