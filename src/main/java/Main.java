import com.formdev.flatlaf.FlatLightLaf;
import modele.Ordonnanceur;
import modele.Potager;
import vueControleur.VueManager;
import vueControleur.vues.VueControleurPotager;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup(); // look and feel

        Potager potager = new Potager();
        VueControleurPotager vcPotager = new VueControleurPotager(potager);
        VueManager.getInstance().setVueControleurPotager(vcPotager);

        Ordonnanceur.getInstance().addObserver(vcPotager);
        Ordonnanceur.getInstance().start(300);

    }
}
