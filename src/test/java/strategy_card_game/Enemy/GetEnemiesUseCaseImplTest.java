package strategy_card_game.Enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Enemy.Impl.GetEnemiesUseCaseImpl;
import strategy_card_game.Domain.Enemy.Enemy;
import strategy_card_game.Domain.Enemy.GetAllEnemyRequest;
import strategy_card_game.Persistance.EnemyRepository;
import strategy_card_game.Persistance.Entity.EnemyEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)  // Use MockitoExtension for JUnit 5
public class GetEnemiesUseCaseImplTest {

    @InjectMocks
    private GetEnemiesUseCaseImpl getEnemiesUseCase;

    @Mock
    private EnemyRepository cardRepository;

    @BeforeEach
    public void setUp() {
        // No need to create the instance of getEnemysUseCase here since Mockito takes care of it.
    }

    @Test
    public void testGetAllEnemys() {
        // Create mock EnemyEntities to be returned by the repository
        EnemyEntity cardEntity1 = new EnemyEntity(1L, "Enemy1", 50, 10, 0, 0, "pattern", new byte[0]);
        EnemyEntity cardEntity2 = new EnemyEntity(2L, "Enemy2", 50, 10, 0, 0, "pattern", new byte[0]);

        List<EnemyEntity> cardEntities = new ArrayList<>();
        cardEntities.add(cardEntity1);
        cardEntities.add(cardEntity2);

        // Mock the behavior of cardRepository.findAll to return the list of EnemyEntities
        when(cardRepository.findAll()).thenReturn(cardEntities);

        // Call the getEnemies method
        List<Enemy> cards = getEnemiesUseCase.getEnemies(new GetAllEnemyRequest()).getEnemies();

        // Verify the response
        assertEquals(2, cards.size());

        // You can further assert the properties of the retrieved cards if needed
        assertEquals("Enemy1", cards.get(0).getName());
        assertEquals("Enemy2", cards.get(1).getName());
    }
}
