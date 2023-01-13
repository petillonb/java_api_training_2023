package fr.lernejo.navy_battle.api.handlers.game;


import com.fasterxml.jackson.core.JsonParseException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.api.handlers.error.ErrorHandler;
import fr.lernejo.navy_battle.api.handlers.game.validators.GameFireValidator;
import fr.lernejo.navy_battle.api.handlers.game.validators.GameStartValidator;
import fr.lernejo.navy_battle.api.handlers.utils.QueryParamsMapper;
import fr.lernejo.navy_battle.api.handlers.utils.ResponseHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ApiHandlerGameFactory {

    private int port;

    public static HttpHandler GetGameStartHandler(int port) {

        return new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                try {
                    matchHTTPVerbToHandler(exchange, port);
                } catch (Exception e) {
                    ApiHandlerGameFactory.exceptionHandler(exchange, e);
                }
            }
        };

    }

    private static void matchHTTPVerbToHandler(HttpExchange exchange, int port) throws IOException {
        String method = exchange.getRequestMethod();
        switch (method) {
            case "GET":
                ApiHandlerGameFactory.handleGet(exchange);
                break;
            case "POST":
                ApiHandlerGameFactory.handlePost(exchange, port);
                break;
            default:
                ErrorHandler.NotFound(exchange);
        }
    }

    private static void handleGet(HttpExchange exchange) throws IOException {

        String query = exchange.getRequestURI().getQuery();
        Map<String, String> queryParams = QueryParamsMapper.toMap(query);
        String cell = queryParams.get("cell");
        if (cell == null) {
            ErrorHandler.BadRequest(exchange, "Missing \"cell\" query param");
        }

        String response = "{\"consequence\":\"sunk\", \"shipLeft\": true }";
        GameFireValidator validator = new GameFireValidator();
        boolean valid = validator.validate(response);
        if (!valid) {
            ErrorHandler.BadRequest(exchange, "Invalid response format");
        }
        System.out.println("Received fire on cell :" + cell);
        ResponseHandler.SendResponse(exchange, 200, response);
    }

    private static void handlePost(HttpExchange exchange, int port) throws IOException {
        GameStartValidator validator = new GameStartValidator();
        InputStream body = exchange.getRequestBody();
        boolean valid = validator.validate(body);
        if (!valid) {
            ErrorHandler.BadRequest(exchange, "json invalid or missing required properties");
        }
        System.out.println("Starting game with player");
        String response = "{\"id\":\"1\", \"url\":\"http://localhost:" + port + "\", \"message\":\"hello\"}";
        ResponseHandler.SendResponse(exchange, 202, response);


    }


    private static void exceptionHandler(HttpExchange exchange, Exception e) throws IOException {
        if (e instanceof JsonParseException) {
            ErrorHandler.BadRequest(exchange, e.getMessage());
        } else {
            System.err.println(e);
            ErrorHandler.InternalServerError(exchange);
        }
    }


    public static HttpHandler GetGameFireHandler(int port) {

        return new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                try {
                    matchHTTPVerbToHandler(exchange, port);
                } catch (Exception e) {
                    ApiHandlerGameFactory.exceptionHandler(exchange, e);
                }
            }
        };

    }
}

