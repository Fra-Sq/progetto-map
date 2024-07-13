/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.GameObserver;

/**
 * Observer implementation for handling movement commands within the game.
 * This observer updates the player's current room based on the direction specified in the command.
 */
public class MoveObserver implements GameObserver {

    /**
     * Processes movement commands (NORTH, SOUTH, EAST, WEST), updating the player's current room accordingly.
     * If the movement is not possible (e.g., a wall blocks the way), it returns a message indicating so.
     * Otherwise, it silently updates the player's current room to the new location.
     *
     * @param description The current state of the game, including the player's current room.
     * @param parserOutput The parsed output of the player's command, including the command type and arguments.
     * @param window The game window where output messages are displayed.
     * @return A string message indicating the outcome of the movement command, or an empty string if the movement was successful.
     */
    @Override
    public String update(GameDescription description, ParserOutput parserOutput, Window window) {
        if (null != parserOutput.getCommand().getType()) switch (parserOutput.getCommand().getType()) {
            case NORD:
                if (description.getCurrentRoom().getNorth() != null) {
                    description.setCurrentRoom(description.getCurrentRoom().getNorth());
                } else {
                    return "Da quella parte non si può andare c'è un muro!\nNon hai ancora acquisito i poteri per oltrepassare i muri...";
                }   break;
            case SOUTH:
                if (description.getCurrentRoom().getSouth() != null) {
                    description.setCurrentRoom(description.getCurrentRoom().getSouth());
                } else {
                    return "Da quella parte non si può andare c'è un muro!\nNon hai ancora acquisito i poteri per oltrepassare i muri...";
                }   break;
            case EAST:
                if (description.getCurrentRoom().getEast() != null) {
                    description.setCurrentRoom(description.getCurrentRoom().getEast());
                } else {
                    return "Da quella parte non si può andare c'è un muro!\nNon hai ancora acquisito i poteri per oltrepassare i muri...";
                }   break;
            case WEST:
                if (description.getCurrentRoom().getWest() != null) {
                    description.setCurrentRoom(description.getCurrentRoom().getWest());
                } else {
                    return "Da quella parte non si può andare c'è un muro!\nNon hai ancora acquisito i poteri per oltrepassare i muri...";
                }   break;
            default:
                break;
        }
        return "";
    }

}
