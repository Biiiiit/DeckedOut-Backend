package strategy_card_game.Business.Enemy.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidEnemyException extends ResponseStatusException {
    public InvalidEnemyException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
