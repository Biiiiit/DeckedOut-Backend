package strategy_card_game.Business.Area;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Area.Impl.CreateAreaUseCaseImpl;
import strategy_card_game.Business.Area.Impl.GetAreaUseCaseImpl;
import strategy_card_game.Domain.Area.Area;
import strategy_card_game.Domain.Level.Level;
import strategy_card_game.Persistance.AreaRepository;
import strategy_card_game.Persistance.Entity.AreaEntity;
import strategy_card_game.Persistance.Entity.LevelEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAreaUseCaseImplTest {
    @InjectMocks
    private GetAreaUseCaseImpl getAreaUseCase;

    @Mock
    private CreateAreaUseCaseImpl createAreaUseCase;

    @Mock
    private AreaRepository areaRepository;

    @BeforeEach
    public void setUp() {
        // No need to create instances here since Mockito takes care of them.
    }

    @Test
    public void testGetAllCards() {
        byte[] Image = new byte[0];
        List<LevelEntity> levels = new ArrayList<>();
        levels.add(new LevelEntity(1L, "Level", new ArrayList<>(), new byte[0], new byte[0]));

        AreaEntity areaEntity = new AreaEntity(1L, "AreaName", "AreaDescription", levels, Image);

        when(areaRepository.findById(1L)).thenReturn(Optional.of(areaEntity));

        List<Level> level2 = new ArrayList<>();
        level2.add(new Level(1L, "Level", new ArrayList<>(), new byte[0], new byte[0]));

        Optional<Area> optionalArea = getAreaUseCase.getArea(1L);

        assertTrue(optionalArea.isPresent());
        Area area = optionalArea.get();
        assertEquals("AreaName", area.getName());
        assertEquals("AreaDescription", area.getDescription());
        assertEquals(level2, area.getListOfLevels());
        assertEquals(Image, area.getBackgroundSprite());
    }
}
