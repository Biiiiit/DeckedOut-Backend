package strategy_card_game.Business.Enemy.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Enemy.DeleteEnemyUseCase;
import strategy_card_game.Persistance.EnemyRepository;

@Service
@AllArgsConstructor
public class DeleteEnemyUseCaseImpl implements DeleteEnemyUseCase {
    private final EnemyRepository enemyRepository;
    @Override
    public void deleteEnemy(long enemyId) {
        this.enemyRepository.deleteById(enemyId);
    }
}
