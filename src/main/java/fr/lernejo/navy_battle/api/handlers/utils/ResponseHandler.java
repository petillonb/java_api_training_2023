package fr.lernejo.navy_battle.api.handlers.utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseHandler {
    public static void SendResponse(HttpExchange exchange, int statusCode, String body) throws IOException {

        exchange.sendResponseHeaders(statusCode,body.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
            os.close();
        }
    }
}
