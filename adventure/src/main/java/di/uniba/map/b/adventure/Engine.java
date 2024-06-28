/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.impl.Database;
import di.uniba.map.b.adventure.impl.Window;
import di.uniba.map.b.adventure.impl.FireHouseGame;
import di.uniba.map.b.adventure.impl.PlayTime;
import di.uniba.map.b.adventure.parser.Parser;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

/**
 *
 *
 * @author pierpaolo
 */
public class Engine {

    private final GameDescription game;

    private Parser parser;
    
    private Window window;
    //private Database database;

    /**
     *
     * @param game
     */
    public Engine(GameDescription game) {
        this.game = game;
        try {
            this.game.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            Set<String> stopwords = Utils.loadFileListInSet(new File("./resources/stopwords"));
            parser = new Parser(stopwords);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     *
     */
    public void execute() {
        window= new Window(game, parser);
        System.out.println("====================================");
        System.out.println("* Space Adventure V1.0 - 2023-2024 *");
        System.out.println("*           developed by           *");
        System.out.println("*           Russo Nicola           *");
        System.out.println("*        Scarale Francesco         *");
        System.out.println("* Squarcella-Gorgoglione Francesco *");
        System.out.println("====================================");
      
        PlayTime.startGame();
        System.out.println();
        System.out.println(game.getWelcomeMsg());
        window.showStartDescription(game.getWelcomeMsg());
        System.out.println();
        System.out.println("Ti trovi qui: " + game.getCurrentRoom().getName());
        window.showRoomName(game.getCurrentRoom().getName());
        System.out.println();
        System.out.println(game.getCurrentRoom().getDescription());
        window.showRoomDescription(game.getCurrentRoom().getDescription());
        System.out.println();
        System.out.print("?> ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
            if (p == null || p.getCommand() == null) {
                System.out.println("Non capisco quello che mi vuoi dire.");
            } else if (p.getCommand() != null && p.getCommand().getType() == CommandType.END) {
                System.out.println("Sei un fifone, addio!");
                PlayTime.endGame();
                break;
            } else {
                game.nextMove(p, System.out, window);
                if (game.getCurrentRoom() == null) {
                    System.out.println("La tua avventura termina qui! Complimenti!");
                    PlayTime.endGame();
                    System.exit(0);
                }
            }
            System.out.print("?> ");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Engine engine = new Engine(new FireHouseGame());
        engine.execute();
    }

}
