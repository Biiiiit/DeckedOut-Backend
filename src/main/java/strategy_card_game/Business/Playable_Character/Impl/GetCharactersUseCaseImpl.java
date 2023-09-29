package strategy_card_game.Business.Playable_Character.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Playable_Character.GetCharactersUseCase;
import strategy_card_game.Domain.Playable_Character.GetAllCharactersRequest;
import strategy_card_game.Domain.Playable_Character.GetAllCharactersResponse;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Persistance.CharacterRepository;
import strategy_card_game.Persistance.Entity.CharacterEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class GetCharactersUseCaseImpl implements GetCharactersUseCase {
    private final CharacterRepository characterRepository;

    @Override
    public GetAllCharactersResponse getCharacters(final GetAllCharactersRequest request) {
        List<CharacterEntity> results = characterRepository.findAll();

        final GetAllCharactersResponse response = new GetAllCharactersResponse();
        List<PlayableCharacter> characters = results
                .stream()
                .map(CharacterConverter::convert)
                .toList();
        response.setCharacters(characters);

        return response;
    }
}
