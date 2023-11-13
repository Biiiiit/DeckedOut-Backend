package strategy_card_game.Level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Level.Impl.CreateLevelUseCaseImpl;
import strategy_card_game.Business.Level.Impl.GetLevelUseCaseImpl;
import strategy_card_game.Domain.Enemy.Enemy;
import strategy_card_game.Domain.Level.Level;
import strategy_card_game.Persistance.Entity.EnemyEntity;
import strategy_card_game.Persistance.Entity.LevelEntity;
import strategy_card_game.Persistance.LevelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetLevelUseCaseImplTest {
    @InjectMocks
    private GetLevelUseCaseImpl getLevelUseCase;

    @Mock
    private CreateLevelUseCaseImpl createLevelUseCase;

    @Mock
    private LevelRepository levelRepository;

    @BeforeEach
    public void setUp() {
        // No need to create instances here since Mockito takes care of them.
    }

    @Test
    public void testGetAllCards() {
        byte[] Image = new byte[0];
        List<EnemyEntity> enemies = new ArrayList<>();
        enemies.add(new EnemyEntity(1L, "enemy1", 50, 10, 0, 0, "pattern", Image));

        LevelEntity levelEntity = new LevelEntity(1L, "Level1", enemies, Image, Image);

        when(levelRepository.findById(1L)).thenReturn(Optional.of(levelEntity));

        List<Enemy> enemies2 = new ArrayList<>();
        enemies2.add(new Enemy(1L, "enemy1", 50, 10, 0, 0, "pattern", Image));

        Optional<Level> optionalLevel = getLevelUseCase.getLevel(1L);

        assertTrue(optionalLevel.isPresent());
        Level level = optionalLevel.get();
        assertEquals("Level1", level.getName());
        assertEquals(enemies2, level.getEnemies());
        assertEquals(Image, level.getBackgroundSprite());
        assertEquals(Image, level.getLvlSprite());
    }
}
