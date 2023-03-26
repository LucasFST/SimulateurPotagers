package vueControleur.vues.components;

import modele.Ordonnanceur;

import javax.swing.*;

public class TimeSlider extends JSlider {
    public TimeSlider() {
        super(JSlider.HORIZONTAL, 500, 10000, (int) Ordonnanceur.getInstance().getDelay());
        setMajorTickSpacing(500);
        setMinorTickSpacing(100);
        setPaintTicks(true);
        setPaintLabels(true);

        this.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            int delay = (int) source.getValue();
            Ordonnanceur.getInstance().setDelay(delay);
        });
    }
}
