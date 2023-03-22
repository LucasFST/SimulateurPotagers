import com.formdev.flatlaf.FlatLightLaf;
import modele.Ordonnanceur;
import modele.SimulateurPotager;
import vueControleur.VueManager;
import vueControleur.vues.VueControleurPotager;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup(); // look and feel

        SimulateurPotager simulateurPotager = new SimulateurPotager();
        VueControleurPotager vcPotager = new VueControleurPotager(simulateurPotager);
        VueManager.getInstance().setVueControleurPotager(vcPotager);

        Ordonnanceur.getInstance().addObserver(vcPotager);
        Ordonnanceur.getInstance().start(1000);

    }
}
