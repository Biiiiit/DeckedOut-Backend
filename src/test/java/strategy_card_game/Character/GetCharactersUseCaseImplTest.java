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
import strategy_card_game.Business.Playable_Character.GetCharactersUseCase;
import strategy_card_game.Business.Playable_Character.Impl.CreateCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.GetCharactersUseCaseImpl;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Card.TypeOfCard;
import strategy_card_game.Domain.Playable_Character.CreateCharacterRequest;
import strategy_card_game.Domain.Playable_Character.GetAllCharactersRequest;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Persistance.CharacterRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GetCharactersUseCaseImplTest {
    private GetCharactersUseCase getCharactersUseCase;
    private CreateCharacterUseCase createCharacterUseCase;
    @Qualifier("characterRepository")
    @Autowired
    private CharacterRepository CharacterRepository;
    @BeforeEach
    public void setUp() {
        getCharactersUseCase = new GetCharactersUseCaseImpl(CharacterRepository);
        createCharacterUseCase = new CreateCharacterUseCaseImpl(CharacterRepository);
    }
    @Test
    public void testGetAllCards() {
        List<Card> deck = new ArrayList<>();
        deck.add(new Card(1L, "CardName", TypeOfCard.Atk, 10, 0, 0));
        Image Image = null;
        CreateCharacterRequest character1Request = new CreateCharacterRequest(1L,"Character1", "Description", 50, 0, deck, new byte[0]);
        CreateCharacterRequest character2Request = new CreateCharacterRequest(2L,"Character2", "Description", 50, 0, deck, new byte[0]);

        createCharacterUseCase.createCharacter(character1Request);
        createCharacterUseCase.createCharacter(character2Request);

        List<PlayableCharacter> characters = getCharactersUseCase.getCharacters(new GetAllCharactersRequest()).getCharacters();

        assertEquals(2, characters.size());

        assertEquals("Character1", characters.get(0).getName());
        assertEquals("Character2", characters.get(1).getName());
    }
}
