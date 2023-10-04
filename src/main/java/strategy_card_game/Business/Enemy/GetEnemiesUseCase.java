package strategy_card_game.Business.Enemy;

import strategy_card_game.Domain.Enemy.GetAllEnemyRequest;
import strategy_card_game.Domain.Enemy.GetAllEnemyResponse;

public interface GetEnemiesUseCase {
    GetAllEnemyResponse getEnemies(GetAllEnemyRequest request);
}
