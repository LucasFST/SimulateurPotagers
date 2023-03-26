import com.formdev.flatlaf.FlatLightLaf;
import modele.Ordonnanceur;
import modele.potagers.SimulateurPotager;
import vueControleur.VueManager;
import vueControleur.vues.VueControleurEnsemblePotagers;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup(); // look and feel

        VueManager.getInstance(); // init vue manager
        Ordonnanceur.getInstance().setDelay(10000); // init ordonnanceur

        SimulateurPotager simulateurPotager = new SimulateurPotager();
        simulateurPotager.ajouterPotager();

        VueControleurEnsemblePotagers vcEnsPotager = new VueControleurEnsemblePotagers(simulateurPotager);

        VueManager.getInstance().setVueControleurEnsemblePotagers(vcEnsPotager);

        Ordonnanceur.getInstance().start();

    }
}
