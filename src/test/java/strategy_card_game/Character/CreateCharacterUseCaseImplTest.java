package strategy_card_game.Character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import strategy_card_game.Business.Playable_Character.Impl.CreateCharacterUseCaseImpl;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Card.TypeOfCard;
import strategy_card_game.Domain.Playable_Character.CreateCharacterRequest;
import strategy_card_game.Domain.Playable_Character.CreateCharacterResponse;
import strategy_card_game.Persistance.CharacterRepository;
import strategy_card_game.Persistance.Entity.CardEntity;
import strategy_card_game.Persistance.Entity.CharacterEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CreateCharacterUseCaseImplTest {
    private CreateCharacterUseCaseImpl createCharacterUseCase;
    @Qualifier("characterRepository")
    @Autowired
    private CharacterRepository CharacterRepository;

    @BeforeEach
    public void setUp() {
        createCharacterUseCase = new CreateCharacterUseCaseImpl(CharacterRepository);
    }
    @Test
    public void testCreateCharacterSuccess() {
        List<Card> deck = new ArrayList<>();
        deck.add(new Card(1L, "CardName", TypeOfCard.Atk, 10, 0, 0));
        Image Image = null;
        CreateCharacterRequest request = new CreateCharacterRequest(1L,"CharacterName", "Description", 50, 0, deck, new byte[0]);

        CreateCharacterResponse response = createCharacterUseCase.createCharacter(request);

        assertNotNull(response);
        assertNotNull(response.getCharacterId());
    }

    @Test
    public void testCreateCharacterFailureCardAlreadyExists() {
        List<Card> deck = null;
        List<CardEntity> deckEntity = null;
        Image Image = null;

        CharacterEntity existingCharacter = new CharacterEntity(1L,"CharacterName", "Description", 50, 0, deckEntity, new byte[0]);
        CharacterRepository.save(existingCharacter);

        CreateCharacterRequest request = new CreateCharacterRequest(1L,"CharacterName", "Description", 50, 0, deck, new byte[0]);

        assertThrows(Exception.class, () -> createCharacterUseCase.createCharacter(request));
    }
}
