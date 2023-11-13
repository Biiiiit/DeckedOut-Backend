package strategy_card_game.Enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Enemy.Impl.CreateEnemyUseCaseImpl;
import strategy_card_game.Business.Enemy.Impl.GetEnemyUseCaseImpl;
import strategy_card_game.Business.Enemy.Impl.UpdateEnemyUseCaseImpl;
import strategy_card_game.Domain.Enemy.CreateEnemyRequest;
import strategy_card_game.Domain.Enemy.CreateEnemyResponse;
import strategy_card_game.Domain.Enemy.Enemy;
import strategy_card_game.Domain.Enemy.UpdateEnemyRequest;
import strategy_card_game.Persistance.EnemyRepository;
import strategy_card_game.Persistance.Entity.EnemyEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateEnemyUseCaseImplTest {

    @InjectMocks
    private UpdateEnemyUseCaseImpl updateEnemyUseCase;

    @InjectMocks
    private GetEnemyUseCaseImpl getEnemyUseCase;

    @InjectMocks
    private CreateEnemyUseCaseImpl createEnemyUseCase;

    @Mock
    private EnemyRepository cardRepository;

    @BeforeEach
    public void setUp() {
        // Initialize the mocks and inject them into the use cases
        createEnemyUseCase = new CreateEnemyUseCaseImpl(cardRepository);
        updateEnemyUseCase = new UpdateEnemyUseCaseImpl(cardRepository);
        getEnemyUseCase = new GetEnemyUseCaseImpl(cardRepository);
    }

    @Test
    public void testUpdateEnemy() {
        // Create a mock CreateEnemyRequest
        CreateEnemyRequest request = new CreateEnemyRequest(1L, "EnemyName", 50, 10, 0, 0, "pattern", new byte[0]);

        // Mock the behavior of cardRepository.save to return a EnemyEntity with an ID.
        EnemyEntity savedEnemy = new EnemyEntity(1L, "EnemyName", 50, 10, 0, 0, "pattern", new byte[0]);
        when(cardRepository.save(any(EnemyEntity.class))).thenReturn(savedEnemy);

        // Call the createEnemyUseCase to create the card
        CreateEnemyResponse createResponse = createEnemyUseCase.createEnemy(request);

        // Mock the behavior of cardRepository.findById to return the created card
        when(cardRepository.findById(eq(createResponse.getEnemyId()))).thenReturn(Optional.of(savedEnemy));

        byte[] image = new byte[0];
        // Create an updated card
        UpdateEnemyRequest updatedEnemy = new UpdateEnemyRequest(createResponse.getEnemyId(), "UpdatedEnemy", 40, 20, 10, 2, "NewPattern", image);

        // Call the updateEnemy method
        updateEnemyUseCase.updateEnemy(updatedEnemy);

        // Verify that the card has been updated
        Optional<Enemy> optionalEnemy = getEnemyUseCase.getEnemy(createResponse.getEnemyId());
        assertTrue(optionalEnemy.isPresent());
        Enemy enemy = optionalEnemy.get();
        assertEquals("UpdatedEnemy", enemy.getName());
        assertEquals(40, enemy.getHealth());
        assertEquals(20, enemy.getAtk());
        assertEquals(10, enemy.getHealing());
        assertEquals(2, enemy.getShielding());
        assertEquals("NewPattern", enemy.getPattern());
        assertEquals(image, image);
    }
}
