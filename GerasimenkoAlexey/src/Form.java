import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.ArrayList;

//Класс фильтрации чилес
class DigitFilter extends DocumentFilter {
    private static final String DIGITS = "\\d+";

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string.matches(DIGITS)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
        if (string.matches(DIGITS)) {
            super.replace(fb, offset, length, string, attrs);
        }
    }
}

public class Form extends JFrame {
    private JPanel contentPane;
    private JButton buttonShow;
    private JTextField textFieldName;
    private JTextField textFieldLength;
    private JButton buttonAdd;
    private JButton buttonClean;
    private final int width = 300;
    private final int height = 200;
    private int screenHeight;
    private int screenWidth;
    private ArrayList <String> riversName = new ArrayList<>();
    private ArrayList <Integer> riversLength = new ArrayList<>();


    private Form() {
        super("Ukrainian Rivers");

        textFieldLength.setText("0");
        //Ввод только чисел в TextField
        PlainDocument doc = (PlainDocument) textFieldLength.getDocument();
        doc.setDocumentFilter(new DigitFilter());

        //беру размеры экрана
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        screenHeight = screenSize.height;
        screenWidth = screenSize.width;

        //Отцентрирую экран
        setLocation(screenWidth/2 - width/2, screenHeight/2 - height/2);
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonShow);

        buttonShow.addActionListener(e -> onShow());
        buttonAdd.addActionListener(e -> onAdd());
        buttonClean.addActionListener(e -> onClean());
    }

    private void onAdd(){
        riversLength.add(Integer.parseInt(textFieldLength.getText()));
        riversName.add(textFieldName.getText());
        textFieldLength.setText("0");
        textFieldName.setText("");
    }
    private void onClean(){
        int length = riversLength.size();
        riversLength.clear();
        riversName.clear();
        System.out.println(riversLength.size());
        if(riversName.size() == 0 || riversLength.size() ==0){
            JOptionPane.showMessageDialog(Form.this,
                    new String[] {"Все успешно удаленно!( " + length + " элемента)"},
                    "Информация",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void onShow() {
        if(riversName.size() == 0 || riversLength.size() ==0){
            JOptionPane.showMessageDialog(Form.this,
                    new String[] {"Убедитесь что вы заполнили все поля!"},
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Diagrama window = new Diagrama("Диаграмма", riversName, riversLength);
        window.setVisible(true);
        window.setSize((int)(screenWidth*0.8), (int)(screenHeight*0.8));
        window.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Form dialog = new Form();
        dialog.pack();
        dialog.setSize(dialog.width,dialog.height);
        dialog.setVisible(true);
    }
}
