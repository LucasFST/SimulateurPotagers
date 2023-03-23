import com.formdev.flatlaf.FlatLightLaf;
import modele.Ordonnanceur;
import modele.SimulateurPotager;
import vueControleur.VueManager;
import vueControleur.vues.VueControleurPotager;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup(); // look and feel

        VueManager.getInstance(); // init vue manager
        Ordonnanceur.getInstance().setDelay(10000); // init ordonnanceur

        SimulateurPotager simulateurPotager = new SimulateurPotager();
        VueControleurPotager vcPotager = new VueControleurPotager(simulateurPotager);
        VueManager.getInstance().setVueControleurPotager(vcPotager);

        Ordonnanceur.getInstance().addObserver(vcPotager);
        Ordonnanceur.getInstance().start();

    }
}
