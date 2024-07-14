package com.mycompany.restserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {

    private Properties dbprops;
    private Connection conn;

    public Database() {
        System.out.println("Da.");
        try {
            dbprops = new Properties();
            dbprops.setProperty("user", "user");
            dbprops.setProperty("password", "1234");
            conn = DriverManager.getConnection("jdbc:h2:./resources/db/database", dbprops);
            System.out.println("Database connected successfully.");
        } catch (SQLException ex) {
            System.err.println("SQLState: " + ex.getSQLState() + " - " + ex.getMessage());
        }
    }

    public void insertInLeaderboard(String name, String date, String time) throws SQLException {
        // Query per creare la tabella se non esiste
        String createTableQuery = "CREATE TABLE IF NOT EXISTS leaderboard ("
                + "id INT PRIMARY KEY, "
                + "name VARCHAR(255), "
                + "date DATE, "
                + "time TIME)";

        // Query per ottenere l'ultimo ID
        String getLastIdQuery = "SELECT COALESCE(MAX(id), 0) AS last_id FROM leaderboard";

        // Query per inserire i dati nella tabella
        String insertQuery = "INSERT INTO leaderboard (id, name, date, time) VALUES (?, ?, ?, ?)";

        int newId;
        try (Statement stmt = conn.createStatement()) {
            // Creazione della tabella se non esiste
            stmt.execute(createTableQuery);
            System.out.println("Table 'leaderboard' created or already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
            throw e;
        }

        try (PreparedStatement ps = conn.prepareStatement(getLastIdQuery)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int lastId = rs.getInt("last_id");
                newId = lastId + 1; // Incrementa l'ID
            } else {
                newId = 1; // Inizia con 1 se non ci sono record
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving last ID: " + e.getMessage());
            throw e;
        }

        try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
            ps.setInt(1, newId);
            ps.setString(2, name);
            ps.setDate(3, java.sql.Date.valueOf(date));
            ps.setTime(4, java.sql.Time.valueOf(time));

            ps.executeUpdate();
            System.out.println("Data inserted successfully into 'leaderboard'.");
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
            throw e;
        }
    }


    public PlayerData getFromLeaderboard(String id) throws SQLException {
        String query = "SELECT id, name, date, time FROM leaderboard WHERE id = ?";
        PlayerData player = null;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String playerName = rs.getString("name");
                    java.sql.Date playerDate = rs.getDate("date");
                    java.sql.Time playerTime = rs.getTime("time");

                    player = new PlayerData(playerName, playerDate.toString(), playerTime.toString());
                }
            }
        }

        return player;
    }

    public List<PlayerData> getTopPlayers(int maxPlayers) throws SQLException {
        String query = "SELECT id, name, date, time FROM leaderboard ORDER BY time LIMIT ?";
        List<PlayerData> players = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, maxPlayers);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String playerName = rs.getString("name");
                    java.sql.Date playerDate = rs.getDate("date");
                    java.sql.Time playerTime = rs.getTime("time");

                    players.add(new PlayerData(playerName, playerDate.toString(), playerTime.toString()));
                }
            }
        }

        return players;
    }
    
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
