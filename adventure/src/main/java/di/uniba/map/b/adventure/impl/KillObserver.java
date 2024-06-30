/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;


import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.GameObserver;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Room;
/**
 *
 * @author franc
 */

public class KillObserver implements GameObserver {

    @Override
    public String update(GameDescription description, ParserOutput parserOutput, Window window) {
        StringBuilder msg = new StringBuilder();
        if (parserOutput.getCommand().getType() == CommandType.KILL) {
            Room currentRoom = description.getCurrentRoom();
            if (currentRoom.getName().equalsIgnoreCase("Anticamera")) {
                if (currentRoom.isMonsterAlive()) {
                    boolean hasWeapon = description.getInventory().stream().anyMatch(obj -> obj.getId() == 8); // ID della spada
                    if (hasWeapon) {
                        AdvObject alieno = currentRoom.getObjectByName("alieno");
                        if (alieno != null) {
                            msg.append("Hai ucciso l'alieno con la tua spada!");
                            currentRoom.removeObject(alieno);
                            currentRoom.setMonsterAlive(false); // Imposta il mostro come morto
                            currentRoom.setLook("Ti trovi nell'anticamera, ma il mostro è morto. \nSul tentacolo del mostro c'è un codice tatuato: 07738");
                        } else {
                            msg.append("Non c'è nessun alieno qui da attaccare.");
                        }
                    } else {
                        msg.append("Non hai nessuna arma per uccidere l'alieno! Devi trovare una spada.");
                        // Puoi decidere cosa fare in questo caso, per esempio mostrare un messaggio diverso
                    }
                } else {
                    msg.append("Hai già ucciso l'alieno qui.");
                }
            } else {
                msg.append("Non c'è nessun mostro qui da uccidere.");
            }
        }
        return msg.toString();
    }
}

