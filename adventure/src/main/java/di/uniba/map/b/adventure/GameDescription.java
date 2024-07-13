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
    
    
    public void setCurrentRoomById(int roomId){
    Optional<Room> room = rooms.stream()
                .filter(r -> r.getId() == roomId)
                .findFirst();
        room.ifPresent(r -> this.currentRoom = r);
    }
    
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

    public abstract void nextMove(ParserOutput p, Window window);

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
