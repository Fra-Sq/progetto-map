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
/**
 *
 * @author pierpaolo
 */
public class Database {
    
    /**
     *
     */
    Properties dbprops;
    Connection conn;
    
    public Database() {
            try {
                //connessione senza parametri
                //Connection conn = DriverManager.getConnection("jdbc:h2:./resources/db");
                //connession con username e password
                //Connection conn = DriverManager.getConnection("jdbc:h2:./resources/db","user","1234");
                //connessione con oggetto Properties
                dbprops = new Properties();
                dbprops.setProperty("user", "user");
                dbprops.setProperty("password", "1234");
                conn = DriverManager.getConnection("jdbc:h2:./resources/db/database", dbprops);
                              
            } catch (SQLException ex) {
                System.err.println(ex.getSQLState() + ": " + ex.getMessage());
            }
    }
    
    public String getNameById(String id) {
        String name = "saba";
        try {
            String query = "SELECT NOME FROM descrizioni WHERE ID = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, id); // Imposta il parametro nella query preparata
                ResultSet rs = ps.executeQuery(); // Esegue la query e ottiene il risultato
                if (rs.next()) {
                    name = rs.getString("NOME"); // Recupera il nome dalla colonna "NOME" nel risultato
                }
                // Chiude la PreparedStatement
            } // Imposta il parametro nella query preparata
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
        return name; // Ritorna il nome recuperato, o null se non trovato o in caso di errore
    }
    
    public String getDescriptionById(String id) {
        String description = "saba";
        try {
            String query = "SELECT DESCRIZIONE FROM descrizioni WHERE ID = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, id); // Imposta il parametro nella query preparata
                ResultSet rs = ps.executeQuery(); // Esegue la query e ottiene il risultato
                if (rs.next()) {
                    description = rs.getString("DESCRIZIONE"); // Recupera il nome dalla colonna "NOME" nel risultato
                }
                // Chiude la PreparedStatement
            } // Imposta il parametro nella query preparata
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
        return description; // Ritorna il nome recuperato, o null se non trovato o in caso di errore
    }

    public String getRoomLookById(String id) {
        String roomLook = null;
        try {
            String query = "SELECT LOOK FROM descrizioni WHERE ID = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, id); // Imposta il parametro nella query preparata
                ResultSet rs = ps.executeQuery(); // Esegue la query e ottiene il risultato
                if (rs.next()) {
                    roomLook = rs.getString("LOOK"); // Recupera il nome dalla colonna "NOME" nel risultato
                }
                // Chiude la PreparedStatement
            } // Imposta il parametro nella query preparata
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
        return roomLook; // Ritorna il nome recuperato, o null se non trovato o in caso di errore
    }


}
