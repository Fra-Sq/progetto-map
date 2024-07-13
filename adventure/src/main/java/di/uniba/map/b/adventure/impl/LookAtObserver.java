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
 * Observer implementation for handling "look at" commands within the game.
 * This observer is responsible for providing descriptions of the current room
 * or objects within it when the player uses the "look at" command.
 */
public class LookAtObserver implements GameObserver {

    /**
     * Processes a "look at" command, returning a description of the current room or an object within it.
     * If the current room has a special description, it is returned; otherwise, a generic message is provided.
     *
     * @param description The current state of the game, including the player's current room.
     * @param parserOutput The parsed output of the player's command, including the command type and arguments.
     * @param window The game window where output messages are displayed.
     * @return A string message containing the description of the current room or a generic message if the room has no special description.
     */
    @Override
    public String update(GameDescription description, ParserOutput parserOutput, Window window) {
        StringBuilder msg = new StringBuilder();
        if (parserOutput.getCommand().getType() == CommandType.LOOK_AT) {
            if (description.getCurrentRoom().getLook() != null) {
                msg.append(description.getCurrentRoom().getLook());
            } else {
                msg.append("Non c'è niente di interessante qui.");
            }
        }
        return msg.toString();
    }

}
