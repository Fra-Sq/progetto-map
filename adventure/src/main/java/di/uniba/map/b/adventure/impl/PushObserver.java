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

/**
 * Observer implementation for handling "push" commands within the game.
 * This observer allows players to interact with objects that can be pushed, triggering specific game events or actions.
 */
public class PushObserver implements GameObserver {

    /**
     * Processes a "push" command, performing actions based on the object being pushed.
     * If the object is pushable, it triggers specific events such as activating a control panel or requiring further input from the player.
     * Messages are generated to inform the player of the action's outcome or next steps.
     *
     * @param description The current state of the game, including the player's current room and inventory.
     * @param parserOutput The parsed output of the player's command, including the command type and targeted object.
     * @param window The game window where output messages are displayed and interactive elements may be shown.
     * @return A string message indicating the outcome of the "push" command or further instructions for the player.
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
                    msg.append("Inserisci il codice di accesso");
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

    /**
     * Verifies if the given input matches a specific pattern, typically used for validating codes or inputs in the game.
     * This method is used to check if the player has entered a correct access code or similar input.
     *
     * @param input The string input to be verified against the pattern.
     * @return true if the input matches the pattern, false otherwise.
     */
    public static boolean verify(final String input) {
        // Compile regular expression
        final Pattern pattern = Pattern.compile("450732N74157E", Pattern.CASE_INSENSITIVE);
        // Match regex against input
        final Matcher matcher = pattern.matcher(input);
        // Use results...
        return matcher.matches();
    }

}
