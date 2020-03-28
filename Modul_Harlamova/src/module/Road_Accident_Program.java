package module;

import javax.swing.*;
import java.awt.*;

public class Road_Accident_Program {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Road Accident Program");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        DataManager dataManager = new DataManager();

        ChartPanel chartPanel = new ChartPanel(dataManager);
        InterfacePanel interfacePanel = new InterfacePanel(dataManager, chartPanel);

        mainPanel.add(chartPanel , BorderLayout.CENTER);
        mainPanel.add(interfacePanel , BorderLayout.EAST);

        jFrame.add(mainPanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
