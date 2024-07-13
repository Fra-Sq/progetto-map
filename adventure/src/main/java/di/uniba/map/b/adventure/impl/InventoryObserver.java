/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.GameObserver;

/**
 * Implements the observer for inventory-related events in the game.
 * This observer is responsible for handling the display of the player's inventory
 * when the inventory command is issued.
 */
public class InventoryObserver implements GameObserver {

    /**
     * Updates the game state in response to an inventory command and generates a message
     * describing the current contents of the player's inventory.
     *
     * @param description The current state of the game.
     * @param parserOutput The output from the command parser, containing the parsed command.
     * @param window The game window where output messages are displayed.
     * @return A string message detailing the contents of the player's inventory, or a message
     *         indicating the inventory is empty if no items are present.
     */
    @Override
    public String update(GameDescription description, ParserOutput parserOutput, Window window) {
        StringBuilder msg = new StringBuilder();
        if (parserOutput.getCommand().getType() == CommandType.INVENTORY) {
            if (description.getInventory().isEmpty()) {
                msg.append("Il tuo inventario è vuoto!");
            } else {
                msg.append("Nel tuo inventario ci sono:\n");
                for (AdvObject o : description.getInventory()) {
                    msg.append(o.getName()).append(": ").append(o.getDescription()).append("\n");
                }
            }
        }
        return msg.toString();
    }

}
