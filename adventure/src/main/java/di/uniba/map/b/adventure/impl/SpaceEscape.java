/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Room;
import java.util.ArrayList;
import java.util.List;
import di.uniba.map.b.adventure.GameObservable;
import di.uniba.map.b.adventure.GameObserver;

/**
 * Main class for the SpaceEscape game, extending GameDescription and implementing GameObservable.
 * This class initializes the game environment, including rooms, objects, and commands, and handles player moves.
 */
public class SpaceEscape extends GameDescription implements GameObservable {

    private final List<GameObserver> observer = new ArrayList<>();
    private Database database;
    private ParserOutput parserOutput;
    private final List<String> messages = new ArrayList<>();

    /**
     * Initializes the game environment using data from the provided database.
     * This includes setting up rooms, commands, objects, and observers.
     *
     * @throws Exception If there is an error accessing the database.
     */
    @Override
    public void init() throws Exception {
        database = new Database();
        messages.clear();
        //Commands
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getCommands().add(nord);
        Command iventory = new Command(CommandType.INVENTORY, "inventario");
        iventory.setAlias(new String[]{"inv"});
        getCommands().add(iventory);
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getCommands().add(sud);
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getCommands().add(est);
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getCommands().add(ovest);
        Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[]{"end", "fine", "abbandona", "muori", "ammazzati", "ucciditi", "suicidati", "exit", "basta"});
        getCommands().add(end);
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getCommands().add(look);
        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi"});
        getCommands().add(pickup);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{});
        getCommands().add(open);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi", "attiva"});
        getCommands().add(push);
        Command use = new Command(CommandType.USE, "usa");
        use.setAlias(new String[]{"utilizza", "combina"});
        getCommands().add(use);
        Command read = new Command(CommandType.READ, "leggi");
        read.setAlias(new String[]{"sfoglia"});
        getCommands().add(read);
        Command killMonster = new Command(CommandType.KILL, "ammazza");
        killMonster.setAlias(new String[]{"uccidi", "elimina", "distruggi"});
        getCommands().add(killMonster);
        //Rooms
        Room portalRoom = new Room(0, database.getNameById("portalRoom"), database.getDescriptionById("portalRoom"), this);
        portalRoom.setLook(database.getRoomLookById("portalRoom"));
        Room corridor = new Room(1, database.getNameById("corridor"), database.getDescriptionById("corridor"), this);
        corridor.setLook(database.getRoomLookById("corridor"));
        Room corridor2 = new Room(2, database.getNameById("corridor2"), database.getDescriptionById("corridor2"), this);
        corridor2.setLook(database.getRoomLookById("corridor2"));
        Room corridor3 = new Room(3, database.getNameById("corridor3"), database.getDescriptionById("corridor3"), this);
        corridor3.setLook(database.getRoomLookById("corridor3"));
        Room corridor4 = new Room(4, database.getNameById("corridor4"), database.getDescriptionById("corridor4"), this);
        corridor4.setLook(database.getRoomLookById("corridor4"));
        Room corridor5 = new Room(5, database.getNameById("corridor5"), database.getDescriptionById("corridor5"), this);
        corridor5.setLook(database.getRoomLookById("corridor5"));
        Room controlRoom = new Room(6, database.getNameById("controlRoom"), database.getDescriptionById("controlRoom"), this);
        controlRoom.setLook(database.getRoomLookById("controlRoom"));
        Room lab = new Room(7, database.getNameById("lab"), database.getDescriptionById("lab"), this);
        lab.setLook(database.getRoomLookById("lab"));
        Room anteroom = new Room(8, database.getNameById("anteroom"), database.getDescriptionById("anteroom"), this);
        anteroom.setMonsterAlive(true);  // Imposta il mostro come vivo all'inizio
        anteroom.setLook(database.getRoomLookById("anteroom"));
        Room archive = new Room(9, database.getNameById("archive"), database.getDescriptionById("archive"), this);
        archive.setLook(database.getRoomLookById("archive"));
        Room armory = new Room(10, database.getNameById("armory"), database.getDescriptionById("armory"), this);
        armory.setLook(database.getRoomLookById("armory"));
        Room engineRoom = new Room(11, database.getNameById("engineRoom"), database.getDescriptionById("engineRoom"), this);
        engineRoom.setLook(database.getRoomLookById("engineRoom"));
        

        //map
        portalRoom.setEast(corridor);
        corridor.setEast(corridor2);
        corridor.setWest(portalRoom);
        corridor2.setSouth(corridor3);
        corridor2.setNorth(controlRoom);
        corridor2.setWest(corridor);
        controlRoom.setSouth(corridor2);
        corridor3.setSouth(corridor4);
        corridor3.setNorth(corridor2);
        corridor3.setEast(armory);
        armory.setWest(corridor3);
        corridor4.setSouth(corridor5);
        corridor4.setNorth(corridor3);
        corridor4.setEast(engineRoom);
        corridor4.setWest(lab);
        engineRoom.setWest(corridor4);
        lab.setEast(corridor4);
        corridor5.setSouth(anteroom);
        corridor5.setNorth(corridor4);
        anteroom.setSouth(archive);
        anteroom.setNorth(corridor5);
        archive.setNorth(anteroom);
        
        //obejcts
        AdvObject portal = new AdvObject(1, database.getNameById("portal"), database.getDescriptionById("portal"));
        portal.setAlias(new String[]{"portal"});
        portal.setPushable(true);
        portalRoom.addObject(portal);
        
        AdvObject controlPanel = new AdvObject(2, database.getNameById("controlPanel"), database.getDescriptionById("controlPanel"));
        controlPanel.setAlias(new String[]{"pannello", "console", "schermi", "console di controllo"});
        controlPanel.setPushable(true);
        portalRoom.addObject(controlPanel);
        
        AdvObjectContainer alien = new AdvObjectContainer(3, database.getNameById("alien"), database.getDescriptionById("alien"));
        alien.setAlias(new String[]{"gigante", "mostro", "essere", "alieno gigante"});
        alien.setCreature(true);
        anteroom.addObject(alien);
        
        AdvObjectContainer safe = new AdvObjectContainer(4, database.getNameById("safe"), database.getDescriptionById("safe"));
        safe.setAlias(new String[]{"cassa", "cassaforte"});
        safe.setOpenable(true);
        controlRoom.addObject(safe);
        
        AdvObject key = new AdvObject(5, database.getNameById("key"), database.getDescriptionById("key"));
        key.setAlias(new String[]{"tessera", "carta", "magnetica"});
        key.setPickupable(true);
        lab.addObject(key);
        
        AdvObject gem = new AdvObject(6, database.getNameById("gem"), database.getDescriptionById("gem"));
        gem.setAlias(new String[]{"pietra", "sasso", "cristallo"});
        gem.setPickupable(true);
        safe.add(gem); //inseritaa la gemma all'interno della cassa forte
        controlRoom.addObject(gem);
        
        AdvObject sword = new AdvObject(8, database.getNameById("sword"), database.getDescriptionById("sword"));
        sword.setAlias(new String[]{"arma", "spadone", "falcione"});
        sword.setPickupable(true);
        armory.addObject(sword);
        
        AdvObject door = new AdvObjectContainer(9, database.getNameById("door"), database.getDescriptionById("door"));
        door.setAlias(new String[]{"uscita", "portone", "porta rinforzata, porta"});
        door.setOpen(false); // Imposta lo stato iniziale della porta come chiusa
        corridor4.addObject(door);
        
        AdvObject map = new AdvObject(11, database.getNameById("map"), database.getDescriptionById("map"));
        map.setAlias(new String[]{"cartina", "pianeta", "coordinate", "mappe"});
        map.setPickupable(true);
        map.setReadable(true);
        map.setContents("COORDINATE VIA LATTEA\n" +
            "Pianeta: Marte\n" +
            "Coordinate: 43522\" N 1372630\" E\n" +
            "Pianeta: Venere\n" +
            "Coordinate: 183657\" N 773300\" E\n" +
            "Pianeta: Giove\n" +
            "Coordinate: 230713\" N 821107\" E\n" +
            "Pianeta: Saturno\n" +
            "Coordinate: 150732\" N 74157\" E\n" +
            "Pianeta: Urano\n" +
            "Coordinate: 43522\" N 1372630\" E\n" +
            "Pianeta: Nettuno\n" +
            "Coordinate: 183657\" N 773300\" E\n" +
            "Pianeta: Plutone\n" +
            "Coordinate: 230713\" N 821107\" E\n" +
            "Pianeta: Mercurio\n" +
            "Coordinate: 150732\" N 74157\" E\n" +
            "Pianeta: Terra\n" +
            "Coordinate: 450732\" N 74157\" E\n" +
            "Pianeta: Luna\n" +
            "Coordinate: 43522\" N 1372630\" E\n" +
            "Stella: Sole\n" +
            "Coordinate: 183657\" N 773300\" E\n" +
            "Pianeta: Mercurio\n" +
            "Coordinate: 150732\" N 74157\" E\n");
        archive.addObject(map);
        
        //Observer
        GameObserver moveObserver = new MoveObserver();
        this.attach(moveObserver);
        GameObserver invObserver = new InventoryObserver();
        this.attach(invObserver);
        GameObserver pushObserver = new PushObserver();
        this.attach(pushObserver);
        GameObserver lookatObserver = new LookAtObserver();
        this.attach(lookatObserver);
        GameObserver pickupObserver = new PickUpObserver();
        this.attach(pickupObserver);
        GameObserver openObserver = new OpenObserver();
        this.attach(openObserver);
        GameObserver useObserver = new UseObserver();
        this.attach(useObserver);
        GameObserver readObserver = new ReadObserver();
        this.attach(readObserver);
        GameObserver killObserver = new KillObserver();
        this.attach(killObserver);
        //set starting room
        getRooms().add(portalRoom);
        getRooms().add(corridor);
        getRooms().add(corridor2);
        getRooms().add(corridor3);
        getRooms().add(corridor4);
        getRooms().add(corridor5);
        getRooms().add(controlRoom);
        getRooms().add(lab);
        getRooms().add(anteroom);
        getRooms().add(archive);
        getRooms().add(armory);
        getRooms().add(engineRoom);
        setCurrentRoom(portalRoom);
        database.close();
    }



/**
 * Processes the next move based on the player's command.
 * This method updates the game state, notifies observers, and displays messages to the player.
 *
 * @param p The parsed output of the player's command.
 * @param window The game window where messages are displayed.
 */
@Override
public void nextMove(ParserOutput p, Window window) {
    parserOutput = p;
    messages.clear();

    if (p.getCommand() == null) {
        window.showMessage("Non ho capito cosa devo fare! Prova con un altro comando.");
    } else {
        Room cr = getCurrentRoom();
        notifyObservers(window);
        boolean move = !cr.equals(getCurrentRoom()) && getCurrentRoom() != null;

        // Controllo per il comando LOOK_AT
        if (p.getCommand().getType() == CommandType.LOOK_AT) {
            AdvObject object = p.getObject();
            if (object != null) {
                window.showMessage("Osservi " + object.getName() + ": " + object.getDescription());
            } else {
                window.showMessage(cr.getLook());
                if (cr.getName().equalsIgnoreCase("Anticamera")) {
                    if (cr.isMonsterAlive()) {
                        cr.setDynamicLook("Sei nell'anticamera, l'alieno gigante si sta svegliando e ti osserva con occhi minacciosi!\n");
                    }
                    window.showMessage(cr.getDynamicLook());
                }
            }
        } else {
            if (!messages.isEmpty()) {
                for (String m : messages) {
                    if (m.length() > 0) {
                        window.showMessage(m);
                    }
                }
            }

            if (move) {
                Room currentRoom = getCurrentRoom();
                if (currentRoom.getName().equals("Sala motori")) {
                    boolean hasKey = getInventory().stream().anyMatch(obj -> obj.getId() == 5);
                    if (hasKey) {
                        if (isKeyUsed()) {
                            window.showRoomName(currentRoom.getName());
                            window.showRoomDescription(currentRoom.getDescription());
                        } else {
                            window.showMessage("La porta è chiusa ma hai la chiave. Usa la chiave per aprirla.");
                            setCurrentRoom(cr);
                        }
                    } else {
                        window.showMessage("La porta è chiusa e non puoi entrare perché non hai la chiave.");
                        setCurrentRoom(cr); // Torna alla stanza precedente
                    }
                } else {
                    window.showRoomName(currentRoom.getName());
                    window.showRoomDescription(currentRoom.getDescription());
                }
            } else {
                // Se non c'è stato un movimento, mostra la stanza corrente
                window.showRoomName(cr.getName());
                window.showRoomDescription(cr.getDescription());
            }
        }
   }
}






    /**
     * Attaches an observer to the game.
     * Observers are notified of game events and can update the game state or display messages.
     *
     * @param o The observer to attach.
     */
    @Override
    public void attach(GameObserver o) {
        if (!observer.contains(o)) {
            observer.add(o);
        }
    }

    /**
     * Detaches an observer from the game.
     *
     * @param o The observer to detach.
     */
    @Override
    public void detach(GameObserver o) {
        observer.remove(o);
    }

    /**
     * Notifies all attached observers of a game event.
     * Observers can update the game state or generate messages based on the event.
     *
     * @param window The game window where messages may be displayed.
     */
    @Override
    public void notifyObservers(Window window) {
        for (GameObserver o : observer) {
            messages.add(o.update(this, parserOutput, window));
        }
    }

    /**
     * Returns the welcome message to be displayed at the start of the game.
     *
     * @return The welcome message string.
     */
    @Override
    public String getWelcomeMsg() {

        return "Eri stato catturato da alcuni mercenari alieni ingaggiati da un collezionista\n"
                + "galattico, ma sei riuscito a scappare dalla tua cella, sei riuscito ad arrivare\n "
                + "   al sistema di teletrasporto installato sulla nave, ma il portale è spento.\n"
                + "                   premi un qualsiasi tasto della tastiera per iniziare      ";
    }
    
}



