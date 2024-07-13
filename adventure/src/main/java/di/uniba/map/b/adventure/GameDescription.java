/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.impl.Database;
import di.uniba.map.b.adventure.impl.Window;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.Room;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Abstract class representing the game description. It serves as a blueprint for creating
 * specific game instances, defining the common structure and behavior of the game.
 */
public abstract class GameDescription {

    private final List<Room> rooms = new ArrayList<>();
    private final List<Command> commands = new ArrayList<>();
    private List<AdvObject> inventory = new ArrayList<>();
    private Room currentRoom;
    private boolean keyUsed = false;
    private final List<AdvObject> allObjects = new ArrayList<>();

    /**
     * Returns the list of rooms in the game.
     * @return List of Room objects
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Sets the current room based on the provided room ID.
     * @param roomId ID of the room to set as the current room
     */
    public void setCurrentRoomById(int roomId){
    Optional<Room> room = rooms.stream()
                .filter(r -> r.getId() == roomId)
                .findFirst();
        room.ifPresent(r -> this.currentRoom = r);
    }

    /**
     * Sets up the game state based on the provided parameters.
     * @param roomId ID of the current room
     * @param inventoryIds List of IDs for objects in the player's inventory
     * @param monsterAlive Flag indicating if the monster is alive
     * @param isDoorOpen Flag indicating if a door is open
     */
    public void setGame(int roomId, List<Integer> inventoryIds, boolean monsterAlive, boolean isDoorOpen){
        Optional<Room> room = rooms.stream()
                .filter(r -> r.getId() == roomId)
                .findFirst();
        room.ifPresent(r -> this.currentRoom = r);
        
        this.inventory = new ArrayList<>();
        for (int id : inventoryIds) {
            Optional<AdvObject> obj = getObjectById(id);
            obj.ifPresent(o -> {
                this.inventory.add(o);
                // Rimuovi l'oggetto dalla stanza in cui si trova
                for (Room stanze : rooms) {
                    if (stanze.getObjects().contains(o)) {
                        stanze.getObjects().remove(o);
                    }
                    if (o.getId() == 6) {
                        AdvObject safe = stanze.getObject(4);
                        if (safe != null) {
                            safe.setOpenable(false);
                        }
                    }
                    if (stanze.getId()==8 && monsterAlive==false) {
                        stanze.removeObject(stanze.getObject(3));
                        stanze.setMonsterAlive(monsterAlive);
                        stanze.setLook("Ti trovi nell'anticamera, ma il mostro è morto. \nSul tentacolo del mostro c'è un codice tatuato: 07738");
                    }
                    if (stanze.getId()==4 && isDoorOpen==true) {
                        stanze.getObject(9).setOpen(true);
                        setKeyUsed(true);
                    }
                }
            });
        }
    }







    /**
     * Returns the list of commands available in the game.
     * @return List of Command objects
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Returns the current room where the player is located.
     * @return Current Room object
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Returns whether a key has been used in the game.
     * @return true if the key has been used, false otherwise
     */
    public boolean isKeyUsed() {
        return keyUsed;
    }

    /**
     * Sets the flag indicating whether a key has been used in the game.
     * @param keyUsed true to indicate the key has been used, false otherwise
     */
    public void setKeyUsed(boolean keyUsed) {
        this.keyUsed = keyUsed;
    }

    /**
     * Sets the current room where the player is located.
     * @param currentRoom The Room object to set as the current room
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Returns the player's inventory.
     * @return List of AdvObject objects in the player's inventory
     */
    public List<AdvObject> getInventory() {
        return inventory;
    }

    /**
     * Initializes the game with the provided database. This method must be implemented by subclasses.
     * @param database The Database object used for game initialization
     * @throws Exception if an error occurs during initialization
     */
    public abstract void init(Database database) throws Exception;

    /**
     * Processes the player's next move based on the parsed input and updates the game state accordingly.
     * This method must be implemented by subclasses.
     * @param p The parsed player input
     * @param window The game window for displaying output
     */
    public abstract void nextMove(ParserOutput p, Window window);

    /**
     * Returns the welcome message for the game. This method must be implemented by subclasses.
     * @return The welcome message as a String
     */
    public abstract String getWelcomeMsg();

    /**
     * Adds an object to the list of all objects in the game.
     * @param obj The AdvObject to add
     */
    public void addObject(AdvObject obj) {
        allObjects.add(obj);
    }

    /**
     * Retrieves an object by its ID from the list of all objects in the game.
     * @param id The ID of the object to retrieve
     * @return An Optional containing the AdvObject if found, or an empty Optional otherwise
     */
    public Optional<AdvObject> getObjectById(int id) {
        return allObjects.stream()
                .filter(o -> o.getId() == id)
                .findFirst();
    }
}
