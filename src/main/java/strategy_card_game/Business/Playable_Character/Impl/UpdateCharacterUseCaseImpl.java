package strategy_card_game.Business.Playable_Character.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Playable_Character.Exception.InvalidCharacterException;
import strategy_card_game.Business.Playable_Character.UpdateCharacterUseCase;
import strategy_card_game.Domain.Playable_Character.UpdateCharacterRequest;
import strategy_card_game.Persistance.CharacterRepository;
import strategy_card_game.Persistance.Entity.CharacterEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateCharacterUseCaseImpl implements UpdateCharacterUseCase {
    private final CharacterRepository characterRepository;

    @Override
    public void updateCharacter(UpdateCharacterRequest request) {
        Optional<CharacterEntity> characterOptional = characterRepository.findById(request.getId());
        if (characterOptional.isEmpty()) {
            throw new InvalidCharacterException("CHARACTER_ID_INVALID");
        }

        CharacterEntity character = characterOptional.get();
        updateFields(request, character);
    }

    private void updateFields(UpdateCharacterRequest request, CharacterEntity character) {
        character.setName(request.getName());
        character.setDescription(request.getDescription());
        character.setHealth(request.getHealth());
        character.setAmmo(request.getAmmo());
        character.setStartingDeck(request.getStartingDeck());
        character.setSprite(request.getSprite());

        characterRepository.save(character);
    }
}
