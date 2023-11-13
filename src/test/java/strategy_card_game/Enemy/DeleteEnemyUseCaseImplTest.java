package strategy_card_game.Enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import strategy_card_game.Business.Enemy.Impl.CreateEnemyUseCaseImpl;
import strategy_card_game.Business.Enemy.Impl.DeleteEnemyUseCaseImpl;
import strategy_card_game.Business.Enemy.Impl.GetEnemyUseCaseImpl;
import strategy_card_game.Domain.Enemy.CreateEnemyRequest;
import strategy_card_game.Domain.Enemy.CreateEnemyResponse;
import strategy_card_game.Persistance.EnemyRepository;
import strategy_card_game.Persistance.Entity.EnemyEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class DeleteEnemyUseCaseImplTest {

    @Mock
    private EnemyRepository cardRepository;

    @InjectMocks
    private GetEnemyUseCaseImpl getEnemyUseCase;

    @InjectMocks
    private CreateEnemyUseCaseImpl createEnemyUseCase;

    @InjectMocks
    private DeleteEnemyUseCaseImpl deleteEnemyUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks
    }

    @Test
    public void testCreateAndDeleteEnemy() {
        // Create a card using createEnemyUseCase
        CreateEnemyRequest cardRequest = new CreateEnemyRequest(1L, "EnemyName", 50, 10, 0, 0, "pattern", new byte[0]);

        // Mock the behavior of cardRepository.save to return a EnemyEntity with an ID.
        EnemyEntity savedEnemy = new EnemyEntity(1L, "EnemyName", 50, 10, 0, 0, "pattern", new byte[0]);
        when(cardRepository.save(Mockito.any(EnemyEntity.class))).thenReturn(savedEnemy);

        CreateEnemyResponse createResponse = createEnemyUseCase.createEnemy(cardRequest);

        // Verify that the card is initially present
        assertTrue(getEnemyUseCase.getEnemy(createResponse.getEnemyId()).isEmpty());

        // Delete the card
        deleteEnemyUseCase.deleteEnemy(createResponse.getEnemyId());

        // Verify that the card is no longer present after deletion
        assertTrue(getEnemyUseCase.getEnemy(createResponse.getEnemyId()).isEmpty());
    }
}
