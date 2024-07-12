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

public class Database {

    Properties dbprops;
    Connection conn;

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
}
