//Krekoten Nikita variant 6
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

public class Main{

    static JPanel jPanel = new JPanel(null);

    public static void main(String args[]){
        JFrame jFrame = getFrame();

        LinkedList<Integer> priceData = new LinkedList<>();
        LinkedList<String> nameData = new LinkedList<>();

        jFrame.add(jPanel);

        JLabel nameLabel = new JLabel("Назва підприємства: ");
        nameLabel.setBounds(10, 110,200,20);
        jPanel.add(nameLabel);
        JTextField nameInput = new JTextField("",15);
        nameInput.setBounds(10,130, 200, 25);
        jPanel.add(nameInput);

        JLabel priceLabel = new JLabel("Ціна послуги: ");
        priceLabel.setBounds(10,160,200,20);
        jPanel.add(priceLabel);
        JTextField priceInput = new JTextField("",15);
        priceInput.setBounds(10,180, 200, 25);
        jPanel.add(priceInput);

        BarPanel chart = new BarPanel(priceData, nameData);
        chart.setPreferredSize(new Dimension(600, 500));
        chart.setBounds(250,0, 600,500);
        jPanel.add(chart);


        JButton submit = new JButton("Підтвердити");
        submit.setBounds(10, 220,200, 25);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (priceInput.getText().equals("")
                        || nameInput.getText().equals("")
                        || Integer.parseInt(priceInput.getText()) <=0){
                    return;
                }
                priceData.add( Integer.parseInt(priceInput.getText()));
                nameData.add(nameInput.getText());
                chart.repaint();
                nameInput.setText("");
                priceInput.setText("");
                if (priceData.size() >= 5 || nameData.size() >= 5){
                    Color bgColor = new Color(204,204,204);
                    nameInput.setEnabled(false);
                    priceInput.setEnabled(false);
                    submit.setEnabled(false);
                    nameInput.setBackground(bgColor);
                    priceInput.setBackground(bgColor);
                    submit.setBackground(bgColor);
                }
            }
        });
        jPanel.add(submit);


        jFrame.setVisible(true);
    }

    static JFrame getFrame(){
        JFrame jFrame = new JFrame();
        int windowWidth =700;
        int windowHeight = 400;

        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width / 2 - windowWidth/2, dimension.height / 2 - windowHeight/2, windowWidth, windowHeight);
        jFrame.setTitle("Module 1 Krekoten Nikita");
        return jFrame;
    }
}




class BarPanel extends JPanel {
    private static final Color BACKGROUND_COLOR = Color.white;
    private static final Color BAR_COLOR = Color.red;

    private LinkedList<Integer> inputPriceData;
    private LinkedList<String> inputNameData;

    public BarPanel(LinkedList<Integer> inputPriceData, LinkedList<String> inputNameData) {
        this.inputPriceData = inputPriceData;
        this.inputNameData = inputNameData;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        drawBars(g);
    }

    private void drawBars(final Graphics g) {
        int OUTER_MARGIN = 20;
        int WIDTH = 800 + 2 * OUTER_MARGIN;
        int HEIGHT = 600 + 2 * OUTER_MARGIN;
        int scale = 1;

        for (int i = 0; i < inputPriceData.size();i++){
            int temp = Integer.toString(inputPriceData.get(i)).length();
            if (temp > scale) {
                scale = temp;
            }
        }

        scale = (int) Math.pow(10,scale) /10;

        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        this.removeAll(); // clear the panel

        final int barWidth = 70;
        for (int i = 0; i < inputPriceData.size() && i < inputNameData.size(); i++) {
            final int x = OUTER_MARGIN + 80 * i;
            final int barHeight = 30 * inputPriceData.get(i) / scale;
            final int y = 300 - barHeight;
            g.setColor(BAR_COLOR);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.BLACK);

            JLabel price = new JLabel(Integer.toString(inputPriceData.get(i)));
            price.setBounds(x,y - 20, barWidth,25);
            this.add(price);

            JLabel companyName = new JLabel(this.inputNameData.get(i));
            companyName.setBounds(x,y + barHeight,barWidth,20);
            this.add(companyName);
        }
    }
}