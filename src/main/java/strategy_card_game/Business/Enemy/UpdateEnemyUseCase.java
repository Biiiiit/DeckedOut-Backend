package strategy_card_game.Business.Enemy;

import strategy_card_game.Domain.Enemy.UpdateEnemyRequest;

public interface UpdateEnemyUseCase {
    void updateEnemy(UpdateEnemyRequest request);
}
