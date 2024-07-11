package di.uniba.map.b.adventure.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RESTClient {

    private final Client client;
    private final WebTarget target;
    private final Gson gson;

    public RESTClient() {
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:4321/leaderboard");
        this.gson = new Gson();
    }

    public void addPlayer(PlayerData player) {
        String jsonPlayer = gson.toJson(player);
        Response response = target.path("add").request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(jsonPlayer, MediaType.APPLICATION_JSON));
        System.out.println("PUT add player response:");
        System.out.println(response);
        System.out.println(response.readEntity(String.class));
    }

    public String getLeaderboard() {
        StringBuilder leaderboardString = new StringBuilder();
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        String jsonResponse = response.readEntity(String.class);
        JsonArray leaderboard = gson.fromJson(jsonResponse, JsonArray.class);

        
        for (int i = 0; i < leaderboard.size(); i++) {
            JsonObject player = leaderboard.get(i).getAsJsonObject();
            String name = player.get("name").getAsString();
            String date = player.get("date").getAsString();
            String time = player.get("time").getAsString();
            leaderboardString.append("Name: ").append(name).append(", Date: ").append(date).append(", Time: ").append(time).append("\n");
        }
        return leaderboardString.toString();
    }
}
