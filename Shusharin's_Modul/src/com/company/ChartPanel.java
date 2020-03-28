package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChartPanel extends JPanel {
    private DataManager dataManager;

    private Color[] colors = {
            new Color(240, 128, 128),
            new Color(152, 251, 152),
            new Color(32, 178, 170),
            new Color(238, 130, 238),
            new Color(244, 164, 96)
    };

    public ChartPanel(DataManager dataManager) {
        this.dataManager = dataManager;
        setPreferredSize(new Dimension(300, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ArrayList<DataManager.Element> normalized = dataManager.getNormalized(360);
        int currentAngle = 0;

        for (int i=0; i < normalized.size(); i++) {
            DataManager.Element element = normalized.get(i);
            g.setColor(colors[i]);
            g.fillArc(0, 0, 290, 290, currentAngle, (int) element.value);
            currentAngle += element.value;
        }
    }
}
