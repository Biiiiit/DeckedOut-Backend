package strategy_card_game.Character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Playable_Character.Impl.CreateCharacterUseCaseImpl;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Card.TypeOfCard;
import strategy_card_game.Domain.Playable_Character.CreateCharacterRequest;
import strategy_card_game.Domain.Playable_Character.CreateCharacterResponse;
import strategy_card_game.Persistance.CharacterRepository;
import strategy_card_game.Persistance.Entity.CharacterEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCharacterUseCaseImplTest {
    @InjectMocks
    private CreateCharacterUseCaseImpl createCharacterUseCase;

    @Mock
    private CharacterRepository characterRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCreateCharacterSuccess() {
        List<Card> deck = new ArrayList<>();
        deck.add(new Card(1L, "CardName", TypeOfCard.Atk, 10, 0, 0));
        Image image = null;
        CreateCharacterRequest request = new CreateCharacterRequest(1L, "CharacterName", "Description", 50, 0, deck, new byte[0]);

        // Mock the behavior of characterRepository.save to return a CharacterEntity with an ID.
        CharacterEntity savedCharacter = new CharacterEntity(1L, "CharacterName", "Description", 50, 0, new ArrayList<>(), new byte[0]);
        when(characterRepository.save(Mockito.any(CharacterEntity.class))).thenReturn(savedCharacter);

        CreateCharacterResponse response = createCharacterUseCase.createCharacter(request);

        assertNotNull(response);
        assertNotNull(response.getCharacterId());
    }

    @Test
    public void testCreateCharacterFailureCharacterAlreadyExists() {
        List<Card> deck = new ArrayList<>();
        deck.add(new Card(1L, "CardName", TypeOfCard.Atk, 10, 0, 0));
        Image image = null;

        // Mock the behavior of characterRepository.findByCharacterName to return an existing character.
        CharacterEntity existingCharacter = new CharacterEntity(1L, "CharacterName", "Description", 50, 0, new ArrayList<>(), new byte[0]);

        CreateCharacterRequest request = new CreateCharacterRequest(1L, "CharacterName", "Description", 50, 0, deck, new byte[0]);

        assertThrows(Exception.class, () -> createCharacterUseCase.createCharacter(request));
    }
}
