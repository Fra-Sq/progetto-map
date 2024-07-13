/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;
import java.io.File; 
import java.io.IOException; 
import javax.sound.sampled.AudioFormat; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.DataLine; 
import javax.sound.sampled.FloatControl; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.SourceDataLine; 
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This class is designed to play sound files in a separate thread. It supports basic operations such as play, stop, and handling of audio pan (left, right, normal).
 */
public class AePlayWave extends Thread {

    private final String filename;
    private final Position curPosition;
    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb

    private volatile boolean isInterrupted = false; // Flag per gestire l'interruzione
    private volatile boolean isPlaying = false;     // Flag per indicare se il suono è in riproduzione

    enum Position {
        LEFT, RIGHT, NORMAL
    };

    public AePlayWave(String wavfile) {
        this.filename = wavfile;
        this.curPosition = Position.NORMAL;
    }

    // Metodo per interrompere la riproduzione del suono
    public void stopSound() {
        isInterrupted = true;
        interrupt(); // Interruppiamo il thread
    }

    // Metodo per avviare o riprendere la riproduzione del suono
    public synchronized void startSound() {
        if (!isPlaying) {
            isInterrupted = false;
            start(); // Avvia il thread se non è già in esecuzione
        }
    }

    @Override
    public void run() {
        isPlaying = true;
        while (!isInterrupted) {
            File soundFile = new File(filename);
            if (!soundFile.exists()) {
                System.err.println("Wave file not found: " + filename);
                return;
            }

            AudioInputStream audioInputStream;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            } catch (UnsupportedAudioFileException | IOException e1) {
                return;
            }

            AudioFormat format = audioInputStream.getFormat();
            SourceDataLine auline;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            try {
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
            } catch (LineUnavailableException e) {
                return;
            } catch (Exception e) {
                return;
            }

            if (auline.isControlSupported(FloatControl.Type.PAN)) {
                FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
                if (curPosition == Position.RIGHT)
                    pan.setValue(1.0f);
                else if (curPosition == Position.LEFT)
                    pan.setValue(-1.0f);
            }

            auline.start();
            int nBytesRead = 0;
            byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

            try {
                while (nBytesRead != -1 && !isInterrupted) { // Verifica se il thread è stato interrotto
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0)
                        auline.write(abData, 0, nBytesRead);
                }
            } catch (IOException e) {
            } finally {
                auline.drain();
                auline.close();
            }

            // Chiudi l'input stream audio
            try {
                audioInputStream.close();
            } catch (IOException e) {
            }
        }
        isPlaying = false;
    }
}
