package strategy_card_game.Business.Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Game.Exception.InvalidGameException;
import strategy_card_game.Business.Game.Impl.CreateGameUseCaseImpl;
import strategy_card_game.Business.Game.Impl.GetGameUseCaseImpl;
import strategy_card_game.Business.Game.Impl.UpdateGameUseCaseImpl;
import strategy_card_game.Domain.Area.Area;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Enemy.Enemy;
import strategy_card_game.Domain.Game.CreateGameRequest;
import strategy_card_game.Domain.Game.CreateGameResponse;
import strategy_card_game.Domain.Game.Game;
import strategy_card_game.Domain.Game.UpdateGameRequest;
import strategy_card_game.Domain.Level.Level;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Domain.User.User;
import strategy_card_game.Persistance.Entity.*;
import strategy_card_game.Persistance.GameRepository;
import strategy_card_game.Persistance.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateGameUseCaseImplTest {
    @InjectMocks
    private UpdateGameUseCaseImpl updateGameUseCase;
    @Mock
    private GetGameUseCaseImpl getGameUseCase;
    @Mock
    private CreateGameUseCaseImpl createGameUseCase;

    @Mock
    private GameRepository gameRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // Initialize the mocks and inject them into the use cases
        createGameUseCase = new CreateGameUseCaseImpl(gameRepository, userRepository);
        updateGameUseCase = new UpdateGameUseCaseImpl(gameRepository);
        getGameUseCase = new GetGameUseCaseImpl(gameRepository);
    }

    @Test
    public void testUpdateGame() {
        byte[] Image = new byte[0];
        List<EnemyEntity> enemies = new ArrayList<>();
        List<AreaEntity> areas = new ArrayList<>();
        List<CardEntity> cards = new ArrayList<>();
        List<CharacterEntity> characters = new ArrayList<>();
        List<LevelEntity> levels = new ArrayList<>();
        List<Enemy> enemies2 = new ArrayList<>();
        List<Area> areas2 = new ArrayList<>();
        List<Card> cards2 = new ArrayList<>();
        List<PlayableCharacter> characters2 = new ArrayList<>();
        List<Level> levels2 = new ArrayList<>();
        User developer = new User();
        UserEntity developer2 = new UserEntity();
        GameEntity game = new GameEntity(1L, "GameName", "Description", Image, Image, areas, cards, enemies, levels, characters, developer2);

        CreateGameRequest gameRequest = new CreateGameRequest(1L, "GameName", "Description", Image, Image, areas2, cards2, enemies2, levels2, characters2, developer);

        when(gameRepository.save(Mockito.any(GameEntity.class))).thenReturn(game);

        // Call the createCardUseCase to create the game
        CreateGameResponse createResponse = createGameUseCase.createGame(gameRequest);

        // Mock the behavior of cardRepository.findById to return the created game
        when(gameRepository.findById(ArgumentMatchers.eq(createResponse.getGameId()))).thenReturn(Optional.of(game));

        // Create an updated game
        UpdateGameRequest updatedGame = new UpdateGameRequest(createResponse.getGameId(), "UpdatedGame", "Description", Image, Image, areas2, cards2, enemies2, levels2, characters2);

        // Call the updateGame method
        updateGameUseCase.updateGame(updatedGame);

        // Verify that the game has been updated
        Optional<Game> optionalGame = getGameUseCase.getGame(createResponse.getGameId());
        assertTrue(optionalGame.isPresent());
        Game playableGame = optionalGame.get();
        assertEquals("UpdatedGame", playableGame.getName());
        assertEquals("Description", game.getDescription());
        assertEquals(Image, game.getBanner());
        assertEquals(Image, game.getIcon());
        assertEquals(areas, game.getGameAreas());
        assertEquals(cards, game.getGameCards());
        assertEquals(enemies, game.getGameEnemies());
        assertEquals(levels, game.getGameLevels());
        assertEquals(characters, game.getGameCharacters());
    }

    @Test
    public void testUpdateInvalidGame() {
        byte[] Image = new byte[0];
        List<Enemy> enemies2 = new ArrayList<>();
        List<Area> areas2 = new ArrayList<>();
        List<Card> cards2 = new ArrayList<>();
        List<PlayableCharacter> characters2 = new ArrayList<>();
        List<Level> levels2 = new ArrayList<>();

        UpdateGameRequest updateRequest = new UpdateGameRequest(999L, "UpdatedGame", "Description", Image, Image, areas2, cards2, enemies2, levels2, characters2);

        // Verify that an InvalidGameException is thrown when updating an invalid game
        assertThrows(InvalidGameException.class, () -> updateGameUseCase.updateGame(updateRequest));
    }
}
