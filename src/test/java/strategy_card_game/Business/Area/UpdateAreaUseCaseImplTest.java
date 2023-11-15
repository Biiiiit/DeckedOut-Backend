package strategy_card_game.Business.Area;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Area.Exception.InvalidAreaException;
import strategy_card_game.Business.Area.Impl.CreateAreaUseCaseImpl;
import strategy_card_game.Business.Area.Impl.GetAreaUseCaseImpl;
import strategy_card_game.Business.Area.Impl.UpdateAreaUseCaseImpl;
import strategy_card_game.Domain.Area.Area;
import strategy_card_game.Domain.Area.CreateAreaRequest;
import strategy_card_game.Domain.Area.CreateAreaResponse;
import strategy_card_game.Domain.Area.UpdateAreaRequest;
import strategy_card_game.Domain.Level.Level;
import strategy_card_game.Persistance.AreaRepository;
import strategy_card_game.Persistance.Entity.AreaEntity;
import strategy_card_game.Persistance.Entity.LevelEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateAreaUseCaseImplTest {
    @InjectMocks
    private UpdateAreaUseCaseImpl updateAreaUseCase;
    @Mock
    private GetAreaUseCaseImpl getAreaUseCase;
    @Mock
    private CreateAreaUseCaseImpl createAreaUseCase;

    @Mock
    private AreaRepository areaRepository;

    @BeforeEach
    public void setUp() {
        // Initialize the mocks and inject them into the use cases
        createAreaUseCase = new CreateAreaUseCaseImpl(areaRepository);
        updateAreaUseCase = new UpdateAreaUseCaseImpl(areaRepository);
        getAreaUseCase = new GetAreaUseCaseImpl(areaRepository);
    }

    @Test
    public void testUpdateArea() {
        byte[] Image = new byte[0];
        List<LevelEntity> levels = new ArrayList<>();
        levels.add(new LevelEntity(1L, "Level", new ArrayList<>(), new byte[0], new byte[0]));
        List<Level> level2 = new ArrayList<>();
        level2.add(new Level(1L, "Level", new ArrayList<>(), new byte[0], new byte[0]));

        CreateAreaRequest areaRequest = new CreateAreaRequest(1L, "AreaName", "AreaDescription", level2, Image);

        AreaEntity area = new AreaEntity(1L, "AreaName", "AreaDescription", levels, Image);
        when(areaRepository.save(Mockito.any(AreaEntity.class))).thenReturn(area);

        // Call the createCardUseCase to create the area
        CreateAreaResponse createResponse = createAreaUseCase.createArea(areaRequest);

        // Mock the behavior of cardRepository.findById to return the created area
        when(areaRepository.findById(ArgumentMatchers.eq(createResponse.getAreaId()))).thenReturn(Optional.of(area));

        // Create an updated area
        UpdateAreaRequest updatedArea = new UpdateAreaRequest(createResponse.getAreaId(), "UpdatedArea", "UpdatedDescription", level2, Image);

        // Call the updateArea method
        updateAreaUseCase.updateArea(updatedArea);

        // Verify that the area has been updated
        Optional<Area> optionalArea = getAreaUseCase.getArea(createResponse.getAreaId());
        assertTrue(optionalArea.isPresent());
        Area playableArea = optionalArea.get();
        assertEquals("UpdatedArea", playableArea.getName());
        assertEquals("UpdatedDescription", playableArea.getDescription());
        assertEquals(level2, playableArea.getListOfLevels());
        assertEquals(Image, playableArea.getBackgroundSprite());
    }

    @Test
    public void testUpdateInvalidArea() {
        List<Level> levels = null;
        UpdateAreaRequest updateRequest = new UpdateAreaRequest(999L, "UpdatedArea", "UpdatedDescription",  levels, new byte[0]);

        // Verify that an InvalidAreaException is thrown when updating an invalid area
        assertThrows(InvalidAreaException.class, () -> updateAreaUseCase.updateArea(updateRequest));
    }
}
