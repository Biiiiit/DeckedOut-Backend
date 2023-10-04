package strategy_card_game.Business.Enemy;

import strategy_card_game.Domain.Enemy.Enemy;

import java.util.Optional;

public interface GetEnemyUseCase {
    Optional<Enemy> getEnemy(long enemyID);
}
