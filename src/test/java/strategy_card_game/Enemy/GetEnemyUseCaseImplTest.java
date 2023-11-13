package strategy_card_game.Enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Enemy.CreateEnemyUseCase;
import strategy_card_game.Business.Enemy.Impl.GetEnemyUseCaseImpl;
import strategy_card_game.Domain.Enemy.Enemy;
import strategy_card_game.Persistance.EnemyRepository;
import strategy_card_game.Persistance.Entity.EnemyEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetEnemyUseCaseImplTest {

    @InjectMocks
    private GetEnemyUseCaseImpl getEnemyUseCase;

    @Mock
    private CreateEnemyUseCase createEnemyUseCase;

    @Mock
    private EnemyRepository cardRepository;

    @BeforeEach
    public void setUp() {
        // No need to create the instance of getEnemyUseCase here since Mockito takes care of it.
    }

    @Test
    public void testGetEnemy() {
        byte[] image = new byte[0];
        // Create a mock EnemyEntity to be returned by the repository
        EnemyEntity cardEntity = new EnemyEntity(1L, "EnemyName", 50, 10, 0, 0, "pattern", image);

        // Mock the behavior of cardRepository.findById to return the EnemyEntity when called with 1L
        when(cardRepository.findById(1L)).thenReturn(Optional.of(cardEntity));

        // Get the created card using getEnemyUseCase
        Optional<Enemy> optionalEnemy = getEnemyUseCase.getEnemy(1L);

        // Verify that the card is present and has the correct details
        assertTrue(optionalEnemy.isPresent());
        Enemy enemy = optionalEnemy.get();
        assertEquals("EnemyName", enemy.getName());
        assertEquals(50, enemy.getHealth());
        assertEquals(10, enemy.getAtk());
        assertEquals(0, enemy.getHealing());
        assertEquals(0, enemy.getShielding());
        assertEquals("pattern", enemy.getPattern());
        assertEquals(image, enemy.getSprite());
    }

    @Test
    public void testGetNonExistentEnemy() {
        // Attempt to get a card with an invalid ID
        when(cardRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Enemy> optionalEnemy = getEnemyUseCase.getEnemy(999L);

        // Verify that the optional is empty as the card doesn't exist
        assertTrue(optionalEnemy.isEmpty());
    }
}
