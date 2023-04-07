package avegetablegarden.vuecontroleur.vues.components;

import avegetablegarden.modele.Ordonnanceur;

import javax.swing.*;
import java.util.Hashtable;

public class TimeSlider extends JSlider {
    public TimeSlider() {
        super(SwingConstants.HORIZONTAL, 0, 4, (int) Ordonnanceur.getInstance().getDelay() / 250);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        for (int i = 0; i <= 4; i++) {
            labelTable.put(i, new JLabel(Double.toString((double) i / 4) + "s"));
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
