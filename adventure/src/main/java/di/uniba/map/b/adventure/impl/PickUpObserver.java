/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.GameObserver;

/**
 * Observer implementation for handling "pick up" commands within the game.
 * This observer allows players to pick up objects from the current room and add them to their inventory,
 * provided the objects are pickupable.
 */
public class PickUpObserver implements GameObserver {

    /**
     * Processes a "pick up" command, attempting to add the specified object to the player's inventory.
     * Verifies if the object is present in the current room and if it is pickupable.
     * If successful, the object is removed from the room and added to the inventory, and a success message is returned.
     * Otherwise, an appropriate failure message is generated.
     *
     * @param description The current state of the game, including the player's current room and inventory.
     * @param parserOutput The parsed output of the player's command, including the command type and targeted object.
     * @param window The game window where output messages are displayed.
     * @return A string message indicating the outcome of the "pick up" command.
     */
    @Override
    public String update(GameDescription description, ParserOutput parserOutput, Window window) {
        StringBuilder msg = new StringBuilder();
        if (parserOutput.getCommand().getType() == CommandType.PICK_UP) {
            if (parserOutput.getObject() != null) {
                if (description.getCurrentRoom().getObjects().contains(parserOutput.getObject())) {
                    if (parserOutput.getObject().isPickupable()) {
                        description.getInventory().add(parserOutput.getObject());
                        description.getCurrentRoom().getObjects().remove(parserOutput.getObject());
                        msg.append("Hai raccolto: ").append(parserOutput.getObject().getDescription());
                        if (description.getCurrentRoom().getId() == 3) {
                            description.getCurrentRoom().setLook("Non c'è nulla di interessante qui.");
                        }
                    } else {
                        msg.append("Non puoi raccogliere questo oggetto.");
                    }
                } else {
                    msg.append("Non c'è niente da raccogliere qui.");
                }
            } else {
                msg.append("Non c'è niente da raccogliere qui.");
            }
        }
        return msg.toString();
    }


}
