package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.HTTPClient.HTTPClientService;
import fr.lernejo.navy_battle.api.handlers.game.ApiHandlerGameFactory;
import fr.lernejo.navy_battle.api.handlers.ping.ApiHandlerPingFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    final private int port;
    final private HttpServer server;
    final private String host;

    public Server(int port) {
        this.port = port;
        this.host = "localhost";
        try {
            this.server = HttpServer.create(new InetSocketAddress(host, port), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPort() {
        return port;
    }

    public void connectAdversary(int port, String url) {
        HTTPClientService clientService = new HTTPClientService(url);
        try {
            System.out.println("Connecting to adversary at :" + url);
            clientService.post("/api/game/start", "{\"id\":\"1\", \"url\":\"http://localhost:" + port + "\", \"message\":\"hello\"}");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void startServer() {


        ExecutorService executor = Executors.newSingleThreadExecutor();
        server.setExecutor(executor);
        server.createContext("/ping", ApiHandlerPingFactory.GetPingHandler());
        server.createContext("/api/game/start", ApiHandlerGameFactory.GetGameStartHandler(port));
        server.createContext("/api/game/fire", ApiHandlerGameFactory.GetGameFireHandler(port));
        server.start();
        System.out.println("Server is ready on: http://" + host + ":" + port);
    }


}
