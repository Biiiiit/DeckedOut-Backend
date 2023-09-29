package strategy_card_game.Character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy_card_game.Business.Playable_Character.CreateCharacterUseCase;
import strategy_card_game.Business.Playable_Character.GetCharactersUseCase;
import strategy_card_game.Business.Playable_Character.Impl.CreateCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.GetCharactersUseCaseImpl;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Playable_Character.CreateCharacterRequest;
import strategy_card_game.Domain.Playable_Character.GetAllCharactersRequest;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Persistance.CharacterRepository;
import strategy_card_game.Persistance.Impl.FakeCharacterRepositoryImpl;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCharactersUseCaseImplTest {
    private GetCharactersUseCase getCharactersUseCase;
    private CreateCharacterUseCase createCharacterUseCase;
    private CharacterRepository fakeCharacterRepository;
    @BeforeEach
    public void setUp() {
        fakeCharacterRepository = new FakeCharacterRepositoryImpl();
        getCharactersUseCase = new GetCharactersUseCaseImpl(fakeCharacterRepository);
        createCharacterUseCase = new CreateCharacterUseCaseImpl(fakeCharacterRepository);
    }
    @Test
    public void testGetAllCards() {
        List<Card> deck = null;
        Image Image = null;
        CreateCharacterRequest character1Request = new CreateCharacterRequest(null,"Character1", "Description", 50, 0, deck, Image);
        CreateCharacterRequest character2Request = new CreateCharacterRequest(null,"Character2", "Description", 50, 0, deck, Image);

        createCharacterUseCase.createCharacter(character1Request);
        createCharacterUseCase.createCharacter(character2Request);

        List<PlayableCharacter> characters = getCharactersUseCase.getCharacters(new GetAllCharactersRequest()).getCharacters();

        assertEquals(2, characters.size());

        assertEquals("Character1", characters.get(0).getName());
        assertEquals("Character2", characters.get(1).getName());
    }
}
