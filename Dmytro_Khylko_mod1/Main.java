package com.modul1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public Main(){
        JFrame mainFrame = new JFrame("Calendar for current week");
        JPanel displayDays = new JPanel(); //Panel where will be displayed days of week
        mainFrame.setSize(600, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridBagLayout mainFrameLayout = new GridBagLayout();
        GridBagConstraints mainFrameLayoutGbc = new GridBagConstraints();
        mainFrame.setLayout(mainFrameLayout);
        mainFrameLayoutGbc.gridx = 0;
        mainFrameLayoutGbc.gridy = 0;
        mainFrame.add(displayDays, mainFrameLayoutGbc);
        new DaysOfWeek(displayDays, mainFrame);
        mainFrame.setVisible(true);
    }
    public static void main(String[] args) {
    new Main();

    }
}