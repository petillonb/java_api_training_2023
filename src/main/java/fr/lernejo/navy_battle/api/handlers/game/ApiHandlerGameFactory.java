package fr.lernejo.navy_battle.api.handlers.game;


import com.fasterxml.jackson.core.JsonParseException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.api.handlers.error.ErrorHandler;
import fr.lernejo.navy_battle.api.handlers.game.validators.GameStartValidator;
import fr.lernejo.navy_battle.api.handlers.utils.ResponseHandler;

import java.io.IOException;

public class ApiHandlerGameFactory {

    public static HttpHandler GetGameStartHandler() {

        return new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                try {
                    matchHTTPVerbToHandler(exchange);
                } catch (Exception e) {
                    ApiHandlerGameFactory.exceptionHandler(exchange, e);
                }
            }
        };

    }

    private static void matchHTTPVerbToHandler(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        switch (method) {
            case "POST":
                ApiHandlerGameFactory.handlePost(exchange);
                break;
            default:
                ErrorHandler.NotFound(exchange);
        }
    }

    private static void handlePost(HttpExchange exchange) throws IOException {
        GameStartValidator validator = new GameStartValidator();
        boolean valid = validator.validate(exchange.getRequestBody());
        if (!valid) {
            ErrorHandler.BadRequest(exchange, "json invalid or missing required properties");
        }
        String body = "toto";
        ResponseHandler.SendResponse(exchange, 202, body);


    }


    private static void exceptionHandler(HttpExchange exchange, Exception e) throws IOException {
        if (e instanceof JsonParseException) {
            ErrorHandler.BadRequest(exchange, e.getMessage());
        } else {
            System.err.println(e);
            ErrorHandler.InternalServerError(exchange);
        }
    }


}

