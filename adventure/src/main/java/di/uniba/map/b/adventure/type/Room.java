package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pierpaolo
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
    /**
     *
     * @param id
     */
    public Room(int id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     */
    public Room(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     *
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     *
     * @return
     */
    public Room getSouth() {
        return south;
    }

    /**
     *
     * @param south
     */
    public void setSouth(Room south) {
        this.south = south;
    }

    /**
     *
     * @return
     */
    public Room getNorth() {
        return north;
    }

    /**
     *
     * @param north
     */
    public void setNorth(Room north) {
        this.north = north;
    }

    /**
     *
     * @return
     */
    public Room getEast() {
        return east;
    }

    /**
     *
     * @param east
     */
    public void setEast(Room east) {
        this.east = east;
    }

    /**
     *
     * @return
     */
    public Room getWest() {
        return west;
    }

    /**
     *
     * @param west
     */
    public void setWest(Room west) {
        this.west = west;
    }

    /**
     *
     * @return
     */
    public List<AdvObject> getObjects() {
        return objects;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
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

    /**
     *
     * @return
     */
    public String getLook() {
        return look;
    }

    /**
     *
     * @param look
     */
    public void setLook(String look) {
        this.look = look;
    }

    /**
     *
     * @param id
     * @return
     */
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
    /**
     * Verifica se il mostro è ancora vivo
     * @return true se il mostro è vivo, false altrimenti
     */
    public boolean isMonsterAlive() {
        return monsterAlive;
    }

    /**
     * Imposta lo stato del mostro (vivo o morto)
     * @param alive true se il mostro è vivo, false se è morto
     */
    public void setMonsterAlive(boolean alive) {
        this.monsterAlive = alive;
    }
    /**
     * Rimuove un oggetto dalla lista degli oggetti della stanza
     * @param object L'oggetto da rimuovere
     */
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
