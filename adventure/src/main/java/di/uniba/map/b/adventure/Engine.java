package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.impl.Database;
import di.uniba.map.b.adventure.impl.Window;
import di.uniba.map.b.adventure.impl.SpaceEscape;
import di.uniba.map.b.adventure.parser.Parser;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * The Engine class is the main driver of the game. It initializes the game environment,
 * including the game description, parser, and window. It also handles the execution
 * of the game by displaying the start description, room name, and room description.
 */
public class Engine {

    private final GameDescription game; // The game description instance

    private Parser parser; // The parser instance for interpreting player commands
    private Window window; // The window instance for displaying game information

    /**
     * Constructs an Engine object with a specified game description.
     *
     * @param game The game description to be used for this engine instance.
     */
    public Engine(GameDescription game) {
        this.game = game;
        try {
            this.game.init();
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
     * Executes the game by setting up the window, displaying the start description,
     * and showing the current room's name and description.
     */
    public void execute() {
        window = new Window(game, parser);
        window.showStartDescription(game.getWelcomeMsg());
        window.showRoomName(game.getCurrentRoom().getName());
        window.showRoomDescription(game.getCurrentRoom().getDescription());
    }

    /**
     * The main method to start the game. It creates an Engine instance with a SpaceEscape game
     * description and executes the game.
     *
     * @param args the command line arguments (not used).
     */
    public static void main(String[] args) {
        Engine engine = new Engine(new SpaceEscape());
        engine.execute();
    }
}