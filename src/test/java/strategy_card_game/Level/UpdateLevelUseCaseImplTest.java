package strategy_card_game.Level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Level.Exception.InvalidLevelException;
import strategy_card_game.Business.Level.Impl.CreateLevelUseCaseImpl;
import strategy_card_game.Business.Level.Impl.GetLevelUseCaseImpl;
import strategy_card_game.Business.Level.Impl.UpdateLevelUseCaseImpl;
import strategy_card_game.Domain.Enemy.Enemy;
import strategy_card_game.Domain.Level.CreateLevelRequest;
import strategy_card_game.Domain.Level.CreateLevelResponse;
import strategy_card_game.Domain.Level.Level;
import strategy_card_game.Domain.Level.UpdateLevelRequest;
import strategy_card_game.Persistance.Entity.EnemyEntity;
import strategy_card_game.Persistance.Entity.LevelEntity;
import strategy_card_game.Persistance.LevelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateLevelUseCaseImplTest {
    @InjectMocks
    private UpdateLevelUseCaseImpl updateLevelUseCase;
    @Mock
    private GetLevelUseCaseImpl getLevelUseCase;
    @Mock
    private CreateLevelUseCaseImpl createLevelUseCase;

    @Mock
    private LevelRepository levelRepository;

    @BeforeEach
    public void setUp() {
        // Initialize the mocks and inject them into the use cases
        createLevelUseCase = new CreateLevelUseCaseImpl(levelRepository);
        updateLevelUseCase = new UpdateLevelUseCaseImpl(levelRepository);
        getLevelUseCase = new GetLevelUseCaseImpl(levelRepository);
    }

    @Test
    public void testUpdateLevel() {
        byte[] Image = new byte[0];
        List<EnemyEntity> enemies = new ArrayList<>();
        enemies.add(new EnemyEntity(1L, "enemy1", 50, 10, 0, 0, "pattern", Image));
        List<Enemy> enemies2 = new ArrayList<>();
        enemies2.add(new Enemy(1L, "enemy1", 50, 10, 0, 0, "pattern", Image));

        CreateLevelRequest levelRequest = new CreateLevelRequest(1L, "Level1", enemies2, Image, Image);

        LevelEntity level = new LevelEntity(1L, "Level1", enemies, Image, Image);
        when(levelRepository.save(Mockito.any(LevelEntity.class))).thenReturn(level);

        // Call the createCardUseCase to create the level
        CreateLevelResponse createResponse = createLevelUseCase.createLevel(levelRequest);

        // Mock the behavior of cardRepository.findById to return the created level
        when(levelRepository.findById(ArgumentMatchers.eq(createResponse.getLevelId()))).thenReturn(Optional.of(level));

        // Create an updated level
        UpdateLevelRequest updatedLevel = new UpdateLevelRequest(createResponse.getLevelId(), "UpdatedLevel", enemies2, Image, Image);

        // Call the updateLevel method
        updateLevelUseCase.updateLevel(updatedLevel);

        // Verify that the level has been updated
        Optional<Level> optionalLevel = getLevelUseCase.getLevel(createResponse.getLevelId());
        assertTrue(optionalLevel.isPresent());
        Level playableLevel = optionalLevel.get();
        assertEquals("UpdatedLevel", playableLevel.getName());
        assertEquals(enemies2, playableLevel.getEnemies());
        assertEquals(Image, playableLevel.getLvlSprite());
        assertEquals(Image, playableLevel.getBackgroundSprite());
    }

    @Test
    public void testUpdateInvalidLevel() {
        List<Enemy> enemies = null;
        UpdateLevelRequest updateRequest = new UpdateLevelRequest(999L, "UpdatedLevel", enemies, new byte[0], new byte[0]);

        // Verify that an InvalidLevelException is thrown when updating an invalid level
        assertThrows(InvalidLevelException.class, () -> updateLevelUseCase.updateLevel(updateRequest));
    }
}
