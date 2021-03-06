package com.chandni.musicvideo;

import javax.sound.midi.*;

public class MiniMusicPlayer1 {
    public static void main(String[] args) throws InvalidMidiDataException {

        try {
            final Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            final Sequence sequence = new Sequence(Sequence.PPQ, 4);
            final Track track = sequence.createTrack();

            for (int i = 5; i < 61; i+= 4) {
                track.add(makeEvent(144, 1, i, 100, i));
                track.add(makeEvent(128, 1, i, 100, i + 2));
            }

            sequencer.setSequence(sequence);
            sequencer.setTempoInBPM(220);
            sequencer.start();
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
