/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Room;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import di.uniba.map.b.adventure.GameObservable;
import di.uniba.map.b.adventure.GameObserver;

/**
 * ATTENZIONE: La descrizione del gioco è fatta in modo che qualsiasi gioco
 * debba estendere la classe GameDescription. L'Engine è fatto in modo che possa
 * eseguire qualsiasi gioco che estende GameDescription, in questo modo si
 * possono creare più gioci utilizzando lo stesso Engine.
 *
 * Diverse migliorie possono essere applicate: - la descrizione del gioco
 * potrebbe essere caricate da file o da DBMS in modo da non modificare il
 * codice sorgente - l'utilizzo di file e DBMS non è semplice poiché all'interno
 * del file o del DBMS dovrebbe anche essere codificata la logica del gioco
 * (nextMove) oltre alla descrizione di stanze, oggetti, ecc...
 *
 * @author pierpaolo
 */
public class FireHouseGame extends GameDescription implements GameObservable {

    private final List<GameObserver> observer = new ArrayList<>();

    private ParserOutput parserOutput;

    private final List<String> messages = new ArrayList<>();

    /**
     *
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
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
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit", "basta"});
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
        //Rooms
        Room portalRoom = new Room(0, "Sala del portale", "La sala della navicella aliena è circolare e illuminata da una luce soffusa." +
                "\nAl centro, un portale scintillante fluttua, circondato da rune luminose. Pannelli di controllo e cavi collegano il portale a macchinari misteriosi. \nDevi attraversare il portale per scappare.");
        portalRoom.setLook("Sei nella sala del portale, il portale è l'unico modo per scappare ma non è attivo. \nAd EST vedi un corridoio.");
        Room corridor = new Room(1, "Corridoio", "Il corridoio è lungo e stretto, con pareti di metallo e luci intermittenti.");
        corridor.setLook("Sei nel corridoio, vedi che continua verso EST e noti una presa d’aria sul muro, senti dei passi venire verso di te.");
        Room corridor2 = new Room(2, "Corridoio", "Il corridoio è lungo e stretto, con pareti di metallo e luci intermittenti.");
        corridor2.setLook("Sei arrivato nell’angolo del corridoio, noti una porta a NORD e il corridoio continua verso SUD.");
        Room corridor3 = new Room(3, "Corridoio", "Il corridoio è lungo e stretto, con pareti di metallo e luci intermittenti.");
        corridor3.setLook("Sei ancora nel corridoio, vedi una porta verso EST e il corridoio continua verso SUD.");
        Room corridor4 = new Room(4, "Corridoio", "Il corridoio è lungo e stretto, con pareti di metallo e luci intermittenti.");
        corridor4.setLook("Il corridoio continua, vedi una porta verso EST e una porta a OVEST, il corridoio continua verso SUD e verso NORD.");
        Room corridor5 = new Room(5, "Corridoio", "Il corridoio è lungo e stretto, con pareti di metallo e luci intermittenti.");
        corridor5.setLook("Sei arrivato alla fine del corridoio e vedi una porta dritta di fronte a te, verso SUD, noti che è più rinforzata delle altre. Verso NORD ripercorri il corridoio.");
        Room controlRoom = new Room(6, "Sala di controllo", "Il cuore pulsante della navicella, con pannelli di controllo bioluminescenti e schermi olografici che fluttuano nell’aria, un grande schermo mostra un pianeta sconosciuto.");
        controlRoom.setLook("Sei nella sala di controllo, Noti quella che (per noi terrestri) sembra una cassaforte in un angolo della stanza. L'uscita e' a SUD.");
        Room lab = new Room(7, "Laboratorio", "Una stanza piena di strumenti scientifici avanzati per l’analisi e la sperimentazione. \nContiene campioni di flora e fauna di diversi pianeti. Al centro, un tavolo di lavoro interattivo permette agli alieni di studiare la vita extraterrestre.");
        lab.setLook("Sei nel laboratorio, vedi un tavolo di lavoro al centro della stanza e una porta a EST.");
        Room anteroom = new Room(8, "Anticamera", "Una stanza di transizione tra il corridoio e l'archivio, con armadietti e pannelli di controllo. \nEntri nell'anticamera, dopo pochi secondi si chiude la porta dietro di te e si apre quella davanti.");
        anteroom.setLook("Sei nell'anticamera, Vedi un alieno gigante dormiente, noti che ha qualcosa tatuato su uno dei suoi tentacoli.\n" +
                "Attento ad avvicinarti senza un’adeguata arma, potrebbe svegliarsi e non avresti l’opportunità di difenderti.\nvedi una porta a NORD e una a SUD.");
        Room archive = new Room(9, "Archivio", "Una stanza piena di scaffalature piene di rotoli di pergamena e dischi di cristallo. \nIl soffitto è illuminato da una luce soffusa che fa brillare i simboli alieni incisi sulle pareti.");
        archive.setLook("Sei nell'archivio, ci sono decine di scaffali con su scritti i nomi dei vari pianeti, magari c’è quello con le informazioni sulla Terra. \nL'uscita è a NORD.");
        Room armory = new Room(10, "Armeria", "Una stanza piena di armi e armature, con un odore di olio e metallo.");
        armory.setLook("Sei nell'armeria, vedi armi di ogni tipo, alcune ti sembrano familiari, forse potresti usarle per difenderti. \nL'uscita è a OVEST.");
        Room engineRoom = new Room(11, "Sala motori", "Una stanza piena di macchinari e motori, con un rumore assordante e un odore di carburante. \nLa porta è bloccata, serve una tessera magnetica per aprirla.");
        engineRoom.setLook("Sei nella sala motori, vedi un grande motore al centro della stanza, la porta è bloccata, serve una tessera magnetica per aprirla. \nL'uscita è a OVEST.");


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
        //obejcts
        AdvObject portal = new AdvObject(1, "portale", "Un portale scintillante fluttua, circondato da rune luminose.");
        portal.setAlias(new String[]{"portal"});
        portal.setOpenable(true);
        portalRoom.getObjects().add(portal);
        AdvObject controlPanel = new AdvObject(2, "pannelli di controllo", "Schermi e console con interfacce aliene, pulsanti luminosi e ologrammi interattivi.");
        controlPanel.setAlias(new String[]{"pannello", "console", "schermi", "console di controllo"});
        controlPanel.setPushable(true);
        portalRoom.getObjects().add(controlPanel);
        AdvObject alien = new AdvObject(3, "alieno", "Un alieno gigante dormiente, ha qualcosa tatuato su uno dei suoi tentacoli.");
        alien.setAlias(new String[]{"gigante", "mostro", "essere", "alieno gigante"});
        anteroom.getObjects().add(alien);
        AdvObjectContainer safe = new AdvObjectContainer(4, "cassaforte", "Una cassaforte con un pannello di controllo, \nnecessita di uuna chiave per poter essere aperta.");
        safe.setAlias(new String[]{"cassa", "cassaforte"});
        safe.setOpenable(true);
        controlRoom.getObjects().add(safe);
        AdvObject key = new AdvObject(5, "chiave", "Una chiave magnetica, potrebbe aprire la cassaforte.");
        key.setAlias(new String[]{"tessera", "carta", "magnetica"});
        key.setPickupable(true);
        AdvObject gem = new AdvObject(6, "gemma", "Una gemma scintillante, sembra avere un potere magico.");
        gem.setAlias(new String[]{"pietra", "sasso", "cristallo"});
        gem.setPickupable(true);
        safe.add(gem); //inseritaa la gemma all'interno della cassa forte
        AdvObjectContainer table = new AdvObjectContainer(7, "tavolo", "Un tavolo di lavoro interattivo con strumenti scientifici avanzati.");
        table.setAlias(new String[]{"tavolo di lavoro", "banco", "scrivania"});
        table.setOpenable(false);
        lab.getObjects().add(table);
        table.add(key); //aggiunta la chiave sul tavolo
        AdvObject sword = new AdvObject(8, "spada", "Una spada affilata, sembra essere di origine terrestre.");
        sword.setAlias(new String[]{"arma", "spadone", "falcione"});
        sword.setPickupable(true);
        armory.getObjects().add(sword);
        AdvObjectContainer door = new AdvObjectContainer(9, "porta", "Una porta rinforzata, sembra essere l'uscita.");
        door.setAlias(new String[]{"uscita", "portone", "porta rinforzata"});
        door.setOpenable(true);
        corridor4.getObjects().add(door);
        AdvObject vent = new AdvObject(10, "presadaria", "Una presa d’aria sul muro.");
        vent.setAlias(new String[]{"presadaria", "presad'aria", "presad'aria", "condotto"});
        vent.setPushable(true);
        corridor.getObjects().add(vent);
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
        //set starting room
        setCurrentRoom(portalRoom);
    }

    /**
     *
     * @param p
     * @param out
     */
    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        parserOutput = p;
        messages.clear();
        if (p.getCommand() == null) {
            out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            Room cr = getCurrentRoom();
            notifyObservers();
            boolean move = !cr.equals(getCurrentRoom()) && getCurrentRoom() != null;
            if (!messages.isEmpty()) {
                for (String m : messages) {
                    if (m.length() > 0) {
                        out.println(m);
                    }
                }
            }
            if (move) {
                out.println(getCurrentRoom().getName());
                out.println("================================================");
                out.println(getCurrentRoom().getDescription());
            }
        }
    }

    /**
     *
     * @param o
     */
    @Override
    public void attach(GameObserver o) {
        if (!observer.contains(o)) {
            observer.add(o);
        }
    }

    /**
     *
     * @param o
     */
    @Override
    public void detach(GameObserver o) {
        observer.remove(o);
    }

    /**
     *
     */
    @Override
    public void notifyObservers() {
        for (GameObserver o : observer) {
            messages.add(o.update(this, parserOutput));
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getWelcomeMsg() {
        return "Sei appena tornato a casa e non sai cosa fare.\nTi ricordi che non hai ancora utilizzato quel fantastico regalo di tua zia Lina.\n"
                + "Sarà il caso di cercarlo e di giocarci!";
    }
}
