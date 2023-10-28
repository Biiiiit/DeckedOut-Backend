package strategy_card_game.Business.Playable_Character.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Playable_Character.GetCharacterUseCase;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Persistance.CharacterRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetCharacterUseCaseImpl implements GetCharacterUseCase {
    private final CharacterRepository characterRepository;
    @Override
    public Optional<PlayableCharacter> getCharacter(long characterID) {
        return characterRepository.findById(characterID).map(CharacterConverter::convert);
    }
}
