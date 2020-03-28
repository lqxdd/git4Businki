package com.company;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        JPanel mainPanel = new JPanel();
        DataManager dataManager = new DataManager();
        NamesPanel namesPanel = new NamesPanel(dataManager);
        ChartPanel chartPanel = new ChartPanel(dataManager);
        InterfacePanel interfacePanel = new InterfacePanel(dataManager, chartPanel, namesPanel);
        mainPanel.add(chartPanel);
        mainPanel.add(namesPanel);
        mainPanel.add(interfacePanel);

        jFrame.add(mainPanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}