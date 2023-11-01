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
import strategy_card_game.Business.Playable_Character.DeleteCharacterUseCase;
import strategy_card_game.Business.Playable_Character.GetCharactersUseCase;
import strategy_card_game.Business.Playable_Character.Impl.CreateCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.DeleteCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.GetCharactersUseCaseImpl;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Card.TypeOfCard;
import strategy_card_game.Domain.Playable_Character.CreateCharacterRequest;
import strategy_card_game.Domain.Playable_Character.CreateCharacterResponse;
import strategy_card_game.Domain.Playable_Character.GetAllCharactersRequest;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Persistance.CharacterRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeleteCharacterUseCaseImplTest {
    private GetCharactersUseCase getCharactersUseCase;
    private CreateCharacterUseCase createCharacterUseCase;
    private DeleteCharacterUseCase deleteCharacterUseCase;
    @Qualifier("characterRepository")
    @Autowired
    private CharacterRepository CharacterRepository;
    @BeforeEach
    public void setUp() {
        getCharactersUseCase = new GetCharactersUseCaseImpl(CharacterRepository);
        createCharacterUseCase = new CreateCharacterUseCaseImpl(CharacterRepository);
        deleteCharacterUseCase = new DeleteCharacterUseCaseImpl(CharacterRepository);
    }

    @Test
    public void testDeleteCharacter() {
        List<Card> deck = new ArrayList<>();
        deck.add(new Card(1L, "CardName", TypeOfCard.Atk, 10, 0, 0));
        Image Image = null;
        CreateCharacterRequest characterRequest = new CreateCharacterRequest(1L,"CharacterName", "Description", 50, 0, deck, new byte[0]);
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
