/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.GameObserver;

/**
 *
 * @author pierpaolo
 */
public class MoveObserver implements GameObserver {

    /**
     *
     * @param description
     * @param parserOutput
     * @param window
     * @return
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
