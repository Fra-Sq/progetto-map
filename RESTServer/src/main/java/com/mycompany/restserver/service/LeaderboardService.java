package com.mycompany.restserver.service;

import com.google.gson.Gson;
import com.mycompany.restserver.Database;
import com.mycompany.restserver.PlayerData;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("leaderboard")
public class LeaderboardService {

    private static final int MAX_LEADERS = 10;
    private static final Logger logger = Logger.getLogger(LeaderboardService.class.getName());
    private final Database database = new Database();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLeaderboard() {
        logger.log(Level.INFO, "Received request to get leaderboard");
        try {
            List<PlayerData> leaderboard = database.getTopPlayers(MAX_LEADERS);
            Gson gson = new Gson();
            String jsonString = gson.toJson(leaderboard);
            logger.log(Level.INFO, "Successfully retrieved leaderboard data");
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "SQL Exception occurred while getting leaderboard: {0}", ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving leaderboard: " + ex.getMessage()).build();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Unexpected Exception occurred: {0}", ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unexpected error: " + ex.getMessage()).build();
        }
    }

    @PUT
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlayer(String json) {
        logger.log(Level.INFO, "Received request to add player with data: {0}", json);
        Gson gson = new Gson();
        PlayerData player = gson.fromJson(json, PlayerData.class);

        try {
            database.insertInLeaderboard(player.getName(), player.getDate(), player.getTime());
            logger.log(Level.INFO, "Successfully added player: {0}", player.getName());
            return Response.ok().build();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "SQL Exception occurred while adding player: {0}", ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding player: " + ex.getMessage()).build();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Unexpected Exception occurred: {0}", ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unexpected error: " + ex.getMessage()).build();
        }
    }
}
