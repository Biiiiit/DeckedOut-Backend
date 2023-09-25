package strategy_card_game.Business.User.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {
    public UserAlreadyExistsException(){super(HttpStatus.BAD_REQUEST, "User_Already_Exists");}
}
