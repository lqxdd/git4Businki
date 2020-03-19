/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuscribble;

/**
 *
 * @author Viktoriia_Mironova
 */
import java.awt.*;
import java.awt.event.*;

public class MenuScribble extends Frame{
    public MenuScribble(String s) { super(s);
        setSize(500,500);
        setVisible(true);
        ScrollPane pane = new ScrollPane();
        pane.setSize(300, 300);
        add(pane, BorderLayout.CENTER);
        Scribble scr = new Scribble(this, 500, 500);
        pane.add(scr);
        MenuBar mb = new MenuBar();
        setMenuBar(mb);
        Menu f = new Menu("File");
        Menu v = new Menu("Draw");
        mb.add(f); mb.add(v);
        MenuItem open = new MenuItem("Open...", new MenuShortcut(KeyEvent.VK_0));
        MenuItem save = new MenuItem("Save", new MenuShortcut(KeyEvent.VK_S));
        MenuItem saveAs = new MenuItem("Save As...");
        MenuItem exit = new MenuItem("Exit", new MenuShortcut(KeyEvent.VK_Q));
        f.add(open); f.add(save); f.add(saveAs);
        f.addSeparator(); f.add(exit);
         open.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                FileDialog fd = new FileDialog(new Frame(),
                " Open File", FileDialog.LOAD);
                fd.setVisible(true);
            }
        });
        saveAs.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                FileDialog fd = new FileDialog(new Frame(),
                " Save File As", FileDialog.SAVE);
                fd.setVisible(true);
            }
        });
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent ev){
                System.exit (0);
            }
        });

        Menu c = new Menu("Color");
        v.add(c);
        MenuItem clear = new MenuItem("Clear",new MenuShortcut(KeyEvent.VK_D));
        v.add(clear);
        MenuItem red = new MenuItem("Red");
        MenuItem green = new MenuItem("Green");
        MenuItem blue = new MenuItem("Blue");
        MenuItem black = new MenuItem("Black");
        c.add(red); c.add(green); c.add(blue); c.add(black);

        red.addActionListener(scr);
        green.addActionListener(scr);
        blue.addActionListener(scr);
        black.addActionListener(scr);
        clear.addActionListener(scr);
}

    public static void main(String[] args){
        Frame fr = new MenuScribble (" \"Рисувалка\" з меню");
    }
}
class Scribble extends Component implements ActionListener, MouseListener, MouseMotionListener{
    protected int lastX, lastY, w, h;
    protected Color currColor = Color.black;
    protected Frame f;
    public Scribble(Frame frame, int width, int height){
        f = frame;
        w = width;
        h = height;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
        addMouseListener(this);
        addMouseMotionListener(this); 
    }
    public Dimension getPreferredSize(){
        return new Dimension(w, h); 
    }
    public void actionPerformed(ActionEvent event){
        String s = event.getActionCommand();
        if (s.equals ("Clear")) repaint();
        else if (s.equals ("Red")) currColor = Color.red;
        else if (s.equals("Green")) currColor = Color.green;
        else if (s.equals("Blue")) currColor = Color.blue;
        else if (s.equals("Black")) currColor = Color.black; 
    }
    public void mousePressed(MouseEvent e){
        if ( (e.getModifiers() & MouseEvent.BUTTON1_MASK) == 0) return;
        lastX = e.getX(); lastY = e.getY(); 
    }
    public void mouseDragged(MouseEvent e){
        if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) == 0) return;
        Graphics g = getGraphics();
        g.setColor(currColor);
        g.drawLine(lastX, lastY, e.getX(), e.getY());
        lastX = e.getX(); lastY = e.getY(); 
    }
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
}
