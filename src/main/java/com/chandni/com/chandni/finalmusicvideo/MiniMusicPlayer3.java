package com.chandni.com.chandni.finalmusicvideo;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

public class MiniMusicPlayer3 {

    private static final JFrame frame = new JFrame("My First Music Video");
    private MyDrawPanel myDrawPanel = new MyDrawPanel();

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final MiniMusicPlayer3 miniMusicPlayer3 = new MiniMusicPlayer3();
        miniMusicPlayer3.go();
    }

    private void go() {
        frame.setContentPane(myDrawPanel);
        frame.setBounds(30, 30, 300, 300);
        frame.setVisible(true);

        try {
            final Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addControllerEventListener(myDrawPanel, new int[]{127});

            final Sequence sequence = new Sequence(Sequence.PPQ, 4);
            final Track track = sequence.createTrack();

            int random = 0;
            for (int i = 5; i < 60; i += 4) {
                random = (int) (Math.random() * 50 + 1);
                track.add(makeEvent(144, 1, random, 100, i));
                track.add(makeEvent(176, 1, 127, 0, i));
                track.add(makeEvent(128, 1, random, 100, i + 2));
            }

            sequencer.setSequence(sequence);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }


    public static MidiEvent makeEvent(int command, int channel, int data1, int data2, long tick) {
        MidiEvent event = null;

        try {
            final ShortMessage shortMessage = new ShortMessage();
            shortMessage.setMessage(command, channel, data1, data2);
            event = new MidiEvent(shortMessage, tick);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

        return event;
    }


    class MyDrawPanel extends JPanel implements ControllerEventListener {
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

}


