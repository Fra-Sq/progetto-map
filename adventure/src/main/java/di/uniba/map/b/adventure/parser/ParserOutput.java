/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.parser;

import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Command;

/**
 *
 * @author sangiovannesi
 */
public class ParserOutput {

    private Command command;

    private AdvObject object;
    
    private AdvObject invObject;

    /**
     *
     * @param command
     * @param object
     */
    public ParserOutput(Command command, AdvObject object) {
        this.command = command;
        this.object = object;
    }

    /**
     *
     * @param command
     * @param object
     * @param invObejct
     */
    public ParserOutput(Command command, AdvObject object, AdvObject invObejct) {
        this.command = command;
        this.object = object;
        this.invObject = invObejct;
    }

    /**
     *
     * @return
     */
    public Command getCommand() {
        return command;
    }

    /**
     *
     * @param command
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     *
     * @return
     */
    public AdvObject getObject() {
        return object;
    }

    /**
     *
     * @param object
     */
    public void setObject(AdvObject object) {
        this.object = object;
    }

    /**
     *
     * @return
     */
    public AdvObject getInvObject() {
        return invObject;
    }

    /**
     *
     * @param invObject
     */
    public void setInvObject(AdvObject invObject) {
        this.invObject = invObject;
    }

}
