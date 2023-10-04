package strategy_card_game.Business.Enemy.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EnemyAlreadyExistsException extends ResponseStatusException {
    public EnemyAlreadyExistsException(){super(HttpStatus.BAD_REQUEST, "Enemy_Already_Exists");}
}
