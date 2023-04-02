package modele.save_load;

import modele.Ordonnanceur;
import modele.player.Inventory;
import modele.potagers.SimulateurPotager;
import vueControleur.VueManager;
import vueControleur.vues.VueControleurEnsemblePotagers;

import java.io.*;
import java.util.logging.Logger;

public class SaveAndLoad {

    public static void loadIfFileExists(String path) {
        if (checkIfFileExists(path)) {
            try {
                load(path);
                System.getLogger("Save").log(System.Logger.Level.INFO, "SimulateurPotager chargé depuis " + path + " !");
            } catch (Exception e) {
                e.printStackTrace();
                loadSimulateurPotager(new SimulateurPotager());
            }
        } else {
            System.getLogger("Save").log(System.Logger.Level.INFO, "Aucun fichier de sauvegarde trouvé, création d'un nouveau SimulateurPotager");
            Ordonnanceur.getInstance().setDelay(Ordonnanceur.DEFAULT_DELAY);
            loadSimulateurPotager(new SimulateurPotager());
        }
    }

    private static void load(String path) throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            try {
                SaveData saveData = (SaveData) objectInputStream.readObject();
                loadSaveData(saveData);
            } catch (Exception e) {
                e.printStackTrace();
                // save in wrong format : create new save
                Logger.getLogger("Save").warning("Fichier de sauvegarde corrompu, création d'un nouveau SimulateurPotager");
                SaveData saveData = new SaveData(new SimulateurPotager());
                loadSaveData(saveData);
            }
        }
    }

    private static void loadSaveData(SaveData saveData) {
        Inventory.getInstance().loadNewInstance(saveData.getInventory());
        SimulateurPotager simulateurPotager = new SimulateurPotager();
        simulateurPotager.loadNewInstance(saveData.getSimulateurPotager());
        Ordonnanceur.getInstance().setDelay(Ordonnanceur.DEFAULT_DELAY);
        Ordonnanceur.getInstance().addRunnable(simulateurPotager.simulateurMeteo);
        loadSimulateurPotager(simulateurPotager);
    }

    public static void save(SimulateurPotager simulateurPotager, String path) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            SaveData data = new SaveData(simulateurPotager);
            objectOutputStream.writeObject(data);
            System.getLogger("Save").log(System.Logger.Level.INFO, "SimulateurPotager sauvegardé dans " + path + " !");
        }
    }

    private static boolean checkIfFileExists(String path) {
        File file = new File(path);
        System.out.println("File exists : " + file.exists() + " " + path);
        return file.exists();
    }

    private static void loadSimulateurPotager(SimulateurPotager simulateurPotager) {
        VueControleurEnsemblePotagers vueControleurEnsemblePotagers = new VueControleurEnsemblePotagers(simulateurPotager);
        VueManager.getInstance().setVueControleurEnsemblePotagers(vueControleurEnsemblePotagers);
    }
}
