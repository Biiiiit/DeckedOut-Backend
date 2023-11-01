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
import strategy_card_game.Business.Playable_Character.GetCharacterUseCase;
import strategy_card_game.Business.Playable_Character.Impl.CreateCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.GetCharacterUseCaseImpl;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Card.TypeOfCard;
import strategy_card_game.Domain.Playable_Character.CreateCharacterRequest;
import strategy_card_game.Domain.Playable_Character.CreateCharacterResponse;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Persistance.CharacterRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GetCharacterUseCaseImplTest {
    private GetCharacterUseCase getCharacterUseCase;
    private CreateCharacterUseCase createCharacterUseCase;
    @Qualifier("characterRepository")
    @Autowired
    private CharacterRepository CharacterRepository;
    @BeforeEach
    public void setUp() {
        getCharacterUseCase = new GetCharacterUseCaseImpl(CharacterRepository);
        createCharacterUseCase = new CreateCharacterUseCaseImpl(CharacterRepository);
    }

    @Test
    public void testGetCharacter() {
        List<Card> deck = new ArrayList<>();
        deck.add(new Card(1L, "CardName", TypeOfCard.Atk, 10, 0, 0));
        Image Image = null;
        CreateCharacterRequest characterRequest = new CreateCharacterRequest(1L,"Character", "Description", 50, 0, deck, new byte[0]);
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
