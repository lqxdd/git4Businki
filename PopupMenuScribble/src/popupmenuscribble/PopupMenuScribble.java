/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popupmenuscribble;

/**
 *
 * @author Viktoriia_Mironova
 */
import java.awt.*;
import java.awt.event.*;
public class PopupMenuScribble extends Frame{
    public PopupMenuScribble(String s){ 
        super (s) ;
        ScrollPane pane = new ScrollPane();
        pane.setSize(300, 300);
        add(pane, BorderLayout.CENTER);
        Scribble scr = new Scribble(this, 500, 500);
        pane.add(scr);
        addWindowListener(new WinClose());
        pack ();
        setVisible(true);
    }
    class WinClose extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }
    }
    public static void main(String[] args){
        new PopupMenuScribble(" \"Рисувалка\" з спливаючим меню");
    }
}
class Scribble extends Component implements ActionListener{
    protected int lastX, lastY, w, h;
    protected Color currColor = Color.black;
    protected Frame f;
    protected PopupMenu c;
    public Scribble(Frame frame, int width, int height){
        f = frame; w = width; h = height;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK|AWTEvent.MOUSE_MOTION_EVENT_MASK);
        c = new PopupMenu ("Color") ;
        add(c);
        MenuItem clear = new MenuItem("Clear", new MenuShortcut(KeyEvent.VK_C));
        MenuItem red = new MenuItem("Red");
        MenuItem green = new MenuItem("Green");
        MenuItem blue = new MenuItem("Blue");
        MenuItem black = new MenuItem("Black");
        c.add(red); c.add(green); c.add(blue);
        c.add(black); c.addSeparator(); c.add(clear);
        red.addActionListener(this);
        green.addActionListener(this);
        blue.addActionListener(this);
        black.addActionListener(this);
        clear.addActionListener(this);
    }
    public Dimension getPreferredSize(){
        return new Dimension(w, h);
    }
    public void actionPerformed(ActionEvent event){
        String s = event.getActionCommand();
        if (s.equals("Clear")) repaint();
        else if (s.equals("Red")) currColor = Color.red;
        else if (s.equals("Green")) currColor = Color.green;
        else if (s.equals("Blue")) currColor = Color.blue;
        else if (s.equals("Black")) currColor = Color.black;
    }
    public void processMouseEvent(MouseEvent e){
        if (e.isPopupTrigger())
        c.show(e.getComponent (), e.getX(), e.getY());
        else if (e.getID() == MouseEvent.MOUSE_PRESSED){
            lastX = e.getX(); lastY = e.getY(); 
        }
        else super.processMouseEvent(e); 
    }
    public void processMouseMotionEvent(MouseEvent e){
        if (e.getID() == MouseEvent.MOUSE_DRAGGED){
            Graphics g = getGraphics();
            g.setColor(currColor) ;
            g.drawLine(lastX, lastY, e.getX(), e.getY());
            lastX = e.getX(); lastY = e.getY();
        }
        else super.processMouseMotionEvent(e);
    }
}
