/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

/*
 * Copyright (C) 2020 pierpaolo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author pierpaolo
 */
public class Database {
    
    /**
     *
     */
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS descrizioni (ID INT PRIMARY KEY, NOME VARCHAR(50), DESCRIZIONE VARCHAR(1024), TIPO VARCHAR(10))";

    public static void database() {
        Scanner scanner = new Scanner(System.in);   
        try {
            //connessione senza parametri
            //Connection conn = DriverManager.getConnection("jdbc:h2:./resources/db");
            //connession con username e password
            //Connection conn = DriverManager.getConnection("jdbc:h2:./resources/db","user","1234");
            //connessione con oggetto Properties
            Properties dbprops = new Properties();
            dbprops.setProperty("user", "user");
            dbprops.setProperty("password", "1234");
            Connection conn = DriverManager.getConnection("jdbc:h2:./resources/db/database", dbprops);
            Statement stm = conn.createStatement();
            stm.executeUpdate(CREATE_TABLE);
            stm.close();
            
            
            int id=0, scelta=1;
            String descrizione, nome, tipo;
            
            while(scelta==1)
            {
                id++;
                System.out.println("NOME");
                nome=scanner.next();
                System.out.println("DESCRIZIONE");
                descrizione=scanner.next();
                System.out.println("TIPO");
                tipo=scanner.next();
                
                PreparedStatement pstm = conn.prepareStatement("INSERT INTO descrizioni VALUES (?,?,?,?)");
                pstm.setInt(1, id);
                pstm.setString(2, nome);
                pstm.setString(3, descrizione);
                pstm.setString(4, tipo);
                pstm.executeUpdate();
                pstm.close();
                
                System.out.println("1 continua");
                scelta= scanner.nextInt();
            }
            
            stm = conn.createStatement();
            System.out.println("SQL Query");
            System.out.println("=======================");
            ResultSet rs = stm.executeQuery("SELECT * FROM descrizioni");
            while (rs.next()) {
                    System.out.println(rs.getInt("ID") + ", " + rs.getString("NOME") + ", " + rs.getString("DESCRIZIONE") + ", " + rs.getString("TIPO"));
            }
            rs.close();
            stm.close();
            
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    
        scanner.close();
    }

}
