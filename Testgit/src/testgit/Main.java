package com.lab6;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Main extends JFrame implements ActionListener {
    private JRadioButton draw_circle;
    private JRadioButton draw_square;
    private JRadioButton draw_polygon;
    JPanel globalPanel = new JPanel();
    JPanel draw;
    Main(String s) {
        super(s);
        drawBorders();
        setSize(400, 400);
        radioButtons();
        setVisible(true);
        //setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void drawBorders(){
        Font title_font = new Font("Serif", Font.BOLD, 18);
        Label title_of_border = new Label("Drawing area");
        title_of_border.setBounds(140, 30, 120, 20);
        title_of_border.setFont(title_font);
        add(title_of_border);
        Label top_border = new Label();
        top_border.setBackground(Color.WHITE);
        top_border.setBounds(60,50, 280, 10);
        add(top_border);
        Label bottom_border = new Label();
        bottom_border.setBackground(Color.WHITE);
        bottom_border.setBounds(60,300, 280, 11);
        add(bottom_border);
        Label left_border = new Label();
        left_border.setBackground(Color.WHITE);
        left_border.setBounds(60,50, 10, 260);
        add(left_border);
        Label right_border = new Label();
        right_border.setBackground(Color.WHITE);
        right_border.setBounds(330,50, 10, 260);
        add(right_border);
    }
    private void radioButtons(){
        draw_circle = new JRadioButton("Draw circle");
        draw_circle.addActionListener(this);
        draw_square = new JRadioButton("Draw square");
        draw_square.addActionListener(this);
        draw_polygon = new JRadioButton("Draw polygon");
        draw_polygon.addActionListener(this);
        //  BUTTON GROUP
        ButtonGroup radio_buttons = new ButtonGroup();
        radio_buttons.add(draw_circle);
        radio_buttons.add(draw_square);
        radio_buttons.add(draw_polygon);
        globalPanel.add(draw_circle);
        globalPanel.add(draw_square);
        globalPanel.add(draw_polygon);
        add(globalPanel);
        repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.draw != null){
        globalPanel.remove(this.draw);}
        if(draw_circle.isSelected()) {
            this.draw = new DrawPanel(0);
            globalPanel.add(this.draw);
            globalPanel.repaint();
        }
        if(draw_square.isSelected()){
            this.draw = new DrawPanel(1);
            globalPanel.add(this.draw);
            globalPanel.repaint();
        }
        if(draw_polygon.isSelected()){
            this.draw = new DrawPanel(2);
            globalPanel.add(this.draw);
            globalPanel.repaint();
        }
    }
    public static void main(String[] args){
        new Main("Choose shape you want to draw");
    }
}
class DrawPanel extends JPanel {
    private int num;
    DrawPanel(int num){
        this.num = num;
        add(new Button("ok"));
        setBounds(100,100, 400,400);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(this.num == 0) {
            g.setColor(Color.BLUE);
            g.fillOval(40, 40, 100, 100);
        }
        if(this.num == 1){
            g.setColor(Color.red);
            g.fillRect(40, 30, 100, 100);
        }
        if(this.num == 2){
            g.setColor(Color.GREEN);
            int xPoly[] = {0, 100, 200, 150, 80, 20};
            int yPoly[] = {0, 0, 20, 120, 120, 20};
            g.fillPolygon(xPoly,yPoly,6);
        }
    }
}
