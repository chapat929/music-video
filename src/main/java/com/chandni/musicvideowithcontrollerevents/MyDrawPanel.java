package com.chandni.musicvideowithcontrollerevents;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.*;
import java.awt.*;

public class MyDrawPanel extends JPanel implements ControllerEventListener {
    boolean message = false;

    @Override
    public void controlChange(ShortMessage event) {
        message = true;
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        if (message) {

            final Graphics2D graphics2D = (Graphics2D) graphics;

            int red = (int) (Math.random() * 250);
            int green = (int) (Math.random() * 250);
            int blue = (int) (Math.random() * 250);

            graphics.setColor(new Color(red, green, blue));

            int height = (int) ((Math.random() * 120) + 10);
            int width = (int) ((Math.random() * 120) + 10);
            int x = (int) ((Math.random() * 40) + 10);
            int y = (int) ((Math.random() * 40) + 10);
            graphics.fillRect(x, y, width, height);

            message = false;
        }
    }
}
