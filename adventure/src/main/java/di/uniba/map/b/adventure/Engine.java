/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.impl.Database;
import di.uniba.map.b.adventure.impl.Window;
import di.uniba.map.b.adventure.impl.FireHouseGame;
import di.uniba.map.b.adventure.parser.Parser;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 *
 *
 * @author pierpaolo
 */
public class Engine {

    private final GameDescription game;

    private Parser parser;
    private Database database;
    private Window window;
    //private Database database;

    /**
     *
     * @param game
     */
    public Engine(GameDescription game) {
        this.game = game;
        database= new Database();
        try {
            this.game.init(database);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            Set<String> stopwords = Utils.loadFileListInSet(new File("./resources/files/stopwords"));
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
        window.showStartDescription(game.getWelcomeMsg());
        window.showRoomName(game.getCurrentRoom().getName());
        window.showRoomDescription(game.getCurrentRoom().getDescription());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Engine engine = new Engine(new FireHouseGame());
        engine.execute();
    }
}
