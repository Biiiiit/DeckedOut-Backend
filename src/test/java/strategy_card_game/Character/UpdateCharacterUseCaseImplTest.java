package strategy_card_game.Character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Playable_Character.Exception.InvalidCharacterException;
import strategy_card_game.Business.Playable_Character.Impl.CreateCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.GetCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.UpdateCharacterUseCaseImpl;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Card.TypeOfCard;
import strategy_card_game.Domain.Playable_Character.CreateCharacterRequest;
import strategy_card_game.Domain.Playable_Character.CreateCharacterResponse;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Domain.Playable_Character.UpdateCharacterRequest;
import strategy_card_game.Persistance.CharacterRepository;
import strategy_card_game.Persistance.Entity.CardEntity;
import strategy_card_game.Persistance.Entity.CharacterEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateCharacterUseCaseImplTest {
    @InjectMocks
    private UpdateCharacterUseCaseImpl updateCharacterUseCase;
    @Mock
    private GetCharacterUseCaseImpl getCharacterUseCase;
    @Mock
    private CreateCharacterUseCaseImpl createCharacterUseCase;

    @Mock
    private CharacterRepository characterRepository;

    @BeforeEach
    public void setUp() {
        // Initialize the mocks and inject them into the use cases
        createCharacterUseCase = new CreateCharacterUseCaseImpl(characterRepository);
        updateCharacterUseCase = new UpdateCharacterUseCaseImpl(characterRepository);
        getCharacterUseCase = new GetCharacterUseCaseImpl(characterRepository);
    }

    @Test
    public void testUpdateCharacter() {
        List<CardEntity> deck = new ArrayList<>();
        deck.add(new CardEntity(1L, "CardName", TypeOfCard.Atk, 10, 0, 0));
        List<Card> deck2 = new ArrayList<>();
        deck2.add(new Card(1L, "CardName", TypeOfCard.Atk, 10, 0, 0));
        byte[] Image = new byte[0];
        CreateCharacterRequest characterRequest = new CreateCharacterRequest(1L, "Character", "Description", 50, 0, deck2, Image);

        CharacterEntity character = new CharacterEntity(1L, "Character", "Description", 50, 0, deck, Image);
        when(characterRepository.save(Mockito.any(CharacterEntity.class))).thenReturn(character);

        // Call the createCardUseCase to create the character
        CreateCharacterResponse createResponse = createCharacterUseCase.createCharacter(characterRequest);

        // Mock the behavior of cardRepository.findById to return the created character
        when(characterRepository.findById(eq(createResponse.getCharacterId()))).thenReturn(Optional.of(character));

        // Create an updated character
        UpdateCharacterRequest updatedCharacter = new UpdateCharacterRequest(createResponse.getCharacterId(), "UpdatedCharacter", "UpdatedDescription", 40, 3, deck2, Image);

        // Call the updateCharacter method
        updateCharacterUseCase.updateCharacter(updatedCharacter);

        // Verify that the character has been updated
        Optional<PlayableCharacter> optionalCharacter = getCharacterUseCase.getCharacter(createResponse.getCharacterId());
        assertTrue(optionalCharacter.isPresent());
        PlayableCharacter playableCharacter = optionalCharacter.get();
        assertEquals("UpdatedCharacter", playableCharacter.getName());
        assertEquals("UpdatedDescription", playableCharacter.getDescription());
        assertEquals(40, playableCharacter.getHealth());
        assertEquals(deck2, playableCharacter.getStartingDeck());
        assertEquals(Image, playableCharacter.getSprite());
    }

    @Test
    public void testUpdateInvalidCharacter() {
        List<Card> deck = null;
        Image Image = null;
        UpdateCharacterRequest updateRequest = new UpdateCharacterRequest(999L, "UpdatedCharacter", "Description", 5, 2, deck, new byte[0]);

        // Verify that an InvalidCharacterException is thrown when updating an invalid character
        assertThrows(InvalidCharacterException.class, () -> updateCharacterUseCase.updateCharacter(updateRequest));
    }
}
