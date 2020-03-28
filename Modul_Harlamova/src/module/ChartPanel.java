package module;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChartPanel extends JPanel {
    private DataManager dataManager;

    public ChartPanel(DataManager dataManager) {
        this.dataManager = dataManager;

        setPreferredSize(new Dimension(300, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(0x43544F));

        ArrayList<DataManager.Element> normalized = dataManager.getNormalized(getHeight() - 20);
        ArrayList<Integer> quantity = new ArrayList<>();
        int width = 67;
        if (normalized.size() > 4)
            width = 4 * 67 / normalized.size();
        for (int i = 0; i < normalized.size(); i++) {
            DataManager.Element e = normalized.get(i);
            quantity.add(dataManager.elements.get(i).value);
            String name = String.valueOf(e.name);
            String quantity_ = String.valueOf(quantity.get(i));
            g.fillRect((int) (i * width + 0.12 * width), getHeight() - e.value, (int) (width - 0.12 * width), e.value);
            g.setColor(new Color(0x000000));
            g.drawString("Year : "+name, ((int) (i * width + 0.12 * width)), getHeight() - e.value-1);
            g.setColor(Color.WHITE);
            g.drawString(quantity_,(int) (i * width + 0.12 * width *1.3),300);
            g.setColor(new Color(0x43544F));
        }
    }
}