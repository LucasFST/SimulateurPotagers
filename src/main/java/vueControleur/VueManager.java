package vueControleur;

import vueControleur.vues.VueControleurPotager;

public class VueManager {
    private static VueManager instance = null;
    private VueControleurPotager vueControleurPotager;

    public static VueManager getInstance() {
        if (instance == null) {
            instance = new VueManager();
        }
        return instance;
    }

    public void setVueControleurPotager(VueControleurPotager vueControleurPotager) {
        this.vueControleurPotager = vueControleurPotager;
        this.vueControleurPotager.setVisible(true);
        //set other vues to invisible
    }
}
