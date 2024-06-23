package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.GameObserver;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;

public class UseObserver implements GameObserver {

    @Override
    public String update(GameDescription description, ParserOutput parserOutput) {
        StringBuilder msg = new StringBuilder();
        if (parserOutput.getCommand().getType() == CommandType.USE) {
            AdvObject usedObject = parserOutput.getInvObject();
            if (usedObject == null) {
                msg.append("Non hai specificato un oggetto da usare.");
                return msg.toString();
            }

            boolean interact = false;

            // Gestione dell'uso della chiave
            if (usedObject.getId() == 5) { // ID della chiave
                msg.append("Chiave trovata nell'inventario. ");
                AdvObject doorObject = description.getCurrentRoom().getObject(9); // Supponiamo che 9 sia l'ID della porta
                if (doorObject != null && doorObject instanceof AdvObjectContainer) {
                    AdvObjectContainer door = (AdvObjectContainer) doorObject;
                    if (door.isOpenable()) {
                        if (door.isOpen()) {
                            msg.append("La porta è già aperta.");
                        } else {
                            msg.append("Hai usato la chiave per aprire la porta. Ora puoi aprirla.");
                            description.setKeyUsed(true); // Imposta il flag a true
                        }
                    } else {
                        msg.append("Non riesci ad aprire la porta con questa chiave.");
                    }
                } else {
                    msg.append("Porta non trovata nella stanza.");
                }
                interact = true;
            } else {
                // Gestione di altri oggetti
                switch (usedObject.getId()) {
                    case 6: // ID della gemma
                        msg.append("Hai usato la gemma. ");
                        interact = true;
                        break;
                    case 8: // ID della spada
                        msg.append("Hai usato la spada. ");
                        interact = true;
                        break;
                    default:
                        msg.append("Non puoi usare questo oggetto qui.");
                }
            }

            if (!interact) {
                msg.append("Non ci sono oggetti utilizzabili qui.");
            }
        }
        return msg.toString();
    }
}
