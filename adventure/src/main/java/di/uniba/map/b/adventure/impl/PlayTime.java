/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

/**
 *
 * @author franc
 */
public class PlayTime {
    
    private static volatile boolean gameRunning = false;
    private static Thread gameTimerThread;

    // Metodo per avviare il gioco
    public static void startGame() {
        gameRunning = true;

        // Crea un'istanza del thread per contare il tempo di gioco
        gameTimerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Misura il tempo di inizio
                long startTime = System.currentTimeMillis();
                while (gameRunning) {
                    // Mantiene il thread in esecuzione finché il gioco è in corso
                    try {
                        Thread.sleep(100); // Riduce l'uso della CPU
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Misura il tempo di fine
                long endTime = System.currentTimeMillis();

                // Calcola il tempo di esecuzione
                long executionTime = endTime - startTime;

                // Formatta il tempo di esecuzione in hh:mm:ss
                String formattedTime = formatTime(executionTime);

                // Stampa il tempo di esecuzione
                System.out.println("Tempo di gioco: " + formattedTime);
            }
        });

        gameTimerThread.start();
    }

    // Metodo per terminare il gioco
    public static void endGame() {
        gameRunning = false;
        try {
            gameTimerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Metodo per formattare il tempo in hh:mm:ss
    private static String formatTime(long milliseconds) {
        long hours = milliseconds / 3600000;
        long minutes = (milliseconds % 3600000) / 60000;
        long seconds = (milliseconds % 60000) / 1000;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
