package strategy_card_game.Area;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Area.Impl.GetAreasUseCaseImpl;
import strategy_card_game.Domain.Area.GetAllAreasRequest;
import strategy_card_game.Domain.Area.Area;
import strategy_card_game.Persistance.Entity.AreaEntity;
import strategy_card_game.Persistance.AreaRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAreasUseCaseImplTest {
    @InjectMocks
    private GetAreasUseCaseImpl getAreasUseCase;

    @Mock
    private AreaRepository levelRepository;

    @BeforeEach
    public void setUp() {
        // No need to create instances here since Mockito takes care of them.
    }

    @Test
    public void testGetAllCards() {
        byte[] Image = new byte[0];

        AreaEntity levelEntity1 = new AreaEntity(1L, "Area1", "AreaDescription", new ArrayList<>(), new byte[0]);
        AreaEntity levelEntity2 = new AreaEntity(1L, "Area2", "AreaDescription", new ArrayList<>(), new byte[0]);

        List<AreaEntity> levelList = new ArrayList<>();
        levelList.add(levelEntity1);
        levelList.add(levelEntity2);

        when(levelRepository.findAll()).thenReturn(levelList);

        List<Area> levels = getAreasUseCase.getAreas(new GetAllAreasRequest()).getAreas();

        assertEquals(2, levels.size());

        assertEquals("Area1", levels.get(0).getName());
        assertEquals("Area2", levels.get(1).getName());
    }
}
