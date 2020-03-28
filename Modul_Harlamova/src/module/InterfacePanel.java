package module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfacePanel extends JPanel {
    public DataManager dataManager;
    public ChartPanel chartPanel;
    static int check_number =1885 ;
    private JTextField textField_year, textField_quantity_incident;
    public JButton button_add;


    public InterfacePanel(DataManager dataManager, ChartPanel chartPanel) {

        class Action_button_add implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                int year;
                int quantity_incident;
                try {
                    year = Integer.parseInt(textField_year.getText());
                    quantity_incident = Integer.parseInt(textField_quantity_incident.getText());
                    if(year>check_number) {
                        dataManager.elements.add(new DataManager.Element(year ,quantity_incident ));
                        chartPanel.repaint();
                        check_number = year;
                    }else System.out.println("Year cannot be less than the previous!");
                } catch (Exception exp) {
                    exp.printStackTrace();
                    System.out.println("You entered not a number!");
                }

            }
        }
        this.dataManager = dataManager;
        this.chartPanel = chartPanel;
        setPreferredSize(new Dimension(300, 75));
        setBackground(new Color(135, 157, 151));
        setLayout(new GridBagLayout());

        textField_year = new JTextField();
        textField_quantity_incident = new JTextField();

        JLabel label_year = new JLabel(" Year incident");
        JLabel label_quantity_incident = new JLabel(" Quantity incident");

        button_add = new JButton(" Add data ");
        button_add.setBackground(new Color(0x43544F));
        button_add.setForeground(Color.WHITE);
        button_add.addActionListener(new Action_button_add() );

        textField_year.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode()==KeyEvent.VK_DOWN) {
                    textField_quantity_incident.grabFocus();
                }
            }
        });
        textField_quantity_incident.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode()==KeyEvent.VK_ENTER)
                    textField_quantity_incident.addActionListener(new Action_button_add());
                if(keyEvent.getKeyCode()==KeyEvent.VK_UP)
                    textField_year.grabFocus();
            }
        });
        add(label_year, new GridBagConstraints(0,0,1,1,1,1,
                GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,
                new Insets(10,0,10,0),1,1));
        add(textField_year, new GridBagConstraints(1,0,1,1,3,1,
                GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
                new Insets(10,20,10,10),1,1));
        add(label_quantity_incident,new GridBagConstraints(0,1,1,1,1,1,
                GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,
                new Insets(10,0,10,0),1,1));
        add(textField_quantity_incident,new GridBagConstraints(1,1,1,1,3,1,
                GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
                new Insets(10,20,10,10),1,1));
        add(button_add,new GridBagConstraints(0,2,3,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
                new Insets(10,20,10,10),1,1));
    }
}
