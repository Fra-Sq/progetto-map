/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.CommandType;
import java.util.Iterator;
import di.uniba.map.b.adventure.GameObserver;

/**
 * Observer implementation for handling "open" commands within the game.
 * This observer allows players to open objects or containers in the game environment,
 * potentially revealing or releasing items contained within.
 */
public class OpenObserver implements GameObserver {

    /**
     * Processes an "open" command, attempting to open the specified object or container.
     * If the object is openable and not already open, it is opened, and any contained items are moved to the current room.
     * Messages are generated to inform the player of the action's outcome.
     *
     * @param description The current state of the game, including the player's current room and inventory.
     * @param parserOutput The parsed output of the player's command, including the command type and targeted object.
     * @param window The game window where output messages are displayed.
     * @return A string message indicating the outcome of the "open" command.
     */
    @Override
    public String update(GameDescription description, ParserOutput parserOutput, Window window) {
        StringBuilder msg = new StringBuilder();
        if (parserOutput.getCommand().getType() == CommandType.OPEN) {
            AdvObject object = parserOutput.getObject();
            AdvObject invObject = parserOutput.getInvObject();

            if (object == null && invObject == null) {
                msg.append("Non c'è niente da aprire qui.");
            } else {
                if (object != null) {
                    openObject(msg, description, object);
                }
                if (invObject != null) {
                    openObject(msg, description, invObject);
                }
            }
        }
        return msg.toString();
    }

    /**
     * Attempts to open a specified object or container. If the object is an instance of AdvObjectContainer
     * and contains items, those items are moved to the current room. Messages are generated to inform the player
     * of the action's outcome.
     *
     * @param msg The StringBuilder object used to build the outcome message.
     * @param description The current state of the game, including the player's current room.
     * @param object The object or container to be opened.
     */
    private void openObject(StringBuilder msg, GameDescription description, AdvObject object) {
        if (object.isOpenable() && !object.isOpen()) {
            if (object instanceof AdvObjectContainer) {
                AdvObjectContainer container = (AdvObjectContainer) object;
                msg.append("Hai aperto: ").append(container.getName());
                if (!container.getList().isEmpty()) {
                    msg.append(" contiene:");
                    Iterator<AdvObject> it = container.getList().iterator();
                    while (it.hasNext()) {
                        AdvObject next = it.next();
                        description.getCurrentRoom().getObjects().add(next);
                        msg.append(" ").append(next.getName());
                        it.remove();
                    }
                    msg.append("\n");
                }
                container.setOpen(true);
            } else {
                msg.append("Hai aperto: ").append(object.getName());
                object.setOpen(true);
            }
        } else {
            msg.append("Non puoi aprire questo oggetto.");
        }
    }
}
