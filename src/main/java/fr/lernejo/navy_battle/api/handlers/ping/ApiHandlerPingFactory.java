package fr.lernejo.navy_battle.api.handlers.ping;


import com.fasterxml.jackson.core.JsonParseException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.api.handlers.error.ErrorHandler;
import fr.lernejo.navy_battle.api.handlers.utils.ResponseHandler;

import java.io.IOException;
import java.io.OutputStream;

public class ApiHandlerPingFactory {


    public static HttpHandler GetPingHandler() {

        return new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                ;
                try (OutputStream os = exchange.getResponseBody()) {
                    ApiHandlerPingFactory.HandleGet(exchange);
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

    private static void HandleGet(HttpExchange exchange) throws IOException {
        String body = "ping";
        ResponseHandler.SendResponse(exchange, 200, body);


    }
}
