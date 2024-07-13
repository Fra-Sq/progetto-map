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

public class SaveGame {

    private static final String SAVE_FILE = "./resources/files/gamesSaved";

    /**
     *
     * @param currentRoom
     * @param inventory
     * @param gameName
     * @param elapsedSeconds
     * @param monsterAlive
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
