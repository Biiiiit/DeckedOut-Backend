package strategy_card_game.Business.Area.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidAreaException extends ResponseStatusException {
    public InvalidAreaException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
