import com.formdev.flatlaf.FlatLightLaf;
import modele.Ordonnanceur;
import modele.Potager;
import vueControleur.VueManager;
import vueControleur.vues.VueControleurPotager;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup(); // look and feel

        VueManager.getInstance(); // init vue manager
        Ordonnanceur.getInstance().setDelay(10000); // init ordonnanceur

        Potager potager = new Potager();
        VueControleurPotager vcPotager = new VueControleurPotager(potager);
        VueManager.getInstance().setVueControleurPotager(vcPotager);

        Ordonnanceur.getInstance().addObserver(vcPotager);
        Ordonnanceur.getInstance().start();

    }
}
