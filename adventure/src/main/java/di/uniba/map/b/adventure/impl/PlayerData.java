/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

/**
 * Represents the data of a player in the game, including their name, the date, and the time.
 * This class is used to manage player-specific information that can be utilized throughout the game.
 */
public class PlayerData {
    private String name;
    private String date;
    private String time;

    /**
     * Constructs a new PlayerData instance with specified name, date, and time.
     *
     * @param name The name of the player.
     * @param date The date related to the player's progress or state.
     * @param time The time related to the player's progress or state.
     */
    public PlayerData(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    /**
     * Gets the player's name.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player's name.
     *
     * @param name The new name of the player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the date associated with the player's current state or progress.
     *
     * @return The date related to the player's progress or state.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date associated with the player's current state or progress.
     *
     * @param date The new date related to the player's progress or state.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the time associated with the player's current state or progress.
     *
     * @return The time related to the player's progress or state.
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time associated with the player's current state or progress.
     *
     * @param time The new time related to the player's progress or state.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Returns a string representation of the player data, including name, date, and time.
     *
     * @return A string representation of the player data.
     */
    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", date=" + date + ", time=" + time + '}';
    }
}
