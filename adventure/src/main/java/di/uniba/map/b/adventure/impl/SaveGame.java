/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGame {

    public static void save(GameDescription game, String fileName, int elapsedSeconds) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("ElapsedSeconds:" + elapsedSeconds + "\n");
            writer.write("CurrentRoom:" + game.getCurrentRoom().getName() + "\n");
            writer.write("Inventory:" + game.getInventory().toString() + "\n");
            //writer.write("History:" + game.getHistory().toString() + "\n"); // Assumi che GameDescription abbia un metodo getHistory()
            //System.out.println("Game saved in " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



