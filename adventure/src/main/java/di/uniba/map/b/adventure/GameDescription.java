/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.impl.Database;
import di.uniba.map.b.adventure.impl.Window;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.Room;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GameDescription {

    private final List<Room> rooms = new ArrayList<>();
    private final List<Command> commands = new ArrayList<>();
    private List<AdvObject> inventory = new ArrayList<>();
    private Room currentRoom;
    private boolean keyUsed = false;
    private final List<AdvObject> allObjects = new ArrayList<>();

    public List<Room> getRooms() {
        return rooms;
    }

    public void setCurrentRoomById(int roomId) {
        Optional<Room> room = rooms.stream()
                .filter(r -> r.getId() == roomId)
                .findFirst();
        room.ifPresent(r -> this.currentRoom = r);
    }


    public void setInventoryByIds(List<Integer> inventoryIds) {
        this.inventory = new ArrayList<>();
        for (int id : inventoryIds) {
            Optional<AdvObject> obj = getObjectById(id);
            obj.ifPresent(o -> {
                this.inventory.add(o);
                // Rimuovi l'oggetto dalla stanza in cui si trova
                for (Room room : rooms) {
                    if (room.getObjects().contains(o)) {
                        room.getObjects().remove(o);
                        if (o.getId() == 6) {
                            AdvObject door = room.getObject(4);
                            if (door != null) {
                                door.setOpenable(false);
                            }
                        }
                    }
                }
            });
        }
    }



    public List<Command> getCommands() {
        return commands;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public boolean isKeyUsed() {
        return keyUsed;
    }

    public void setKeyUsed(boolean keyUsed) {
        this.keyUsed = keyUsed;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public List<AdvObject> getInventory() {
        return inventory;
    }

    public abstract void init(Database database) throws Exception;

    public abstract void nextMove(ParserOutput p, PrintStream out, Window window);

    public abstract String getWelcomeMsg();

    public void addObject(AdvObject obj) {
        allObjects.add(obj);
    }

    public Optional<AdvObject> getObjectById(int id) {
        return allObjects.stream()
                .filter(o -> o.getId() == id)
                .findFirst();
    }
}
