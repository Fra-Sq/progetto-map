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
 *
 * @author sangiovannesi
 */
public class OpenObserver implements GameObserver {

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
