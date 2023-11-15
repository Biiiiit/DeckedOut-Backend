package strategy_card_game.Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Game.Impl.DeleteGameUseCaseImpl;
import strategy_card_game.Domain.Game.Game;
import strategy_card_game.Persistance.GameRepository;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteGameUseCaseImplTest {
    @InjectMocks
    private DeleteGameUseCaseImpl deleteGameUseCase;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testDeleteGame() {
        // Create a mock game for testing
        Game game = new Game(1L, "GameName", "Description", new byte[0], new byte[0], new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Mock the behavior of gameRepository.deleteById to do nothing when the game is deleted
        doNothing().when(gameRepository).deleteById(game.getId());

        // Delete the game
        deleteGameUseCase.deleteGame(game.getId());

        // Verify that the gameRepository's deleteById method was called
        verify(gameRepository, times(1)).deleteById(game.getId());
    }
}
