/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveGame {

    public static void save(GameDescription game, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(game);
            System.out.println("Game saved in " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

