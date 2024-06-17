package strategy_card_game.Business.Game;

import org.junit.jupiter.api.Assertions;
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
import strategy_card_game.Domain.User.User;
import strategy_card_game.Persistance.Entity.GameEntity;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.GameRepository;
import strategy_card_game.Persistance.UserRepository; // Ensure you import the UserRepository

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateGameUseCaseImplTest {

    @InjectMocks
    private CreateGameUseCaseImpl createGameUseCase;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private UserRepository userRepository; // Mock the UserRepository as well

    @BeforeEach
    public void setUp() {
        // Initialize the user repository mock in the createGameUseCase
        createGameUseCase = new CreateGameUseCaseImpl(gameRepository, userRepository);
    }

    @Test
    public void testCreateGameSuccess() {
        // Mock the developer entity
        UserEntity developer = new UserEntity();
        when(userRepository.findById(1L)).thenReturn(Optional.of(developer));

        // Create a mock CreateGameRequest
        CreateGameRequest request = new CreateGameRequest("GameName", "Description", new byte[0], new byte[0], new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 1L);

        // Mock the behavior of gameRepository.save to return a GameEntity with an ID.
        GameEntity savedGame = new GameEntity(1L, "GameName", "Description", new byte[0], new byte[0], new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), developer);
        when(gameRepository.save(Mockito.any(GameEntity.class))).thenReturn(savedGame);

        // Call the createGame method
        CreateGameResponse response = createGameUseCase.createGame(request);

        // Verify that the response contains the gameId
        assertNotNull(response);
        Assertions.assertEquals(1L, response.getGameId()); // Assuming CreateGameResponse has a method to get the gameId
    }


    @Test
    public void testCreateGameFailureGameAlreadyExists() {
        // Mock the behavior of cardRepository.existsByName to return true.
        when(gameRepository.existsByName("ExistingGameName")).thenReturn(true);

        // Create a CreateGameRequest for an existing card
        CreateGameRequest request = new CreateGameRequest("ExistingGameName", "Description", new byte[0], new byte[0], new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 1L);

        // Call the createGame method and expect a GameAlreadyExistsException
        assertThrows(GameAlreadyExistsException.class, () -> createGameUseCase.createGame(request));
    }
}
