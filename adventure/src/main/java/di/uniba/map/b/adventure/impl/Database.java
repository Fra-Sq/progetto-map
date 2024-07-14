/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

/**
 * This class provides database operations for the adventure game.
 * It handles connections to the database and provides methods to retrieve and update game data.
 */
public class Database {

    Properties dbprops;
    Connection conn;

    /**
     * Constructor for Database. Initializes the database connection using JDBC and H2 database.
     */
    public Database() {
        try {
            // Connessione con oggetto Properties
            dbprops = new Properties();
            dbprops.setProperty("user", "user");
            dbprops.setProperty("password", "1234");
            conn = DriverManager.getConnection("jdbc:h2:./resources/db/database", dbprops);
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }

    /**
     * Inserts new data into the database by prompting the user for input.
     * Data includes ID, name, description, and room look.
     *
     * @return true if the data was successfully inserted, false otherwise.
     */
    public boolean insertNewData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci l'ID: ");
        String id = scanner.nextLine();
        System.out.print("Inserisci il Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci la Descrizione: ");
        String descrizione = scanner.nextLine();
        System.out.print("Inserisci il Look della stanza: ");
        String look = scanner.nextLine();

        try {
            String query = "INSERT INTO descrizioni (ID, NOME, DESCRIZIONE, LOOK) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, id); // Imposta l'ID
                ps.setString(2, nome); // Imposta il Nome
                ps.setString(3, descrizione); // Imposta la Descrizione
                ps.setString(4, look); // Imposta il Look
                int rowsAffected = ps.executeUpdate(); // Esegue l'inserimento
                return rowsAffected > 0; // Ritorna true se almeno una riga è stata inserita
            }
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
            return false;
        }
    }

    /**
     * Retrieves the name associated with a given ID from the database.
     * @param id The ID for which the name is to be retrieved.
     * @return The name associated with the ID, or null if not found or in case of an error.
     */
    public String getNameById(String id) {
        String name = null;
        try {
            String query = "SELECT NOME FROM descrizioni WHERE ID = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, id); // Imposta il parametro nella query preparata
                ResultSet rs = ps.executeQuery(); // Esegue la query e ottiene il risultato
                if (rs.next()) {
                    name = rs.getString("NOME"); // Recupera il nome dalla colonna "NOME" nel risultato
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
        return name; // Ritorna il nome recuperato, o null se non trovato o in caso di errore
    }

    /**
     * Retrieves the description associated with a given ID from the database.
     * @param id The ID for which the description is to be retrieved.
     * @return The description associated with the ID, or null if not found or in case of an error.
     */
    public String getDescriptionById(String id) {
        String description = null;
        try {
            String query = "SELECT DESCRIZIONE FROM descrizioni WHERE ID = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, id); // Imposta il parametro nella query preparata
                ResultSet rs = ps.executeQuery(); // Esegue la query e ottiene il risultato
                if (rs.next()) {
                    description = rs.getString("DESCRIZIONE"); // Recupera la descrizione dalla colonna "DESCRIZIONE" nel risultato
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
        return description; // Ritorna la descrizione recuperata, o null se non trovata o in caso di errore
    }

    /**
     * Retrieves the room look associated with a given ID from the database.
     * @param id The ID for which the room look is to be retrieved.
     * @return The room look associated with the ID, or null if not found or in case of an error.
     */
    public String getRoomLookById(String id) {
        String roomLook = null;
        try {
            String query = "SELECT LOOK FROM descrizioni WHERE ID = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, id); // Imposta il parametro nella query preparata
                ResultSet rs = ps.executeQuery(); // Esegue la query e ottiene il risultato
                if (rs.next()) {
                    roomLook = rs.getString("LOOK"); // Recupera il look dalla colonna "LOOK" nel risultato
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
        return roomLook; // Ritorna il look recuperato, o null se non trovato o in caso di errore
    }

    /**
     * Updates the name associated with a given ID in the database.
     * @param id The ID for which the name is to be updated.
     * @param newName The new name to be set.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateNameById(String id, String newName) {
        try {
            String query = "UPDATE descrizioni SET NOME = ? WHERE ID = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, newName); // Imposta il nuovo nome
                ps.setString(2, id); // Imposta l'id
                int rowsAffected = ps.executeUpdate(); // Esegue l'update
                return rowsAffected > 0; // Ritorna true se almeno una riga è stata aggiornata
            }
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
            return false;
        }
    }

    /**
     * Updates the description associated with a given ID in the database.
     * @param id The ID for which the description is to be updated.
     * @param newDescription The new description to be set.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateDescriptionById(String id, String newDescription) {
        try {
            String query = "UPDATE descrizioni SET DESCRIZIONE = ? WHERE ID = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, newDescription); // Imposta la nuova descrizione
                ps.setString(2, id); // Imposta l'id
                int rowsAffected = ps.executeUpdate(); // Esegue l'update
                return rowsAffected > 0; // Ritorna true se almeno una riga è stata aggiornata
            }
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
            return false;
        }
    }

    /**
     * Updates the room look associated with a given ID in the database.
     * @param id The ID for which the room look is to be updated.
     * @param newRoomLook The new room look to be set.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateRoomLookById(String id, String newRoomLook) {
        try {
            String query = "UPDATE descrizioni SET LOOK = ? WHERE ID = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, newRoomLook); // Imposta il nuovo look
                ps.setString(2, id); // Imposta l'id
                int rowsAffected = ps.executeUpdate(); // Esegue l'update
                return rowsAffected > 0; // Ritorna true se almeno una riga è stata aggiornata
            }
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
            return false;
        }
    }
    

    /**
     * Closes the database connection.
     * 
     */
    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }
}