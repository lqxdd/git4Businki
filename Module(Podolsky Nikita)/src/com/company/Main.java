package com.company;

import jdk.swing.interop.DragSourceContextWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class DTP extends JFrame {
    JTextField City, Amount;
    JPanel Diagram;
    Label error;
    ArrayList<String> Cities = new ArrayList();
    ArrayList<Integer> Amounts = new ArrayList();
    DTP() {
        super("Діаграма кількості ДТП по регіонам України");
        setLayout(new BorderLayout());
        Diagram = new JPanel(null);
        add(Diagram, BorderLayout.CENTER);
        setSize(700, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        City = new JTextField();
        City.setBounds(50,50,200,30);
        Diagram.add(City);

        Amount = new JTextField();
        Amount.setBounds(420,50,200,30);
        Diagram.add(Amount);

        error = new Label("", Label.CENTER);
        error.setForeground(Color.RED);
        error.setBounds(230,10,210,30);
        Diagram.add(error);

        Label input_city = new Label("Введіть назву регіону:", Label.CENTER);
        input_city.setBounds(50,10,200,30);
        Diagram.add(input_city);

        Label input_amount = new Label("Введіть кількість ДТП:", Label.CENTER);
        input_amount.setBounds(420,10,200,30);
        Diagram.add(input_amount);

        Button input_DTP = new Button("Ввести");
        input_DTP.setBounds(290, 50, 80, 30);
        Diagram.add(input_DTP);

        Button Draw_Diagram = new Button("Побудувати діаграму");
        Draw_Diagram.setBounds(265, 82, 130, 30);
        Diagram.add(Draw_Diagram);

        Button Clear_Diagram = new Button("Очистити діаграму");
        Clear_Diagram.setBounds(265, 115, 130, 30);
        Diagram.add(Clear_Diagram);

        Chart chart = new Chart(Amounts, Cities);
        chart.setPreferredSize(new Dimension(600, 500));
        chart.setBounds(25,150, 600,400);
        Diagram.add(chart);


        input_DTP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scan = e.getActionCommand();
                error.setText("");
                if (City.getText().isEmpty() || Amount.getText().isEmpty()) {
                    error.setText("Заповніть всі поля");
                    City.setText("");
                    Amount.setText("");
                    return;
                }

                Cities.add(City.getText());
                try {
                    Amounts.add(Integer.parseInt(Amount.getText()));
                } catch (NumberFormatException a) {
                    error.setText("Кількість ДТП має бути цілим числом");
                }

                City.setText("");
                Amount.setText("");
            }
        });
        Draw_Diagram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                {
                    chart.repaint();
                }
            }
        });
        Clear_Diagram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                {
                    Amounts.clear();
                    Cities.clear();
                    chart.removeAll();
                    chart.repaint();
                }
            }
        });
        }
    }

class Chart extends JPanel {
    private static final Color Background = Color.white;
    private static final Color Chart_Color = Color.red;

    private ArrayList<Integer> Amounts;
    private ArrayList<String> Cities;

    public Chart(ArrayList<Integer> Amounts, ArrayList<String> Cities) {
        this.Amounts = Amounts;
        this.Cities = Cities;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        drawChart(g);
    }

    private void drawChart(final Graphics g) {
        int external_field = 20;
        int Width = 700 + 2 * external_field;
        int Height = 600 + 2 * external_field;

        g.setColor(Background);
        g.fillRect(0, 0, Width, Height);
        final int barWidth = 60;
        int max = 0;
        int percent[] = new int [Amounts.size()];
        for(int i = 0; i < Amounts.size(); i++)
        {
            if(Amounts.get(i) > max)
                max = Amounts.get(i);
        }
        for(int i = 0; i < Amounts.size(); i++)
        {
            percent[i] = (Amounts.get(i) * 100) / max;
        }
        for (int i = 0; i < Amounts.size(); i++) {
            final int x = external_field + 80 * i;
            final int chartsHeight = 3 * percent[i];
            final int y = 350 - chartsHeight;
            g.setColor(Chart_Color);
            g.fillRect(x, y, barWidth, chartsHeight);
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(Amounts.get(i)), x + barWidth / 2, y);
            JLabel companyName = new JLabel(this.Cities.get(i));
            companyName.setBounds(x,y + chartsHeight,barWidth,20);
            this.add(companyName);
        }
    }
}
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DTP();
            }
        });
    }
}