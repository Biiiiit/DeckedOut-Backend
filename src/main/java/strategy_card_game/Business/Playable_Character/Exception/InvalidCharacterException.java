package strategy_card_game.Business.Playable_Character.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCharacterException extends ResponseStatusException {
    public InvalidCharacterException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
