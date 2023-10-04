package strategy_card_game.Business.Enemy.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Enemy.CreateEnemyUseCase;
import strategy_card_game.Business.Enemy.Exception.EnemyAlreadyExistsException;
import strategy_card_game.Domain.Enemy.CreateEnemyRequest;
import strategy_card_game.Domain.Enemy.CreateEnemyResponse;
import strategy_card_game.Persistance.EnemyRepository;
import strategy_card_game.Persistance.Entity.EnemyEntity;

@Service
@AllArgsConstructor
public class CreateEnemyUseCaseImpl implements CreateEnemyUseCase {
    private final EnemyRepository enemyRepository;
    @Override
    public CreateEnemyResponse createEnemy(CreateEnemyRequest request){
        if (enemyRepository.existsByName(request.getName())){
            throw new EnemyAlreadyExistsException();
        }

        EnemyEntity savedEnemy = saveNewEnemy(request);

        return CreateEnemyResponse.builder()
                .enemyId(savedEnemy.getId())
                .build();
    }
    private EnemyEntity saveNewEnemy(CreateEnemyRequest request) {

        EnemyEntity newEnemy = EnemyEntity.builder()
                .id(request.getId())
                .name(request.getName())
                .health(request.getHealth())
                .atk(request.getAtk())
                .healing(request.getHealing())
                .shielding(request.getShielding())
                .pattern(request.getPattern())
                .sprite(request.getSprite())
                .build();
        return enemyRepository.save(newEnemy);
    }
}
