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
     * @return
     */
    @Override
    public String update(GameDescription description, ParserOutput parserOutput) {
        StringBuilder msg = new StringBuilder();
        if (parserOutput.getCommand().getType() == CommandType.PUSH) {
            //ricerca oggetti pushabili
            if (parserOutput.getObject() != null && parserOutput.getObject().isPushable()) {
                msg.append("Hai premuto: ").append(parserOutput.getObject().getName()).append("\n");
                if (parserOutput.getObject().getId() == 2 && GameUtils.getObjectFromInventory(description.getInventory(), 6) != null) {
                    System.out.println("Hai attivato il pannello di controllo e hai aperto il portale per tornare a casa. \nprima di tornare a casa è necessario inserire delle coordinate spaziali ed un codice di accesso.\nSei riuscito a recuperarli?");
                    Scanner scanner = new Scanner(System.in);
                    String input = scanner.nextLine();
                    if (input.equals("si")) {
                        System.out.println("Inserire codice di accesso e le coordinate spaziali corrette per tornare a casa:");
                        input = scanner.nextLine();
                        if (input.equals("07738")) {
                            System.out.println("Hai inserito correttamente il codice di accesso.");
                            input = scanner.nextLine();
                            if (verify(input)) {
                                System.out.println("Hai inserito correttamente le coordinate spaziali \nSei tornato a casa sano e salvo.");
                                description.setCurrentRoom(null);
                            } else {
                                System.out.println("Hai inserito delle coordinate spaziali errate, muori malamente disperso nello spazio...");
                                description.setCurrentRoom(null);
                            }
                        } else {
                            System.out.println("Codice di accesso errato, non puoi tornare a casa.");
                        }
                    } else {
                        System.out.println("Non hai recuperato i codici di accesso e le coordinate spaziali, non puoi tornare a casa.");
                    }
                    msg = new StringBuilder();
                } else if (parserOutput.getObject().getId() == 2) {
                    msg.append("Per attivare il pannello di controllo ed utilizzare il portale è necessaria una particolare gemma");
                }
            } else if (parserOutput.getInvObject() != null && parserOutput.getInvObject().isPushable()) {
                msg.append("Hai premuto: ").append(parserOutput.getInvObject().getName()).append("\n");
                if (parserOutput.getInvObject().getId() == 3 && GameUtils.getObjectFromInventory(description.getInventory(), 1) != null) {
                    msg.append("Premi il pulsante del giocattolo e in seguito ad una forte esplosione la tua casa prende fuoco...\ntu e tuoi famigliari cercate invano di salvarvi e venite avvolti dalle fiamme...\nè stata una morte CALOROSA...addio!");
                    description.setCurrentRoom(null);
                } else if (parserOutput.getInvObject().getId() == 3) {
                    msg.append("Non posso utilizzare il giocattolo senza delle batterie.");
                }
            } else {
                msg.append("Non ci sono oggetti che puoi premere qui.");
            }
        }
        return msg.toString();
    }

    public static boolean verify(final String input) {
        // Compile regular expression
        final Pattern pattern = Pattern.compile("450732N 74157E", Pattern.CASE_INSENSITIVE);
        // Match regex against input
        final Matcher matcher = pattern.matcher(input);
        // Use results...
        return matcher.matches();
    }

}
