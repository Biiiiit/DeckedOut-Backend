package strategy_card_game.Business.Area;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Area.Impl.DeleteAreaUseCaseImpl;
import strategy_card_game.Business.Area.Impl.GetAreasUseCaseImpl;
import strategy_card_game.Domain.Area.Area;
import strategy_card_game.Persistance.AreaRepository;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteAreaUseCaseImplTest {
    @InjectMocks
    private DeleteAreaUseCaseImpl deleteAreaUseCase;

    @Mock
    private AreaRepository levelRepository;

    @Mock
    private GetAreasUseCaseImpl getAreasUseCase;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testDeleteArea() {
        // Create a mock level for testing
        Area level = new Area(1L, "AreaName", "AreaDescription", new ArrayList<>(), new byte[0]);

        // Mock the behavior of levelRepository.deleteById to do nothing when the level is deleted
        doNothing().when(levelRepository).deleteById(level.getId());

        // Delete the level
        deleteAreaUseCase.deleteArea(level.getId());

        // Verify that the levelRepository's deleteById method was called
        verify(levelRepository, times(1)).deleteById(level.getId());
    }
}
