package strategy_card_game.Business.Playable_Character;

import strategy_card_game.Domain.Playable_Character.CreateCharacterRequest;
import strategy_card_game.Domain.Playable_Character.CreateCharacterResponse;

public interface CreateCharacterUseCase {
    CreateCharacterResponse createCharacter(CreateCharacterRequest request);
}
