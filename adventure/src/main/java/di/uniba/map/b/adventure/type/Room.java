/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.type;

import di.uniba.map.b.adventure.GameDescription;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sangiovannesi
 */
public class Room {

    private final int id;
    private String name;
    private String description;
    private String look;
    private boolean visible = true;
    private Room south = null;
    private Room north = null;
    private Room east = null;
    private Room west = null;
    private final List<AdvObject> objects = new ArrayList<>();
    private boolean monsterAlive = true;
    private String dynamicLook;
    private final GameDescription game;  // Riferimento alla GameDescription

    // Costruttore aggiornato
    public Room(int id, GameDescription game) {
        this.id = id;
        this.game = game;
    }

    public Room(int id, String name, String description, GameDescription game) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public Room getWest() {
        return west;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public List<AdvObject> getObjects() {
        return objects;
    }

    public void addObject(AdvObject obj) {
        if(obj.getId()!=6)
            objects.add(obj);
        game.addObject(obj);  // Aggiungi l'oggetto alla lista allObjects
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        return this.id == other.id;
    }

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }

    public AdvObject getObject(int id) {
        for (AdvObject o : objects) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }

    public String getDynamicLook() {
        StringBuilder dynamiclook = new StringBuilder(look);

        for (AdvObject obj : objects) {
            if (obj instanceof AdvObjectContainer && obj.getId() == 9) { // porta rinforzata
                if (((AdvObjectContainer) obj).isOpen()) {
                    dynamiclook.append("\nLa porta rinforzata è aperta.");
                } else {
                    dynamiclook.append("\nLa porta rinforzata è chiusa.");
                }
            }
        }

        return dynamiclook.toString();
    }

    public AdvObject getObjectByName(String name) {
        for (AdvObject obj : objects) {
            if (obj.getName().equalsIgnoreCase(name)) {
                return obj;
            }
        }
        return null;
    }

    public boolean isMonsterAlive() {
        return monsterAlive;
    }

    public void setMonsterAlive(boolean alive) {
        this.monsterAlive = alive;
    }

    public void removeObject(AdvObject object) {
        objects.remove(object);
    }

    public String getDynamicLookmonster() {
        return dynamicLook != null ? dynamicLook : description;
    }

    public void setDynamicLook(String dynamicLook) {
        this.dynamicLook = dynamicLook;
    }
}
