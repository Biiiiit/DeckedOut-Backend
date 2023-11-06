package strategy_card_game.Business.Game.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidGameException extends ResponseStatusException {
    public InvalidGameException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
