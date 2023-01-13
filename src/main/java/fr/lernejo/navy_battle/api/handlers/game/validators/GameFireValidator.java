package fr.lernejo.navy_battle.api.handlers.game.validators;

import fr.lernejo.navy_battle.api.handlers.utils.BodyValidator;

import java.io.IOException;
import java.io.InputStream;

public class GameFireValidator extends BodyValidator {

    public GameFireValidator() {

        super("./src/resources/schema/FireSchema.json");
    }

    public boolean validate(InputStream body) throws IOException {

        return this._validate(body);

    }

    public boolean validate(String body) throws IOException {

        return this._validate(body);

    }

}
