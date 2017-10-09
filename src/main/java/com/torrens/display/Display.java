package com.torrens.display;

import javax.swing.*;
import java.awt.*;

public class Display {

    private boolean created = false;  // check created window or not
    private JFrame window;            // frame of game window
    private Canvas content;           // canvas for drawing

    // create window
    public void create(int width, int height, String title) {

        if (created) return;  // if the window is already created, exit the method

        window = new JFrame(title);                             // create a window frame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = new Canvas(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                render(g);
            }
        };                                 // create a canvas

        Dimension size = new Dimension(width, height); // size of canvas
        content.setPreferredSize(size);                // set the canvas size
        content.setBackground(Color.black);

        window.setResizable(false);                    // forbid user to change size of a window
        window.getContentPane().add(content);          // добавляем контент в окно
        window.pack();                                 // устанавливаем размер контейнера
        window.setLocationRelativeTo(null);
        window.setVisible(true);                       // Делает окно видимым
    }

    public void render(){
        content.repaint();
    }

    private void render(Graphics g){
        g.setColor(Color.white);
        g.fillOval(400-50,300-50,100,100);
    }
}
