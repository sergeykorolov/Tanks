package com.torrens.main;

import com.torrens.display.Display;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main {

    public static void main(String[] args) {
        Display display = new Display();
        display.create(800,600,"Tanks");

        Timer t = new Timer(1000 / 400, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.render();
            }
        });

        t.setRepeats(true);
        t.start();
    }
}
