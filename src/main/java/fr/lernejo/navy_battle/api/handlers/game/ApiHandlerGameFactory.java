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
                    String method = exchange.getRequestMethod();
                    switch (method) {

                        case "POST":
                            GameStartValidator validator = new GameStartValidator();
                            boolean valid = validator.validate(exchange.getRequestBody());
                            if (!valid) {
                                ErrorHandler.BadRequest(exchange, "json invalid or missing required properties");
                            }
                            ApiHandlerGameFactory.HandlePost(exchange);


                            break;

                        default:
                            ErrorHandler.NotFound(exchange);

                    }
                } catch (Exception e) {
                    if (e instanceof JsonParseException) {
                        ErrorHandler.BadRequest(exchange, e.getMessage());
                    } else {
                        System.err.println(e);
                        ErrorHandler.InternalServerError(exchange);


                    }
                }


            }

        };

    }

    private static void HandlePost(HttpExchange exchange) throws IOException {
        System.out.println("bonjour");
        String body = "toto";
        ResponseHandler.SendResponse(exchange, 202, body);


    }
}
