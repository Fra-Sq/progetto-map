/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.GameObserver;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;

/**
 * Observer implementation for handling "read" commands within the game.
 * This observer allows players to read objects that contain readable content, such as maps or notes.
 */
public class ReadObserver implements GameObserver{
    /**
     * Processes a "read" command, displaying the content of the readable object to the player.
     * It checks if the object is in the player's inventory or in the current room and if it is readable.
     * If the object is a map, it provides additional handling.
     *
     * @param description The current state of the game, including the player's current room and inventory.
     * @param parserOutput The parsed output of the player's command, including the command type and targeted object.
     * @param window The game window where output messages are displayed.
     * @return A string message indicating the outcome of the "read" command, such as the content of the object or an error message.
     */

    @Override
    public String update(GameDescription description, ParserOutput parserOutput, Window window) {
        StringBuilder msg = new StringBuilder();

        if (parserOutput.getCommand().getType() == CommandType.READ) {
            boolean interact = false;
            boolean map = parserOutput.getInvObject() != null && parserOutput.getInvObject().getId() == 11;
            map = map || parserOutput.getObject() != null && parserOutput.getObject().getId() == 11;
            if (map) {
                if (parserOutput.getObject() != null && parserOutput.getObject().getId() == 11){
                    msg.append("Devi prima raccoglierlo per poterlo leggere.");
                    interact = true;
                }
                if (parserOutput.getInvObject() != null && parserOutput.getInvObject().getId() == 11) {
                    if (parserOutput.getInvObject().isReadable()) {
                        msg.append("Mappa: ").append(parserOutput.getInvObject().getContents());
                        msg.append("Non sembra molto utile, ma potrebbe tornarti utile in futuro.");
                    }
                    interact = true;
                }

            }

            if (!interact) {
                msg.append("Non c'è niente da leggere qui.");
            }
        }
        return msg.toString();
    }
}
