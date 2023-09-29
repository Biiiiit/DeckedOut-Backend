package strategy_card_game.Business.Playable_Character;

import strategy_card_game.Domain.Playable_Character.GetAllCharactersRequest;
import strategy_card_game.Domain.Playable_Character.GetAllCharactersResponse;

public interface GetCharactersUseCase {
    GetAllCharactersResponse getCharacters(GetAllCharactersRequest request);
}
