package strategy_card_game.Character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy_card_game.Business.Playable_Character.CreateCharacterUseCase;
import strategy_card_game.Business.Playable_Character.DeleteCharacterUseCase;
import strategy_card_game.Business.Playable_Character.GetCharactersUseCase;
import strategy_card_game.Business.Playable_Character.Impl.CreateCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.DeleteCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.GetCharactersUseCaseImpl;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Playable_Character.CreateCharacterRequest;
import strategy_card_game.Domain.Playable_Character.CreateCharacterResponse;
import strategy_card_game.Domain.Playable_Character.GetAllCharactersRequest;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Persistance.CharacterRepository;
import strategy_card_game.Persistance.Impl.FakeCharacterRepositoryImpl;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCharacterUseCaseImplTest {
    private GetCharactersUseCase getCharactersUseCase;
    private CreateCharacterUseCase createCharacterUseCase;
    private DeleteCharacterUseCase deleteCharacterUseCase;
    private CharacterRepository fakeCharacterRepository;
    @BeforeEach
    public void setUp() {
        fakeCharacterRepository = new FakeCharacterRepositoryImpl();
        getCharactersUseCase = new GetCharactersUseCaseImpl(fakeCharacterRepository);
        createCharacterUseCase = new CreateCharacterUseCaseImpl(fakeCharacterRepository);
        deleteCharacterUseCase = new DeleteCharacterUseCaseImpl(fakeCharacterRepository);
    }

    @Test
    public void testDeleteCharacter() {
        List<Card> deck = null;
        Image Image = null;
        CreateCharacterRequest characterRequest = new CreateCharacterRequest(null,"CharacterName", "Description", 50, 0, deck, Image);
        CreateCharacterResponse createResponse = createCharacterUseCase.createCharacter(characterRequest);

        // Get all cards and verify that the card is present
        List<PlayableCharacter> characterBeforeDelete = getCharactersUseCase.getCharacters(new GetAllCharactersRequest()).getCharacters();
        assertTrue(characterBeforeDelete.stream().anyMatch(character -> character.getId().equals(createResponse.getCharacterId())));

        // Delete the card
        deleteCharacterUseCase.deleteCharacter(createResponse.getCharacterId());

        // Get all cards again and verify that the card is no longer present
        List<PlayableCharacter> characterAfterDelete = getCharactersUseCase.getCharacters(new GetAllCharactersRequest()).getCharacters();
        assertFalse(characterAfterDelete.stream().anyMatch(character -> character.getId().equals(createResponse.getCharacterId())));
    }
}
