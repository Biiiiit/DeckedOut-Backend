package strategy_card_game.Business.Playable_Character.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Playable_Character.CreateCharacterUseCase;
import strategy_card_game.Business.Playable_Character.Exception.CharacterAlreadyExistsException;
import strategy_card_game.Domain.Playable_Character.CreateCharacterRequest;
import strategy_card_game.Domain.Playable_Character.CreateCharacterResponse;
import strategy_card_game.Persistance.CharacterRepository;
import strategy_card_game.Persistance.Entity.CharacterEntity;

@Service
@AllArgsConstructor
public class CreateCharacterUseCaseImpl implements CreateCharacterUseCase {
    private final CharacterRepository characterRepository;
    @Override
    public CreateCharacterResponse createCharacter(CreateCharacterRequest request){
        if (characterRepository.existsByName(request.getName())){
            throw new CharacterAlreadyExistsException();
        }

        CharacterEntity savedCharacter = saveNewCharacter(request);

        return CreateCharacterResponse.builder()
                .characterId(savedCharacter.getId())
                .build();
    }
    private CharacterEntity saveNewCharacter(CreateCharacterRequest request) {

        CharacterEntity newCharacter = CharacterEntity.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .health(request.getHealth())
                .ammo(request.getAmmo())
                .startingDeck(request.getStartingDeck())
                .sprite(request.getSprite())
                .build();
        return characterRepository.save(newCharacter);
    }
}
