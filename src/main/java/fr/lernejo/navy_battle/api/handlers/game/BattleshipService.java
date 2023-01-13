package fr.lernejo.navy_battle.api.handlers.game;

import com.sun.net.httpserver.HttpExchange;
import fr.lernejo.navy_battle.api.handlers.error.ErrorHandler;
import fr.lernejo.navy_battle.api.handlers.game.validators.GameFireValidator;

import java.io.IOException;

public class BattleshipService {


    public static String handleGetGame(HttpExchange exchange, String cell, String consequences) throws IOException {
        String response = "{\"consequence\": \"" + consequences + "\", \"shipLeft\": true }";
        GameFireValidator validator = new GameFireValidator();
        boolean valid = validator.validate(response);
        if (!valid) {
            ErrorHandler.BadRequest(exchange, "Invalid response format");
        }
        return response;
    }
}
