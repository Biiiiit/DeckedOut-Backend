package strategy_card_game.Character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Playable_Character.Impl.CreateCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.GetCharacterUseCaseImpl;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Card.TypeOfCard;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Persistance.CharacterRepository;
import strategy_card_game.Persistance.Entity.CardEntity;
import strategy_card_game.Persistance.Entity.CharacterEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCharacterUseCaseImplTest {
    @InjectMocks
    private GetCharacterUseCaseImpl getCharacterUseCase;

    @Mock
    private CreateCharacterUseCaseImpl createCharacterUseCase;

    @Mock
    private CharacterRepository characterRepository;

    @BeforeEach
    public void setUp() {
        // No need to create instances here since Mockito takes care of them.
    }

    @Test
    public void testGetAllCards() {
        List<CardEntity> deck = new ArrayList<>();
        deck.add(new CardEntity(1L, "CardName", TypeOfCard.Atk, 10, 0, 0));
        byte[] Image = new byte[0];

        CharacterEntity characterEntity = new CharacterEntity(1L, "Character1", "Description", 50, 0, deck, Image);

        when(characterRepository.findById(1L)).thenReturn(Optional.of(characterEntity));

        Optional<PlayableCharacter> optionalCharacter = getCharacterUseCase.getCharacter(1L);


        List<Card> deck2 = new ArrayList<>();
        deck2.add(new Card(1L, "CardName", TypeOfCard.Atk, 10, 0, 0));

        assertTrue(optionalCharacter.isPresent());
        PlayableCharacter character = optionalCharacter.get();
        assertEquals("Character1", character.getName());
        assertEquals("Description", character.getDescription());
        assertEquals(50, character.getHealth());
        assertEquals(0, character.getAmmo());
        assertEquals(deck2, character.getStartingDeck());
        assertEquals(Image, character.getSprite());
    }
}
