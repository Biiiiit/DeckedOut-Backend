package strategy_card_game.Business.Enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Enemy.Exception.EnemyAlreadyExistsException;
import strategy_card_game.Business.Enemy.Impl.CreateEnemyUseCaseImpl;
import strategy_card_game.Domain.Enemy.CreateEnemyRequest;
import strategy_card_game.Domain.Enemy.CreateEnemyResponse;
import strategy_card_game.Persistance.EnemyRepository;
import strategy_card_game.Persistance.Entity.EnemyEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateEnemyUseCaseImplTest {
    @InjectMocks
    private CreateEnemyUseCaseImpl createEnemyUseCase;

    @Mock
    private EnemyRepository cardRepository;

    @BeforeEach
    public void setUp() {
        // No need to create the instance of createEnemyUseCase here since Mockito takes care of it.
    }

    @Test
    public void testCreateEnemySuccess() {
        // Create a mock CreateEnemyRequest
        CreateEnemyRequest request = new CreateEnemyRequest(1L, "EnemyName", 50, 10, 0, 0, "pattern", new byte[0]);

        // Mock the behavior of cardRepository.save to return a EnemyEntity with an ID.
        EnemyEntity savedEnemy = new EnemyEntity(1L, "EnemyName", 50, 10, 0, 0, "pattern", new byte[0]);
        when(cardRepository.save(Mockito.any(EnemyEntity.class))).thenReturn(savedEnemy);

        // Call the createEnemy method
        CreateEnemyResponse response = createEnemyUseCase.createEnemy(request);

        // Verify that the response contains the cardId
        assertNotNull(response);
    }

    @Test
    public void testCreateEnemyFailureEnemyAlreadyExists() {
        // Mock the behavior of cardRepository.existsByName to return true.
        when(cardRepository.existsByName("ExistingEnemyName")).thenReturn(true);

        // Create a CreateEnemyRequest for an existing card
        CreateEnemyRequest request = new CreateEnemyRequest(1L, "ExistingEnemyName", 50, 10, 0, 0, "pattern", new byte[0]);

        // Call the createEnemy method and expect a EnemyAlreadyExistsException
        assertThrows(EnemyAlreadyExistsException.class, () -> createEnemyUseCase.createEnemy(request));
    }
}