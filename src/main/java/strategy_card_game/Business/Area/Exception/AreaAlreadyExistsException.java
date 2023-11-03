package strategy_card_game.Business.Area.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AreaAlreadyExistsException extends ResponseStatusException {
    public AreaAlreadyExistsException(){super(HttpStatus.BAD_REQUEST, "Area_Already_Exists");}
}
