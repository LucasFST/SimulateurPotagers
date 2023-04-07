package a_vegetable_garden.vue_controleur.vues.components;

import a_vegetable_garden.modele.Ordonnanceur;

import javax.swing.*;
import java.util.Hashtable;

public class TimeSlider extends JSlider {
    public TimeSlider() {
        super(SwingConstants.HORIZONTAL, 0, 4, (int) Ordonnanceur.getInstance().getDelay() / 250);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        for (int i = 0; i <= 4; i++) {
            labelTable.put(i, new JLabel((double) i / 4 + "s"));
        }

        setLabelTable(labelTable);
        setPaintTicks(true);
        setPaintLabels(true);

        this.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            int delay = source.getValue();
            Ordonnanceur.getInstance().setDelay(250L * delay);
        });
    }
}
