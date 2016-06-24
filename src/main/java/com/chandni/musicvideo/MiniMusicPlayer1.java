package com.chandni.musicvideo;

import javax.sound.midi.*;

public class MiniMusicPlayer1 {
    public static void main(String[] args) {

        try {
            MidiSystem.getSequencer();
        } catch (MidiUnavailableException e) {
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
}
