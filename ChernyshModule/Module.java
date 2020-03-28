package labs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Module {


    public static void main(String[] args) {


        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {

                        JFrame moduleFrame = new ModuleMainWindow("Module");

                    }
                });


    }

    static class ModuleMainWindow extends JFrame implements ActionListener
    {

        //нужно добавить панельку для 1,3,5

        ArrayList<JTextField> tfArrFirst= new ArrayList<>();
        ArrayList<JTextField> tfArrSecond= new ArrayList<>();
        JTextField tfFirstArrSize = new JTextField();
        JTextField tfSecondArrSize = new JTextField();
        JPanel pArrSizeSet = new JPanel();//1
        JLabel lFirstArr;//2
        JPanel pFirstArrElem = new JPanel();//3
        JLabel lSecondArr;//4
        JPanel pSecondArrElem = new JPanel();//5
        JButton bShowResults;

        ModuleMainWindow(String title) {

            super(title);
            setLayout(new GridLayout(6,1));

            pArrSizeSet.setLayout(new FlowLayout());
            pFirstArrElem.setLayout(new FlowLayout());
            pSecondArrElem.setLayout(new FlowLayout());



            //region Panel First

            JLabel lFirstDim = new JLabel("Размерность первого массива:");
            lFirstDim.setSize(100,30);
            pArrSizeSet.add(lFirstDim);

            tfFirstArrSize.setColumns(5);
            pArrSizeSet.add(tfFirstArrSize);

            JLabel lSecondDim = new JLabel("Размерность второго массива:");
            lSecondDim.setSize(100,30);
            pArrSizeSet.add(lSecondDim);

            tfSecondArrSize.setColumns(5);
            pArrSizeSet.add(tfSecondArrSize);

            JButton dimSetButton = new JButton("Ввод");
            dimSetButton.setSize(150, 30);
            dimSetButton.addActionListener(this);
            pArrSizeSet.add(dimSetButton);

            add(pArrSizeSet);

            //endregion



            //region Label Second

            lFirstArr = new JLabel("Первый массив");
            lFirstArr.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            lFirstArr.setSize(100,30);

            //endregion

            //region Label Fourth

            lSecondArr = new JLabel("Второй массив");
            lSecondArr.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            lSecondArr.setSize(100,30);

            //endregion

            bShowResults = new JButton("Показать результат");
            bShowResults.setSize(150, 30);
            bShowResults.addActionListener(new ShowResults());
            //dimSetButton.addActionListener(this);
            //pArrSizeSet.add(dimSetButton);


            /*for (int i = 0; i<10; i++)
            {

                tfArrFirst.add(new JTextField());
                tfArrFirst.get(i).setSize(100,30);

            }*/



            int windWidth = 700, windHeight = 400;
            setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - windWidth / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - windHeight / 2, windWidth, windHeight);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            setResizable(false);



        }

        public void actionPerformed(ActionEvent e)
        {

            try{

                if(tfArrFirst.size()!=0)
                {

                    for (int i = 0; i< tfArrFirst.size(); i++)
                    {

                        tfArrFirst.get(i).setText("");


                    }

                }

                if(tfArrSecond.size()!=0)
                {

                    for (int i = 0; i< tfArrSecond.size(); i++)
                    {

                        tfArrSecond.get(i).setText("");


                    }

                }

                pFirstArrElem.removeAll();
                pSecondArrElem.removeAll();

                int firstArrSize = Integer.parseInt(tfFirstArrSize.getText());
                int secondArrSize = Integer.parseInt(tfSecondArrSize.getText());

                if (firstArrSize<1||secondArrSize<1)
                {

                    throw new NumberFormatException("1");

                }

                if (firstArrSize>20||secondArrSize>20)
                {

                    throw new RuntimeException();

                }

                this.add(lFirstArr);

                for (int i = 0; i< firstArrSize; i++)
                {

                    tfArrFirst.add(new JTextField());
                    tfArrFirst.get(i).setColumns(5);
                    pFirstArrElem.add(tfArrFirst.get(i));

                }

                this.add(pFirstArrElem);

                this.add(lSecondArr);

                for (int i = 0; i< secondArrSize; i++)
                {

                    tfArrSecond.add(new JTextField());
                    tfArrSecond.get(i).setColumns(5);
                    pSecondArrElem.add(tfArrSecond.get(i));

                }

                this.add(pSecondArrElem);

                this.add(bShowResults);

                this.revalidate();

                this.repaint();

            }
            catch (NumberFormatException E)
            {

                JOptionPane.showMessageDialog(new JFrame(),"Введите правильные значения","Ошибка ввода",JOptionPane.ERROR_MESSAGE);

            }
            catch (RuntimeException E)
            {

                JOptionPane.showMessageDialog(new JFrame(),"Введите размерность <=20","Ошибка ввода",JOptionPane.ERROR_MESSAGE);

            }


        }

        class ShowResults implements ActionListener
        {


            public void actionPerformed(ActionEvent e) {

                try
                {

                    int firstArrSize = Integer.parseInt(tfFirstArrSize.getText());
                    int secondArrSize = Integer.parseInt(tfSecondArrSize.getText());

                    ArrayList<Double> repeatingNumsArr = new ArrayList<Double>();
                    ArrayList<Double> firstArr = new ArrayList<Double>();
                    ArrayList<Double> secondArr = new ArrayList<Double>();

                    for (int i= 0; i<firstArrSize;i++)
                    {

                        firstArr.add(Double.parseDouble(tfArrFirst.get(i).getText()));

                    }

                    for (int i= 0; i<secondArrSize;i++)
                    {

                        secondArr.add(Double.parseDouble(tfArrSecond.get(i).getText()));

                    }

                    for (int i = 0; i<firstArrSize;i++)
                    {

                        for (int j= 0; j<secondArrSize;j++)
                        {

                            if (Double.compare(firstArr.get(i),secondArr.get(j))==0)
                            {

                                boolean sw = false;

                                for (int k = 0; k<repeatingNumsArr.size();k++)
                                {

                                    if(Double.compare(repeatingNumsArr.get(k),firstArr.get(i))==0)
                                    {
                                        sw= true;
                                        break;

                                    }

                                }

                                if (sw == false)
                                {

                                    repeatingNumsArr.add(firstArr.get(i));

                                }


                            }

                        }

                    }




                    JFrame moduleInfoWindow = new ModuleInfoWindow("Module", repeatingNumsArr);



                }
                catch (NumberFormatException E)
                {

                    JOptionPane.showMessageDialog(new JFrame(),"Введите правильные значения","Ошибка ввода",JOptionPane.ERROR_MESSAGE);

                }



            }

        }

    }

    static class ModuleInfoWindow extends JFrame
    {

        ModuleInfoWindow(String title, ArrayList<Double> repeatingNumsArr)
        {

            super(title);

            setLayout(new FlowLayout());

            ArrayList<JLabel> tfArrSecond= new ArrayList<>();

            for (int i = 0; i<repeatingNumsArr.size(); i++)
            {

                tfArrSecond.add(new JLabel(Double.toString(repeatingNumsArr.get(i))));
                add(tfArrSecond.get(i));

            }

            int windWidth = 400, windHeight = 200;
            setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - windWidth / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - windHeight / 2, windWidth, windHeight);

            //pack();

            //setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - getBounds().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - getBounds().height / 2);

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
            setResizable(false);


        }

    }

}
