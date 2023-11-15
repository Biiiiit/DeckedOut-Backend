package strategy_card_game.Business.Character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Playable_Character.Impl.GetCharactersUseCaseImpl;
import strategy_card_game.Domain.Card.TypeOfCard;
import strategy_card_game.Domain.Playable_Character.GetAllCharactersRequest;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Persistance.CharacterRepository;
import strategy_card_game.Persistance.Entity.CardEntity;
import strategy_card_game.Persistance.Entity.CharacterEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCharactersUseCaseImplTest {
    @InjectMocks
    private GetCharactersUseCaseImpl getCharactersUseCase;

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

        CharacterEntity characterEntity1 = new CharacterEntity(1L, "Character1", "Description", 50, 0, deck, new byte[0]);
        CharacterEntity characterEntity2 = new CharacterEntity(2L, "Character2", "Description", 50, 0, deck, new byte[0]);

        List<CharacterEntity> characterList = new ArrayList<>();
        characterList.add(characterEntity1);
        characterList.add(characterEntity2);

        when(characterRepository.findAll()).thenReturn(characterList);

        List<PlayableCharacter> characters = getCharactersUseCase.getCharacters(new GetAllCharactersRequest()).getCharacters();

        assertEquals(2, characters.size());

        assertEquals("Character1", characters.get(0).getName());
        assertEquals("Character2", characters.get(1).getName());
    }
}
