package strategy_card_game.Business.Playable_Character;

import strategy_card_game.Domain.Playable_Character.UpdateCharacterRequest;

public interface UpdateCharacterUseCase {
    void updateCharacter(UpdateCharacterRequest request);
}
