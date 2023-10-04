package strategy_card_game.Business.Enemy;

import strategy_card_game.Domain.Enemy.CreateEnemyRequest;
import strategy_card_game.Domain.Enemy.CreateEnemyResponse;

public interface CreateEnemyUseCase {
    CreateEnemyResponse createEnemy(CreateEnemyRequest request);
}
