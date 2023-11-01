package strategy_card_game.Character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import strategy_card_game.Business.Playable_Character.CreateCharacterUseCase;
import strategy_card_game.Business.Playable_Character.Exception.InvalidCharacterException;
import strategy_card_game.Business.Playable_Character.GetCharactersUseCase;
import strategy_card_game.Business.Playable_Character.Impl.CreateCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.GetCharactersUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.UpdateCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.UpdateCharacterUseCase;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Card.TypeOfCard;
import strategy_card_game.Domain.Playable_Character.*;
import strategy_card_game.Persistance.CharacterRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UpdateCharacterUseCaseImplTest {
    private GetCharactersUseCase getCharactersUseCase;
    private CreateCharacterUseCase createCharacterUseCase;
    private UpdateCharacterUseCase updateCharacterUseCase;
    @Qualifier("characterRepository")
    @Autowired
    private CharacterRepository CharacterRepository;
    @BeforeEach
    public void setUp() {
        getCharactersUseCase = new GetCharactersUseCaseImpl(CharacterRepository);
        createCharacterUseCase = new CreateCharacterUseCaseImpl(CharacterRepository);
        updateCharacterUseCase = new UpdateCharacterUseCaseImpl(CharacterRepository);
    }

    @Test
    public void testUpdateCharacter() {
        List<Card> deck = new ArrayList<>();
        deck.add(new Card(1L, "CardName", TypeOfCard.Atk, 10, 0, 0));
        Image Image = null;
        CreateCharacterRequest characterRequest = new CreateCharacterRequest(1L,"Character", "Description", 50, 0, deck, new byte[0]);
        CreateCharacterResponse createResponse = createCharacterUseCase.createCharacter(characterRequest);

        List<PlayableCharacter> charactersBeforeUpdate = getCharactersUseCase.getCharacters(new GetAllCharactersRequest()).getCharacters();
        assertTrue(charactersBeforeUpdate.stream().anyMatch(character -> character.getId().equals(createResponse.getCharacterId())));

        // Update the card
        UpdateCharacterRequest updateRequest = new UpdateCharacterRequest(createResponse.getCharacterId(), "UpdatedCharacter", "Description", 5, 2, deck, new byte[0]);
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
        UpdateCharacterRequest updateRequest = new UpdateCharacterRequest(999L, "UpdatedCharacter", "Description", 5, 2, deck, new byte[0]);

        assertThrows(InvalidCharacterException.class, () -> updateCharacterUseCase.updateCharacter(updateRequest));
    }
}
