package strategy_card_game.Business.Level.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidLevelException extends ResponseStatusException {
    public InvalidLevelException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
