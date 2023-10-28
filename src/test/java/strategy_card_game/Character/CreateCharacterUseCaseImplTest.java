//package strategy_card_game.Character;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import strategy_card_game.Business.Playable_Character.Exception.CharacterAlreadyExistsException;
//import strategy_card_game.Business.Playable_Character.Impl.CreateCharacterUseCaseImpl;
//import strategy_card_game.Domain.Card.Card;
//import strategy_card_game.Domain.Playable_Character.CreateCharacterRequest;
//import strategy_card_game.Domain.Playable_Character.CreateCharacterResponse;
//import strategy_card_game.Persistance.CharacterRepository;
//import strategy_card_game.Persistance.Entity.CharacterEntity;
//import strategy_card_game.Persistance.Impl.FakeCharacterRepositoryImpl;
//
//import java.awt.*;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class CreateCharacterUseCaseImplTest {
//    private CreateCharacterUseCaseImpl createCharacterUseCase;
//    private CharacterRepository fakeCharacterRepository;
//
//    @BeforeEach
//    public void setUp() {
//        fakeCharacterRepository = new FakeCharacterRepositoryImpl(); // Instantiate your fake repository
//        createCharacterUseCase = new CreateCharacterUseCaseImpl(fakeCharacterRepository);
//    }
//    @Test
//    public void testCreateCharacterSuccess() {
//        List<Card> deck = null;
//        Image Image = null;
//        CreateCharacterRequest request = new CreateCharacterRequest(1L,"CharacterName", "Description", 50, 0, deck, Image);
//
//        CreateCharacterResponse response = createCharacterUseCase.createCharacter(request);
//
//        assertNotNull(response);
//        assertNotNull(response.getCharacterId());
//    }
//
//    @Test
//    public void testCreateCharacterFailureCardAlreadyExists() {
//        List<Card> deck = null;
//        Image Image = null;
//
//        CharacterEntity existingCharacter = new CharacterEntity(null,"CharacterName", "Description", 50, 0, deck, Image);
//        fakeCharacterRepository.save(existingCharacter);
//
//        CreateCharacterRequest request = new CreateCharacterRequest(1L,"CharacterName", "Description", 50, 0, deck, Image);
//
//        assertThrows(CharacterAlreadyExistsException.class, () -> createCharacterUseCase.createCharacter(request));
//    }
//}
