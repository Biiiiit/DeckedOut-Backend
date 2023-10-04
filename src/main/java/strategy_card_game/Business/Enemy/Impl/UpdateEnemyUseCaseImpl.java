package strategy_card_game.Business.Enemy.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Enemy.UpdateEnemyUseCase;
import strategy_card_game.Business.User.Exception.InvalidUserException;
import strategy_card_game.Domain.Enemy.UpdateEnemyRequest;
import strategy_card_game.Persistance.EnemyRepository;
import strategy_card_game.Persistance.Entity.EnemyEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateEnemyUseCaseImpl implements UpdateEnemyUseCase {
    private final EnemyRepository enemyRepository;
    @Override
    public void updateEnemy(UpdateEnemyRequest request) {
        Optional<EnemyEntity> enemyOptional = enemyRepository.findById(request.getId());
        if (enemyOptional.isEmpty()) {
            throw new InvalidUserException("ENEMY_ID_INVALID");
        }

        EnemyEntity enemy = enemyOptional.get();
        updateFields(request, enemy);
    }

    private void updateFields(UpdateEnemyRequest request, EnemyEntity enemy) {
        enemy.setName(request.getName());
        enemy.setHealth(request.getHealth());
        enemy.setAtk(request.getAtk());
        enemy.setHealing(request.getHealing());
        enemy.setShielding(request.getShielding());
        enemy.setPattern(request.getPattern());
        enemy.setSprite(request.getSprite());

        enemyRepository.save(enemy);
    }
}
