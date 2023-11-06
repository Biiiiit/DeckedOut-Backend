package strategy_card_game.Business.Game.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GameAlreadyExistsException extends ResponseStatusException {
    public GameAlreadyExistsException(){super(HttpStatus.BAD_REQUEST, "Game_Already_Exists");}
}
