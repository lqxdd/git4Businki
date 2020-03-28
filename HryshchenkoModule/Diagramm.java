import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Diagramm extends JFrame  {
    JLayeredPane myPanel=getLayeredPane();
    JLabel nameRegionLabel, amountPopulationLabel, maxAmountPopulation, halfAmountPopulation;
    JLabel regionLabel = new JLabel("Області"), zeroAmountPopulation;
    JTextField nameRegionTextField, amountPopulationTextField;
    JButton toAddButton;
    LineOfDiagram myDiagram= new LineOfDiagram();
    ElementOfDiagram element= new ElementOfDiagram();
    JLabel nameRegionDiagramLabel, amountPopulationDiagramLabel;
    int distance=0, amountColons=0;

    Diagramm(){
        super("Діаграма");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        toAddButton=new JButton("Додати");

        nameRegionLabel=new JLabel("Введіть назву області");
        amountPopulationLabel= new JLabel("Введіть кількість населення(в десятках тис.)");
        regionLabel.setFont(new Font("Romain",Font.ITALIC,8));
        maxAmountPopulation=new JLabel("4 млн.");
        halfAmountPopulation=new JLabel("2 млн");
        zeroAmountPopulation=new JLabel("0");

        nameRegionTextField=new JTextField();
        amountPopulationTextField=new JTextField();

        toAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //перевірка вводу
                    if (amountColons == 5) {
                        JOptionPane.showMessageDialog(null, "Забагто стовпчиків!");
                    }
                    else if((Integer.parseInt(amountPopulationTextField.getText()))>400){
                        JOptionPane.showMessageDialog(null,"Занадто велике число!");
                    }
                    else if((Integer.parseInt(amountPopulationTextField.getText()))<0) {
                        JOptionPane.showMessageDialog(null,"Помилка вводу!");
                    }
                    else {
                        element = new ElementOfDiagram();
                        element.setBounds(50 + distance, 0, 1000, 1000);
                        distance = distance + 100;//створення відстань між стовпичками
                        myPanel.add(element);
                        amountColons++;
                        nameRegionDiagramLabel = new JLabel(nameRegionTextField.getText());
                        myPanel.add(nameRegionDiagramLabel);
                        nameRegionDiagramLabel.setBounds(distance, 425, 80, 50);
                        amountPopulationDiagramLabel = new JLabel(amountPopulationTextField.getText());
                        myPanel.add(amountPopulationDiagramLabel);
                        amountPopulationDiagramLabel.setBounds(distance, (390 - Integer.parseInt(amountPopulationTextField.getText())), 80, 50);
                    }

                } catch (IllegalArgumentException e1) {
                    JOptionPane.showMessageDialog(null, "Помилка!");
                }
            }});

        myDiagram.setBounds(25,0,1000,1000);
        nameRegionLabel.setBounds(25,525,150,25);
        nameRegionTextField.setBounds(25,560,200,25);
        amountPopulationLabel.setBounds(250,525,300,25);
        amountPopulationTextField.setBounds(250,560,200,25);
        toAddButton.setBounds(550,550,100,100);
        regionLabel.setBounds(600,407,100,25);
        zeroAmountPopulation.setBounds(30,415,50,25);
        halfAmountPopulation.setBounds(10,225,50,25);
        maxAmountPopulation.setBounds(7,20,50,25);

        myPanel.add(nameRegionLabel);
        myPanel.add(zeroAmountPopulation);
        myPanel.add(halfAmountPopulation);
        myPanel.add(maxAmountPopulation);
        myPanel.add(amountPopulationLabel);
        myPanel.add(nameRegionTextField);
        myPanel.add(amountPopulationTextField);
        myPanel.add(toAddButton);
        myPanel.add(regionLabel);
        myPanel.add(myDiagram);

        setSize(700,700);
    }
    public static class LineOfDiagram extends JComponent{
        @Override
        public void paintComponent(Graphics figure) {
            figure.drawLine(25,25,25,425);
            figure.drawLine(25,425,625,425);
        }
    }
    public class ElementOfDiagram extends JComponent{
        @Override
        public void paintComponent(Graphics figure){
            figure.setColor(Color.RED);
            figure.fillRect(50,(425-Integer.parseInt(amountPopulationTextField.getText())),50, ((Integer.parseInt(amountPopulationTextField.getText()))));
        }
    }
}

