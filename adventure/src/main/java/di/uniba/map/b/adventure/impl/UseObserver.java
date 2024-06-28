package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.GameObserver;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.Room;

public class UseObserver implements GameObserver {

    @Override
    public String update(GameDescription description, ParserOutput parserOutput, Window window) {
        StringBuilder msg = new StringBuilder();
        if (parserOutput.getCommand().getType() == CommandType.USE) {
            AdvObject usedObject = parserOutput.getInvObject();
            if (usedObject == null) {
                msg.append("Non hai specificato un oggetto da usare.");
                return msg.toString();
            }

            Room currentRoom = description.getCurrentRoom();
            boolean interact = false;

            switch (usedObject.getId()) {
                case 8: // ID della spada
                    if (currentRoom.getName().equalsIgnoreCase("Anticamera")) {
                        if (currentRoom.isMonsterAlive()) {
                            AdvObject alieno = currentRoom.getObjectByName("alieno");
                            if (alieno != null) {
                                msg.append("Hai colpito l'alieno con la tua spada! L'alieno si sveglia furioso e ti attacca!");
                                // L'alieno ora è sveglio e attacca
                                currentRoom.setDynamicLook("Sei nell'anticamera, l'alieno gigante è sveglio e ti attacca furiosamente!\n");
                            } else {
                                msg.append("Non c'è nessun alieno qui da attaccare.");
                            }
                        } else {
                            msg.append("Hai già ucciso l'alieno qui.");
                        }
                    } else {
                        msg.append("Non puoi usare la spada qui.");
                    }
                    interact = true;
                    break;
                
                case 5: // ID della chiave
                {
                    // ID della chiave
                    msg.append("Chiave trovata nell'inventario. ");
                    AdvObject doorObject = currentRoom.getObject(9); // Supponiamo che 9 sia l'ID della porta
                    if (doorObject != null && doorObject instanceof AdvObjectContainer) {
                        AdvObjectContainer door = (AdvObjectContainer) doorObject;
                        if (door.isOpenable()) {
                            if (door.isOpen()) {
                                msg.append("La porta è già aperta.");
                            } else {
                                msg.append("Hai usato la chiave per aprire la porta.");
                                door.setOpen(true); // Apri la porta
                                description.setKeyUsed(true); // Imposta il flag a true
                            }
                        } else {
                            msg.append("Non riesci ad aprire la porta con questa chiave.");
                        }
                    } else {
                        msg.append("Porta non trovata nella stanza.");
                    }       interact = true;
                    break;
                }

            }

            if (!interact) {
            msg.append("Non ci sono oggetti utilizzabili qui.");
        }
    } else if (parserOutput.getCommand().getType() == CommandType.OPEN) {
        Room currentRoom = description.getCurrentRoom();
        AdvObject doorObject = currentRoom.getObject(9); // Supponiamo che 9 sia l'ID della porta
        if (doorObject != null && doorObject instanceof AdvObjectContainer) {
            AdvObjectContainer door = (AdvObjectContainer) doorObject;
            if (door.isOpenable()) {
                if (door.isOpen()) {
                    msg.append("La porta è già aperta.");
                } else {
                    if (description.isKeyUsed()) {
                        door.setOpen(true); // Apri la porta
                        msg.append("Hai aperto la porta.");
                    } else {
                        msg.append("La porta è chiusa. Usa la chiave per aprirla.");
                    }
                }
            } 
        }        
    }

    return msg.toString();
}


      
}


