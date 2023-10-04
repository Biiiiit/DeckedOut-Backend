package strategy_card_game.Business.Enemy.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Enemy.GetEnemyUseCase;
import strategy_card_game.Domain.Enemy.Enemy;
import strategy_card_game.Persistance.EnemyRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetEnemyUseCaseImpl implements GetEnemyUseCase {
    private EnemyRepository enemyRepository;
    @Override
    public Optional<Enemy> getEnemy(long enemyID) {
        return enemyRepository.findById(enemyID).map(EnemyConverter::convert);
    }
}
