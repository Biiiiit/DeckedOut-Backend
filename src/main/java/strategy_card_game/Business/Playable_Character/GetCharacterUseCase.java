package strategy_card_game.Business.Playable_Character;

import strategy_card_game.Domain.Playable_Character.PlayableCharacter;

import java.util.Optional;

public interface GetCharacterUseCase {
    Optional<PlayableCharacter> getCharacter(long characterId);
}
