package com.mycompany.restserver;

import com.mycompany.restserver.service.LeaderboardService;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class RESTServer {
    
    public static void startServer() {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(4321).build();
        ResourceConfig config = new ResourceConfig(LeaderboardService.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down server...");
            server.shutdown();
        }));

        try {
            server.start();
            System.out.println(String.format("""
                                             Jersey app started with WADL available at %sapplication.wadl
                                             Hit enter to stop it...""", baseUri));
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(RESTServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            server.shutdown();
        }
    }

    public static void main(String[] args) {
        startServer();
    }
}
