package com.company;
import javax.swing.*;
import java.awt.*;

public class NamesPanel extends JPanel {
    private DataManager dataManager;

    private Color[] colors = {
            new Color(240, 128, 128),
            new Color(152, 251, 152),
            new Color(32, 178, 170),
            new Color(238, 130, 238),
            new Color(244, 164, 96)
    };

    public NamesPanel(DataManager dataManager) {
        this.dataManager = dataManager;
        setPreferredSize(new Dimension(400, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int letDown = 0;

        for (int i=0; i < dataManager.elements.size(); i++) {
            String e = "";
            e = dataManager.elements.get(i).name + " Кол-во: " + dataManager.elements.get(i).value;

            g.setFont(new Font("Cursive", Font.ITALIC, 14));
            g.drawString(e, 10, 120 + letDown);

            g.setColor(colors[i]);
            g.fillRect(0, 120 + letDown, 5, 5);
            g.setColor(Color.BLACK);

            letDown += 25;
        }

    }


}
