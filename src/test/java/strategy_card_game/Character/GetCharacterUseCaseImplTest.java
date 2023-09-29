package strategy_card_game.Character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy_card_game.Business.Playable_Character.CreateCharacterUseCase;
import strategy_card_game.Business.Playable_Character.GetCharacterUseCase;
import strategy_card_game.Business.Playable_Character.Impl.CreateCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.GetCharacterUseCaseImpl;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Playable_Character.CreateCharacterRequest;
import strategy_card_game.Domain.Playable_Character.CreateCharacterResponse;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Persistance.CharacterRepository;
import strategy_card_game.Persistance.Impl.FakeCharacterRepositoryImpl;

import java.awt.*;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetCharacterUseCaseImplTest {
    private GetCharacterUseCase getCharacterUseCase;
    private CreateCharacterUseCase createCharacterUseCase;
    private CharacterRepository fakeCharacterRepository;
    @BeforeEach
    public void setUp() {
        fakeCharacterRepository = new FakeCharacterRepositoryImpl(); // Instantiate your fake repository
        getCharacterUseCase = new GetCharacterUseCaseImpl(fakeCharacterRepository);
        createCharacterUseCase = new CreateCharacterUseCaseImpl(fakeCharacterRepository);
    }

    @Test
    public void testGetCharacter() {
        List<Card> deck = null;
        Image Image = null;
        CreateCharacterRequest characterRequest = new CreateCharacterRequest(null,"Character", "Description", 50, 0, deck, Image);
        CreateCharacterResponse createResponse = createCharacterUseCase.createCharacter(characterRequest);

        // Get the created card using getCardUseCase
        Optional<PlayableCharacter> optionalCharacter = getCharacterUseCase.getCharacter(createResponse.getCharacterId());

        // Verify that the card is present and has the correct details
        assertTrue(optionalCharacter.isPresent());
        PlayableCharacter character = optionalCharacter.get();
        assertEquals("Character", character.getName());
        assertEquals("Description", character.getDescription());
        assertEquals(50, character.getHealth());
        assertEquals(0, character.getAmmo());
        assertEquals(deck, character.getStartingDeck());
        assertEquals(Image, character.getSprite());
    }

    @Test
    public void testGetNonExistentCharacter() {
        // Attempt to get a card with an invalid ID
        Optional<PlayableCharacter> optionalCharacter = getCharacterUseCase.getCharacter(999L);

        // Verify that the optional is empty as the card doesn't exist
        assertTrue(optionalCharacter.isEmpty());
    }
}
