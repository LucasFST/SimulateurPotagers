package vueControleur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IconRepository {
    private static IconRepository instance = null;
    private Hashtable<IconNames, ImageIcon> icones = new Hashtable<>();

    public IconRepository() {
        chargerIcones();
    }

    public static IconRepository getInstance() {
        if (instance == null) {
            instance = new IconRepository();
        }
        return instance;
    }

    public ImageIcon getIcone(IconNames icon) {
        return icones.get(icon);
    }

    private BufferedImage getSubImage(String path, int x, int y, int w, int h) throws ExecutionException {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(VueControleurPotager.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExecutionException("Image not found", ex);
        }
        return image.getSubimage(x, y, w, h);
    }

    private void chargerIcones() {
        // image libre de droits utilisée pour les légumes : https://www.vecteezy.com/vector-art/2559196-bundle-of-fruits-and-vegetables-icons

        icones.put(IconNames.SALADE, chargerIcone("Images/data.png", 0, 0, 120, 120));
        icones.put(IconNames.TERRE, chargerIcone("Images/Terre.png"));
        icones.put(IconNames.VIDE, chargerIcone("Images/Vide.png"));
        icones.put(IconNames.MUR, chargerIcone("Images/Mur.png"));
    }

    private ImageIcon chargerIcone(String path) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(path));
            return new ImageIcon(image);
        } catch (IOException ex) {
            Logger.getLogger(VueControleurPotager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private ImageIcon chargerIcone(String path, int x, int y, int w, int h) {
        // charger une sous partie de l'image à partir de ses coordonnées dans path
        BufferedImage bi = null;
        try {
            bi = getSubImage(path, x, y, w, h);
        } catch (ExecutionException e) {
            return null;
        }
        // adapter la taille de l'image a la taille du composant (ici : 20x20)
        return new ImageIcon(bi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    }
}
