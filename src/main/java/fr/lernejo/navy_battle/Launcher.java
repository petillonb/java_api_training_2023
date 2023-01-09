package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.api.handlers.game.ApiHandlerGameFactory;
import fr.lernejo.navy_battle.api.handlers.ping.ApiHandlerPingFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Launcher {

    public static void main(String[] argv) {

        HttpServer server;
        int port = 9876;
        String host = "localhost";
        try {
            server = HttpServer.create(new InetSocketAddress(host, port), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        server.setExecutor(executor);
        server.createContext("/ping", ApiHandlerPingFactory.GetPingHandler());
        server.createContext("/api/game/start", ApiHandlerGameFactory.GetGameStartHandler());
        server.start();

        System.out.println("Server is ready on: http://" + host + ":" + port);


    }
}
