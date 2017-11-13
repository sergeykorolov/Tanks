package com.torrens.display;

import com.torrens.IO.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Display {

    private static boolean created = false;  // check created window or not
    private static JFrame window;            // frame of game window
    private static Canvas content;           // canvas for drawing

    private static BufferedImage buffer;
    private static int[] bufferData;
    private static Graphics bufferGraphics;
    private static int clearColor;

    public static BufferStrategy bufferStrategy;

    // create window
    public static void create(int width, int height, String title, int clear_Color, int numBuffers) {

        if (created) return;  // if the window is already created, exit the method

        window = new JFrame(title);                             // create a window frame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = new Canvas();                                 // create a canvas

        Dimension size = new Dimension(width, height); // size of canvas
        content.setPreferredSize(size);                // set the canvas size

        window.setResizable(false);                    // forbid user to change size of a window
        window.getContentPane().add(content);          // добавляем контент в окно
        window.pack();                                 // устанавливаем размер контейнера
        window.setLocationRelativeTo(null);
        window.setVisible(true);                       // Делает окно видимым

        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        // включаем сглаживание на объект bufferGraphics
        ((Graphics2D)bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = clear_Color;

        content.createBufferStrategy(numBuffers);
        bufferStrategy = content.getBufferStrategy();

        created = true;
    }

    public static void clear() {
        Arrays.fill(bufferData, clearColor);
    }

    public static void swapBuffers() {
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer, 0, 0, null);
        bufferStrategy.show();
    }

    public static Graphics2D getGraphics(){
        return (Graphics2D)bufferGraphics;
    }

    // уничтожает окно
    public static void destroy(){
        if(!created)
            return;

        window.dispose();
    }

    public static void setTitle(String title){

        window.setTitle(title);
    }

    public static void addInputListener(Input inputListener){
        window.add(inputListener);
    }
}
