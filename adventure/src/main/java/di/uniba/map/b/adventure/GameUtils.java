/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.type.AdvObject;
import java.util.List;

/**
 * Utility class containing helper methods for game operations.
 */
public class GameUtils {

    /**
     * Searches for an object in the player's inventory by its ID.
     *
     * @param inventory The list of {@link AdvObject} representing the player's inventory.
     * @param id The unique identifier of the object to search for.
     * @return The {@link AdvObject} if found in the inventory; otherwise, returns {@code null}.
     */
    public static AdvObject getObjectFromInventory(List<AdvObject> inventory, int id) {
        for (AdvObject o : inventory) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }

}
