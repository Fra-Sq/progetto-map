/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

import java.util.List;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Room;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides functionality to save and load game states to and from a file.
 * This class includes methods to save the current game state, check if a game save exists, and load a game state from a file.
 */
public class SaveGame {

    private static final String SAVE_FILE = "./resources/files/gamesSaved";

    /**
     * Saves the current game state to a file. This includes the current room, player inventory, game name, elapsed time,
     * monster alive status, and door open status. If a game with the same name already exists, the save is aborted.
     *
     * @param currentRoom The current room the player is in.
     * @param inventory The current inventory of the player.
     * @param gameName The name of the game save.
     * @param elapsedSeconds The elapsed time in seconds since the game started.
     * @param monsterAlive The alive status of the monster.
     * @param isDoorOpen The open status of the door.
     */
    public static void save(Room currentRoom, List<AdvObject> inventory, String gameName, int elapsedSeconds, boolean monsterAlive, boolean isDoorOpen) {
        int currentRoomId = currentRoom.getId();
        // Check if a game with the same name already exists
        if (gameExists(gameName)) {
            System.out.println("Una partita con questo nome esiste già. Salvataggio annullato.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE, true))) {
            writer.write("GameName:" + gameName + "\n");
            writer.write("ElapsedSeconds:" + elapsedSeconds + "\n");
            writer.write("CurrentRoom:" + currentRoomId + "\n");
            writer.write("MonsterAlive:" + monsterAlive + "\n");
            writer.write("IsDoorOpen:" + isDoorOpen + "\n");
            writer.write("Inventory:");
            for (AdvObject obj : inventory) {
                writer.write(obj.getId() + ",");
            }
            writer.write("\n");
            writer.write("---\n"); // delimiter between different game saves
        } catch (IOException e) {
        }
    }

    /**
     * Checks if a game save with the specified name already exists in the file.
     *
     * @param gameName The name of the game to check.
     * @return true if the game exists, false otherwise.
     */
    public static boolean gameExists(String gameName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("GameName:") && line.substring(9).trim().equals(gameName)) {
                    return true;
                }
            }
        } catch (IOException e) {
        }
        return false;
    }

    /**
     * Loads the game state from a file based on the specified game name. This method reads the file and constructs
     * a map containing the game state, including elapsed seconds, current room, monster alive status, door open status,
     * and player inventory.
     *
     * @param gameName The name of the game to load.
     * @return A map containing the loaded game state.
     */
    public static Map<String, Object> load(String gameName) {
        Map<String, Object> gameData = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String line;
            boolean isGameFound = false;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("GameName:")) {
                    isGameFound = line.substring(9).trim().equals(gameName);
                }

                if (isGameFound) {
                    if (line.startsWith("ElapsedSeconds:")) {
                        gameData.put("ElapsedSeconds", Integer.valueOf(line.substring(15).trim()));
                    } else if (line.startsWith("CurrentRoom:")) {
                        gameData.put("CurrentRoom", Integer.valueOf(line.substring(12).trim()));
                    } else if (line.startsWith("MonsterAlive:")) {
                        gameData.put("MonsterAlive", Boolean.valueOf(line.substring(13).trim()));
                    } else if (line.startsWith("IsDoorOpen:")) {
                        gameData.put("IsDoorOpen", Boolean.valueOf(line.substring(11).trim()));
                    } else if (line.startsWith("Inventory:")) {
                        String[] inventoryIds = line.substring(10).trim().split(",");
                        List<Integer> inventory = new ArrayList<>();
                        for (String id : inventoryIds) {
                            if (!id.isEmpty()) {
                                inventory.add(Integer.valueOf(id.trim()));
                            }
                        }
                        gameData.put("Inventory", inventory);
                    } else if (line.equals("---")) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
        }
        return gameData;
    }

}
