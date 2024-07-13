/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a command in the adventure game.
 * Commands are actions that the player can perform, such as moving, picking up items, or interacting with the game world.
 */
public class Command {

    private final CommandType type;

    private final String name;

    private Set<String> alias;

    /**
     * Constructs a Command with a specified type and name.
     * This constructor initializes a command without any aliases.
     *
     * @param type The type of the command.
     * @param name The primary name of the command.
     */
    public Command(CommandType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * Constructs a Command with a specified type, name, and set of aliases.
     * This constructor allows for the initialization of a command with multiple names.
     *
     * @param type  The type of the command.
     * @param name  The primary name of the command.
     * @param alias A set of alternative names or aliases for the command.
     */
    public Command(CommandType type, String name, Set<String> alias) {
        this.type = type;
        this.name = name;
        this.alias = alias;
    }

    /**
     * Returns the primary name of the command.
     *
     * @return The primary name of the command.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the set of aliases for the command.
     *
     * @return A set of alternative names or aliases for the command.
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     * Sets the aliases for the command.
     *
     * @param alias A set of alternative names or aliases for the command.
     */
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    /**
     * Sets the aliases for the command from an array of strings.
     * This method converts the array into a set and assigns it to the command.
     *
     * @param alias An array of alternative names or aliases for the command.
     */
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     * Returns the type of the command.
     *
     * @return The type of the command.
     */
    public CommandType getType() {
        return type;
    }

    /**
     * Generates a hash code for the command.
     * The hash code is based primarily on the command's type.
     *
     * @return A hash code for the command.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.type);
        return hash;
    }

    /**
     * Compares this command to another object for equality.
     * Two commands are considered equal if they have the same type.
     *
     * @param obj The object to compare with this command.
     * @return true if the specified object is a command with the same type; false otherwise.
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
        final Command other = (Command) obj;
        return this.type == other.type;
    }

}
