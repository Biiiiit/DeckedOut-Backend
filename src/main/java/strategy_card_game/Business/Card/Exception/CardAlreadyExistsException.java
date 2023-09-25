package strategy_card_game.Business.Card.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CardAlreadyExistsException extends ResponseStatusException {
    public CardAlreadyExistsException(){super(HttpStatus.BAD_REQUEST, "Card_Already_Exists");}
}
