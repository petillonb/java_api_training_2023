package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.api.handlers.game.ApiHandlerGameFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.*;


public class Launcher {

    public static void main(String[] argv){

        HttpServer server;
        int port = 9876;
        String host = "localhost";
        try {
            server = HttpServer.create(new InetSocketAddress(host,port),0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpHandler handler = new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                String body = "OK";
                exchange.sendResponseHeaders(200, body.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(body.length());
                }
            }
        };
        ExecutorService executor = Executors.newSingleThreadExecutor();
        server.setExecutor(executor);
        server.createContext("/ping",handler);
        server.createContext("/api/game/start", ApiHandlerGameFactory.GetGameStartHandler());
        server.start();

        System.out.println("Server is ready on: http://" + host + ":" + port );




    }
}
