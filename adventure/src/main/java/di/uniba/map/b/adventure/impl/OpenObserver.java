package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.CommandType;
import java.util.Iterator;
import di.uniba.map.b.adventure.GameObserver;

public class OpenObserver implements GameObserver {

    @Override
    public String update(GameDescription description, ParserOutput parserOutput, Window window) {
        StringBuilder msg = new StringBuilder();
        if (parserOutput.getCommand().getType() == CommandType.OPEN) {
            if (parserOutput.getObject() == null && parserOutput.getInvObject() == null) {
                msg.append("Non c'è niente da aprire qui.");
            } else {
                if (parserOutput.getObject() != null) {
                    if (parserOutput.getObject().getId() == 9 && !description.isKeyUsed()) {
                        msg.append("La porta è chiusa. Hai bisogno di una chiave per aprirla.");
                    } else if (parserOutput.getObject().isOpenable() && !parserOutput.getObject().isOpen()) {
                        if (parserOutput.getObject() instanceof AdvObjectContainer) {
                            msg.append("Hai aperto: ").append(parserOutput.getObject().getName());
                            AdvObjectContainer c = (AdvObjectContainer) parserOutput.getObject();
                            if (!c.getList().isEmpty()) {
                                msg.append(c.getName()).append(" contiene:");
                                Iterator<AdvObject> it = c.getList().iterator();
                                while (it.hasNext()) {
                                    AdvObject next = it.next();
                                    description.getCurrentRoom().getObjects().add(next);
                                    msg.append(" ").append(next.getName());
                                    it.remove();
                                }
                                msg.append("\n");
                            }
                            parserOutput.getObject().setOpen(true);
                        } else {
                            msg.append("Hai aperto: ").append(parserOutput.getObject().getName());
                            parserOutput.getObject().setOpen(true);
                        }
                    } else {
                        msg.append("Non puoi aprire questo oggetto.");
                    }
                }
                if (parserOutput.getInvObject() != null) {
                    if (parserOutput.getInvObject().isOpenable() && !parserOutput.getInvObject().isOpen()) {
                        if (parserOutput.getInvObject() instanceof AdvObjectContainer) {
                            AdvObjectContainer c = (AdvObjectContainer) parserOutput.getInvObject();
                            if (!c.getList().isEmpty()) {
                                msg.append(c.getName()).append(" contiene:");
                                Iterator<AdvObject> it = c.getList().iterator();
                                while (it.hasNext()) {
                                    AdvObject next = it.next();
                                    description.getInventory().add(next);
                                    msg.append(" ").append(next.getName());
                                    it.remove();
                                }
                                msg.append("\n");
                            }
                            parserOutput.getInvObject().setOpen(true);
                        } else {
                            parserOutput.getInvObject().setOpen(true);
                        }
                        msg.append("Hai aperto nel tuo inventario: ").append(parserOutput.getInvObject().getName());
                    } else {
                        msg.append("Non puoi aprire questo oggetto.");
                    }
                }
            }
        }
        return msg.toString();
    }
}
