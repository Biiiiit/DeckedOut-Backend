package strategy_card_game.Character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy_card_game.Business.Playable_Character.CreateCharacterUseCase;
import strategy_card_game.Business.Playable_Character.Exception.InvalidCharacterException;
import strategy_card_game.Business.Playable_Character.GetCharactersUseCase;
import strategy_card_game.Business.Playable_Character.Impl.CreateCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.GetCharactersUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.UpdateCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.UpdateCharacterUseCase;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Playable_Character.*;
import strategy_card_game.Persistance.CharacterRepository;
import strategy_card_game.Persistance.Impl.FakeCharacterRepositoryImpl;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateCharacterUseCaseImplTest {
    private GetCharactersUseCase getCharactersUseCase;
    private CreateCharacterUseCase createCharacterUseCase;
    private UpdateCharacterUseCase updateCharacterUseCase;
    private CharacterRepository fakeCharacterRepository;
    @BeforeEach
    public void setUp() {
        fakeCharacterRepository = new FakeCharacterRepositoryImpl();
        getCharactersUseCase = new GetCharactersUseCaseImpl(fakeCharacterRepository);
        createCharacterUseCase = new CreateCharacterUseCaseImpl(fakeCharacterRepository);
        updateCharacterUseCase = new UpdateCharacterUseCaseImpl(fakeCharacterRepository);
    }

    @Test
    public void testUpdateCharacter() {
        List<Card> deck = null;
        Image Image = null;
        CreateCharacterRequest characterRequest = new CreateCharacterRequest(null,"Character", "Description", 50, 0, deck, Image);
        CreateCharacterResponse createResponse = createCharacterUseCase.createCharacter(characterRequest);

        List<PlayableCharacter> charactersBeforeUpdate = getCharactersUseCase.getCharacters(new GetAllCharactersRequest()).getCharacters();
        assertTrue(charactersBeforeUpdate.stream().anyMatch(character -> character.getId().equals(createResponse.getCharacterId())));

        // Update the card
        UpdateCharacterRequest updateRequest = new UpdateCharacterRequest(createResponse.getCharacterId(), "UpdatedCharacter", "Description", 5, 2, deck, Image);
        updateCharacterUseCase.updateCharacter(updateRequest);

        List<PlayableCharacter> charactersAfterUpdate = getCharactersUseCase.getCharacters(new GetAllCharactersRequest()).getCharacters();
        assertTrue(charactersAfterUpdate.stream().anyMatch(character -> character.getId().equals(createResponse.getCharacterId())));
        PlayableCharacter updatedCharacter = charactersAfterUpdate.stream()
                .filter(character -> character.getId().equals(createResponse.getCharacterId()))
                .findFirst()
                .orElse(null);

        assertEquals("UpdatedCharacter", updatedCharacter.getName());
        assertEquals("Description", updatedCharacter.getDescription());
        assertEquals(5, updatedCharacter.getHealth());
        assertEquals(2, updatedCharacter.getAmmo());
        assertEquals(deck, updatedCharacter.getStartingDeck());
        assertEquals(Image, updatedCharacter.getSprite());
    }

    @Test
    public void testUpdateInvalidCharacter() {
        List<Card> deck = null;
        Image Image = null;
        UpdateCharacterRequest updateRequest = new UpdateCharacterRequest(999L, "UpdatedCharacter", "Description", 5, 2, deck, Image);

        assertThrows(InvalidCharacterException.class, () -> updateCharacterUseCase.updateCharacter(updateRequest));
    }
}
