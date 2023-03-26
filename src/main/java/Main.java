import com.formdev.flatlaf.FlatLightLaf;
import modele.Ordonnanceur;
import modele.Potager;
import modele.SimulateurPotager;
import vueControleur.VueManager;
import vueControleur.vues.VueControleurEnsemblePotagers;
import vueControleur.vues.VueControleurPotager;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup(); // look and feel

        VueManager.getInstance(); // init vue manager
        Ordonnanceur.getInstance().setDelay(10000); // init ordonnanceur

        SimulateurPotager simulateurPotager = new SimulateurPotager();
        Potager potager = simulateurPotager.getListePotagers().get(0);
        simulateurPotager.ajouterPotager();

        VueControleurEnsemblePotagers vcEnsPotager = new VueControleurEnsemblePotagers(simulateurPotager);
        VueControleurPotager vcPotager = new VueControleurPotager(potager, vcEnsPotager);

        VueManager.getInstance().setVueControleurEnsemblePotagers(vcEnsPotager);

        Ordonnanceur.getInstance().addObserver(vcPotager);
        Ordonnanceur.getInstance().start();

    }
}
