package strategy_card_game.Business.Playable_Character.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Playable_Character.DeleteCharacterUseCase;
import strategy_card_game.Persistance.CharacterRepository;

@Service
@AllArgsConstructor
public class DeleteCharacterUseCaseImpl implements DeleteCharacterUseCase {
    private final CharacterRepository characterRepository;

    @Override
    public void deleteCharacter(long characterId) {
        this.characterRepository.deleteById(characterId);
    }
}
