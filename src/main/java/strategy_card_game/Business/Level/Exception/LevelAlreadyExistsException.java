package strategy_card_game.Business.Level.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LevelAlreadyExistsException extends ResponseStatusException {
    public LevelAlreadyExistsException(){super(HttpStatus.BAD_REQUEST, "Level_Already_Exists");}
}
