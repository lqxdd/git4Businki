import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class dynamicTextFieldArrays extends Frame {
    private JTextField[] firstArray;
    private JTextField[] secondArray;
    private Button buttonCount;

    dynamicTextFieldArrays(String title) {
        super(title);
        setLayout(null);

        Font f = new Font("Serif", Font.BOLD, 16);
        setFont(f);

        Label label = new Label("Оберіть розмірність масивів:", Label.LEFT);
        label.setBounds(24, 30, 300, 30);
        add(label);

        JSpinner spinner = new JSpinner();
        spinner.setBounds(325 , 38, 50, 20);
        add(spinner);
        spinner.setValue(1);

        spinner.addChangeListener(e -> {
            if ((int) spinner.getValue() > 10 || (int) spinner.getValue() < 1) {
                spinner.setValue(1);
            }
        });
        JSpinner.DefaultEditor editor = ( JSpinner.DefaultEditor ) spinner.getEditor();
        editor.getTextField().setEnabled( true );
        editor.getTextField().setEditable( false );

        Button buttonSet = new Button("Створити масиви");
        buttonSet.setBounds(24 , 60, 350, 30);
        add(buttonSet);
        buttonSet.addActionListener(e -> {
            int number = (int) spinner.getValue();
            firstArray = new JTextField[number];
            for (int i = 0, m = 20; i < number; i++, m+=40) {
                firstArray[i] = new JTextField();
                firstArray[i].addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!((c >= '0') && (c <= '9') ||
                                (c == KeyEvent.VK_BACK_SPACE) ||
                                (c == KeyEvent.VK_DELETE))) {
                            getToolkit().beep();
                            e.consume();
                        }
                    }
                });
                firstArray[i].setBounds(90, 110+m , 40, 20);
                add(firstArray[i]);
            }

            secondArray = new JTextField[number];
            for (int i = 0, m = 20; i < number; i++, m+=40) {
                secondArray[i] = new JTextField();
                secondArray[i].addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!((c >= '0') && (c <= '9') ||
                                (c == KeyEvent.VK_BACK_SPACE) ||
                                (c == KeyEvent.VK_DELETE))) {
                            getToolkit().beep();
                            e.consume();
                        }
                    }
                });
                secondArray[i].setBounds(260, 110+m , 40, 20);
                add(secondArray[i]);
            }

            buttonCount.setBounds(25 ,  170 + (number-1)*40, 350, 30);
            Label arrayLabel1 = new Label("перший масив", Label.LEFT);
            arrayLabel1.setBounds(64, 95, 110, 30);
            add(arrayLabel1);

            Label arrayLabel2 = new Label("другий масив", Label.LEFT);
            arrayLabel2.setBounds(230, 95, 100, 30);
            add(arrayLabel2);
            buttonSet.setEnabled(false);
            spinner.setEnabled(false);
            setSize(400, 110 + 40*number + 70);
            repaint();
        });
         buttonCount = new Button("Де більша сума?");

        add(buttonCount);
        buttonCount.addActionListener(e -> {
            revalidate();
            repaint();
            int number = (int)spinner.getValue();
            double sum1 = 0, sum2 = 0;
            for (int i = 0; i < number; i++) {
                if(firstArray[i].getText().equals("")){
                    firstArray[i].setText("0");
                }
                if(secondArray[i].getText().equals("")){
                    secondArray[i].setText("0");
                }
                sum1 += Integer.parseInt(firstArray[i].getText());
                sum2 += Integer.parseInt(secondArray[i].getText());
            }
            if(sum1>sum2){
                buttonCount.setLabel("Сума елементів більша у першого масива");
            } else if (sum2>sum1){
                buttonCount.setLabel("Сума елементів більша у другого масива");
            } else {
                buttonCount.setLabel("=)");
            }

            }
        );
        setSize(400, 110);
        setVisible(true);
    }

    public static void main(String[] args) {
        Frame f = new dynamicTextFieldArrays("Модульний контроль №1 Грицунь");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });
    }
}
