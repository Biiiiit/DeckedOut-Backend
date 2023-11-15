package strategy_card_game.Business.Level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Level.Impl.DeleteLevelUseCaseImpl;
import strategy_card_game.Business.Level.Impl.GetLevelsUseCaseImpl;
import strategy_card_game.Domain.Level.Level;
import strategy_card_game.Persistance.LevelRepository;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteLevelUseCaseImplTest {
    @InjectMocks
    private DeleteLevelUseCaseImpl deleteLevelUseCase;

    @Mock
    private LevelRepository levelRepository;

    @Mock
    private GetLevelsUseCaseImpl getLevelsUseCase;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testDeleteLevel() {
        // Create a mock level for testing
        Level level = new Level(1L, "LevelName", new ArrayList<>(), new byte[0], new byte[0]);

        // Mock the behavior of levelRepository.deleteById to do nothing when the level is deleted
        doNothing().when(levelRepository).deleteById(level.getId());

        // Delete the level
        deleteLevelUseCase.deleteLevel(level.getId());

        // Verify that the levelRepository's deleteById method was called
        verify(levelRepository, times(1)).deleteById(level.getId());
    }
}
