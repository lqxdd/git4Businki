import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Diagrama extends JFrame {
    private ArrayList<String> riversName;
    private ArrayList <Integer> riversLength;
    private int height;
    private int width;
    private int widthRect;
    private int heigthRect;

    Diagrama(String s, ArrayList <String> riversName, ArrayList <Integer> riversLength){
        super(s);
        this.riversLength = riversLength;
        this.riversName = riversName;
        setBackground(Color.white);
    }
    //геренация рандомного цвета
    private Color color(){

        Random random = new Random();
        final float hue = random.nextFloat();
        final float saturation = 0.9f;
        final float luminance = 1.0f;
        Color color = Color.getHSBColor(hue, saturation, luminance);
        return color;
    }
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        height = (int)(screenHeight*0.8);
        width = (int)(screenWidth*0.8);

        // сглаживание
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g2.setRenderingHints(rh);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setFont( new Font("Courier", Font.BOLD, 15));

        //Динамическое определение ширины прямоугольников
        if(riversLength.size() > 14){
            widthRect = (width-20)/riversLength.size()-10;
        }else{
            widthRect = 50;
        }

        int max = Collections.max(riversLength);
        System.out.println(max);

        for(int i=0; i<riversName.size(); i++){
            //Динамическое определение высоты прямоугольников
            if(max > height-100){
                System.out.println("heigth: " + height);
                float temp = max/(height-100);
                System.out.println("temp: " + temp);
                heigthRect = (int)((riversLength.get(i)/temp)*0.75);
                System.out.println("heigthRect: " + heigthRect);
            }else{
                heigthRect = (int)(riversLength.get(i)*0.5);
            }
            //текст под углом
            g2.setColor(color());
            g2.rotate(Math.PI / 4.0,(widthRect +10)*i + 20,height-heigthRect-130);
            g2.drawString(riversLength.get(i).toString(), (widthRect +10)*i + 20,height-heigthRect-130);
            g2.rotate(-Math.PI / 4.0, (widthRect +10)*i + 20,height-heigthRect-130);
            //сам прямоугольник
            g2.fillRect((widthRect +10)*i + 20, height-heigthRect-100, widthRect, heigthRect);
            g2.rotate(Math.PI / 4.0,(widthRect +10)*i + 20,height-90);
            g2.drawString(riversName.get(i), (widthRect +10)*i + 20,height-90);
            g2.rotate(-Math.PI / 4.0, (widthRect +10)*i + 20,height-90);
        }
    }
}
