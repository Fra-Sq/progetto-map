/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.GameObserver;
import di.uniba.map.b.adventure.GameUtils;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

/**
 *
 * @author pierpaolo
 */
public class PushObserver implements GameObserver {

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
        if (parserOutput.getCommand().getType() == CommandType.PUSH) {
            //ricerca oggetti pushabili
            if (parserOutput.getObject() != null && parserOutput.getObject().isPushable()) {
                msg.append("Hai premuto: ").append(parserOutput.getObject().getName()).append("\n");
                if (parserOutput.getObject().getId() == 1 && GameUtils.getObjectFromInventory(description.getInventory(), 6) != null) {
                    msg.append("Hai attivato il pannello di controllo e hai aperto il portale per tornare a casa. \nprima di tornare a casa è necessario inserire delle coordinate spaziali ed un codice di accesso.\nSei riuscito a recuperarli?");
                    parserOutput.getObject().setPush(true);
                } else if (parserOutput.getObject().getId() == 1) {
                    msg.append("Per attivare il pannello di controllo ed utilizzare il portale è necessaria una\nparticolare gemma");
                }

                if (parserOutput.getObject().getId() == 2 && description.getCurrentRoom().getObject(1).isPush()) {
                    msg.append("Inserisci il codie di accesso");
                    window.testo3.setVisible(true);
                } else if (parserOutput.getObject().getId() == 2) {
                    msg.append("Per utilizzare il pannello ed il portale e' necessario attivarli prima.");
                }
            }else {
                msg.append("Non ci sono oggetti che puoi premere qui.");
            }
        }
        return msg.toString();
    }

    public static boolean verify(final String input) {
        // Compile regular expression
        final Pattern pattern = Pattern.compile("450732N74157E", Pattern.CASE_INSENSITIVE);
        // Match regex against input
        final Matcher matcher = pattern.matcher(input);
        // Use results...
        return matcher.matches();
    }

}
