package strategy_card_game.Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Game.Exception.GameAlreadyExistsException;
import strategy_card_game.Business.Game.Impl.CreateGameUseCaseImpl;
import strategy_card_game.Domain.Game.CreateGameRequest;
import strategy_card_game.Domain.Game.CreateGameResponse;
import strategy_card_game.Persistance.Entity.GameEntity;
import strategy_card_game.Persistance.GameRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateGameUseCaseImplTest {
    @InjectMocks
    private CreateGameUseCaseImpl createGameUseCase;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    public void setUp() {
        // No need to create the instance of createGameUseCase here since Mockito takes care of it.
    }

    @Test
    public void testCreateGameSuccess() {
        // Create a mock CreateGameRequest
        CreateGameRequest request = new CreateGameRequest(1L, "GameName", "Description", new byte[0], new byte[0], new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Mock the behavior of cardRepository.save to return a GameEntity with an ID.
        GameEntity savedGame = new GameEntity(1L, "GameName", "Description", new byte[0], new byte[0], new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        when(gameRepository.save(Mockito.any(GameEntity.class))).thenReturn(savedGame);

        // Call the createGame method
        CreateGameResponse response = createGameUseCase.createGame(request);

        // Verify that the response contains the cardId
        assertNotNull(response);
    }

    @Test
    public void testCreateGameFailureGameAlreadyExists() {
        // Mock the behavior of cardRepository.existsByName to return true.
        when(gameRepository.existsByName("ExistingGameName")).thenReturn(true);

        // Create a CreateGameRequest for an existing card
        CreateGameRequest request = new CreateGameRequest(1L, "ExistingGameName", "Description", new byte[0], new byte[0], new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Call the createGame method and expect a GameAlreadyExistsException
        assertThrows(GameAlreadyExistsException.class, () -> createGameUseCase.createGame(request));
    }
}