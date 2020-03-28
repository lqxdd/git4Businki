package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.awt.BorderLayout.NORTH;

public class InterfacePanel extends JPanel {


    private DataManager dataManager;
    private ChartPanel chartPanel;
    private JTextField tfName, tfValue;
    private JButton btAdd;

    public InterfacePanel(DataManager dataManager, ChartPanel chartPanel, NamesPanel namesPanel) {
        this.dataManager = dataManager;
        this.chartPanel = chartPanel;
        setPreferredSize(new Dimension(250, 150));
        setLayout(null);

        tfName = new JTextField("Введите здесь марку, модель");
        tfName.setBounds(0, 12, 210, 25);

        tfValue = new JTextField("Введите здесь количество продаж");
        tfValue.setBounds(0, 42, 210, 25);

        btAdd = new JButton("ADD");
        btAdd.setBounds(0, 72, 210, 25);


        btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int value;
                try {

                    value = Integer.parseInt(tfValue.getText());
                    dataManager.elements.add(new DataManager.Element(tfName.getText(), value));
                    chartPanel.repaint();
                    namesPanel.repaint();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        add(tfName);
        add(tfValue);
        add(btAdd);
    }
}
