package strategy_card_game.Business.Enemy.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Enemy.GetEnemiesUseCase;
import strategy_card_game.Domain.Enemy.Enemy;
import strategy_card_game.Domain.Enemy.GetAllEnemyRequest;
import strategy_card_game.Domain.Enemy.GetAllEnemyResponse;
import strategy_card_game.Persistance.EnemyRepository;
import strategy_card_game.Persistance.Entity.EnemyEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class GetEnemiesUseCaseImpl implements GetEnemiesUseCase {
    private EnemyRepository enemyRepository;

    @Override
    public GetAllEnemyResponse getEnemies(final GetAllEnemyRequest request) {
        List<EnemyEntity> results = enemyRepository.findAll();

        final GetAllEnemyResponse response = new GetAllEnemyResponse();
        List<Enemy> enemies = results
                .stream()
                .map(EnemyConverter::convertToEnemy)
                .toList();
        response.setEnemies(enemies);

        return response;
    }
}
