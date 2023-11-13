package strategy_card_game.Level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Level.Impl.GetLevelsUseCaseImpl;
import strategy_card_game.Domain.Level.GetAllLevelsRequest;
import strategy_card_game.Domain.Level.Level;
import strategy_card_game.Persistance.Entity.LevelEntity;
import strategy_card_game.Persistance.LevelRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetLevelsUseCaseImplTest {
    @InjectMocks
    private GetLevelsUseCaseImpl getLevelsUseCase;

    @Mock
    private LevelRepository levelRepository;

    @BeforeEach
    public void setUp() {
        // No need to create instances here since Mockito takes care of them.
    }

    @Test
    public void testGetAllCards() {
        byte[] Image = new byte[0];

        LevelEntity levelEntity1 = new LevelEntity(1L, "Level1", new ArrayList<>(), new byte[0], new byte[0]);
        LevelEntity levelEntity2 = new LevelEntity(2L, "Level2", new ArrayList<>(), new byte[0], new byte[0]);

        List<LevelEntity> levelList = new ArrayList<>();
        levelList.add(levelEntity1);
        levelList.add(levelEntity2);

        when(levelRepository.findAll()).thenReturn(levelList);

        List<Level> levels = getLevelsUseCase.getLevels(new GetAllLevelsRequest()).getLevels();

        assertEquals(2, levels.size());

        assertEquals("Level1", levels.get(0).getName());
        assertEquals("Level2", levels.get(1).getName());
    }
}
