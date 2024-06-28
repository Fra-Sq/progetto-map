package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.GameObserver;
import di.uniba.map.b.adventure.GameUtils;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;

/**
 *
 * @author sangiovannesi
 */

public class ReadObserver implements GameObserver{
    /**
     *
     * @param description
     * @param parserOutput
     * @param window
     * @return
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
                        msg.append("Mappa: " + parserOutput.getInvObject().getContents());
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
