package fr.lernejo.navy_battle.api.handlers.game.validators;

import fr.lernejo.navy_battle.api.handlers.utils.BodyValidator;

import java.io.IOException;
import java.io.InputStream;

public class GameStartValidator extends BodyValidator {

    public GameStartValidator() {
        super("./src/resources/schema/StartSchema.json");
    }

    public boolean validate(InputStream body) throws IOException {

        return this._validate(body);

    }

}
