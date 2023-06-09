package a_vegetable_garden.vue_controleur.icon;

import a_vegetable_garden.modele.Singleton;
import a_vegetable_garden.vue_controleur.vues.VueControleurPotager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IconRepository implements Singleton {
    private static IconRepository instance = null;
    private final ConcurrentHashMap<IconNames, ImageIcon> icones = new ConcurrentHashMap<>();
    private final HashMap<String, BufferedImage> imagesCache = new HashMap<>();

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
        BufferedImage image;

        if (imagesCache.containsKey(path)) {
            image = imagesCache.get(path);
        } else {
            try {
                image = ImageIO.read(new File(path));
                imagesCache.put(path, image);
            } catch (IOException ex) {
                throw new ExecutionException("Image not found", ex);
            }
        }

        return image.getSubimage(x, y, w, h);
    }

    private void chargerIcones() {
        // image libre de droits utilisée pour les légumes : https://www.vecteezy.com/vector-art/2559196-bundle-of-fruits-and-vegetables-icons

        icones.put(IconNames.SALADE, chargerLegumeIcone(0, 0));
        icones.put(IconNames.CAROTTE, chargerLegumeIcone(1, 1));
        icones.put(IconNames.TOMATE, chargerLegumeIcone(1, 2));
        icones.put(IconNames.MAIS, chargerLegumeIcone(5, 1));
        icones.put(IconNames.AIL, chargerLegumeIcone(7, 1));
        icones.put(IconNames.ASPERGE, chargerLegumeIcone(2, 3));
        icones.put(IconNames.TERRE, Objects.requireNonNull(chargerIcone("Images/Terre.png")));
        icones.put(IconNames.VIDE, Objects.requireNonNull(chargerIcone("Images/Vide.png")));
        icones.put(IconNames.MUR, Objects.requireNonNull(chargerIcone("Images/Mur.png")));
        icones.put(IconNames.ARROSER, Objects.requireNonNull(chargerIcone("Images/Arroser.png")));
        icones.put(IconNames.PLANTER, Objects.requireNonNull(chargerIcone("Images/Planter.png")));
        icones.put(IconNames.RECOLTER, Objects.requireNonNull(chargerIcone("Images/Recolter.png")));
    }

    private ImageIcon chargerIcone(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            return new ImageIcon(image);
        } catch (IOException ex) {
            Logger.getLogger(VueControleurPotager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private ImageIcon chargerLegumeIcone(int xIndex, int yIndex) {
        if (xIndex < 0 || xIndex > 9 || yIndex < 0 || yIndex > 4) {
            throw new IllegalArgumentException("xIndex and yIndex must be between 0 and 9 and 0 and 4 respectively (number of sprites in the image)");
        }

        String path = "Images/data.png";
        int spriteWidth = 140;
        int spriteHeight = 140;
        int xSpaceBetweenSprites = 250;
        int ySpaceBetweenSprites = 250;

        int x = xIndex * spriteWidth + xIndex * xSpaceBetweenSprites;
        int y = yIndex * spriteHeight + yIndex * ySpaceBetweenSprites;


        return chargerIcone(path, x, y, spriteWidth, spriteHeight);
    }

    private ImageIcon chargerIcone(String path, int x, int y, int w, int h) {
        // charger une sous partie de l'image à partir de ses coordonnées dans path
        try {
            BufferedImage bi = getSubImage(path, x, y, w, h);
            // adapter la taille de l'image a la taille du composant (ici : 20x20)
            return new ImageIcon(bi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        } catch (ExecutionException e) {
            return null;
        }
    }
}
