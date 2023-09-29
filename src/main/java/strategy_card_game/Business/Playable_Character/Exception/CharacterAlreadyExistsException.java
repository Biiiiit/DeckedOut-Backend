package strategy_card_game.Business.Playable_Character.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CharacterAlreadyExistsException extends ResponseStatusException {
    public CharacterAlreadyExistsException(){super(HttpStatus.BAD_REQUEST, "Character_Already_Exists");}
}
