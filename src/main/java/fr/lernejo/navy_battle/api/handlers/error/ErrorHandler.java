package fr.lernejo.navy_battle.api.handlers.error;

import com.sun.net.httpserver.HttpExchange;
import fr.lernejo.navy_battle.api.handlers.utils.ResponseHandler;

import java.io.IOException;

public class ErrorHandler {
    public static void NotFound(HttpExchange exchange) throws IOException {

        String body = "Not Found";
        ResponseHandler.SendResponse(exchange, 404, body);
    }

    public static void BadRequest(HttpExchange exchange, String message) throws IOException {

        String body = "Bad request : " + message;
        ResponseHandler.SendResponse(exchange, 400, body);
    }

    public static void InternalServerError(HttpExchange exchange) throws IOException {

        String body = "Internal Server Error";
        ResponseHandler.SendResponse(exchange, 500, body);
    }
}
