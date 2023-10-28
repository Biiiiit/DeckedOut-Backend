package strategy_card_game.Business.Level.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Enemy.Impl.EnemyConverter;
import strategy_card_game.Business.Level.CreateLevelUseCase;
import strategy_card_game.Business.Level.Exception.LevelAlreadyExistsException;
import strategy_card_game.Domain.Level.CreateLevelRequest;
import strategy_card_game.Domain.Level.CreateLevelResponse;
import strategy_card_game.Persistance.Entity.EnemyEntity;
import strategy_card_game.Persistance.Entity.LevelEntity;
import strategy_card_game.Persistance.LevelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreateLevelUseCaseImpl implements CreateLevelUseCase {
    private final LevelRepository levelRepository;
    @Override
    public CreateLevelResponse createLevel(CreateLevelRequest request){
        if (levelRepository.existsByName(request.getName())){
            throw new LevelAlreadyExistsException();
        }

        LevelEntity savedLevel = saveNewLevel(request);

        return CreateLevelResponse.builder()
                .levelId(savedLevel.getId())
                .build();
    }
    private LevelEntity saveNewLevel(CreateLevelRequest request) {
        List<EnemyEntity> enemyEntities = request.getEnemies().stream()
                .map(EnemyConverter::convertToEnemyEntity)
                .collect(Collectors.toList());

        LevelEntity newLevel = LevelEntity.builder()
                .id(request.getId())
                .name(request.getName())
                .enemies(enemyEntities)
                .lvlSprite(request.getLvlSprite())
                .backgroundSprite(request.getBackgroundSprite())
                .build();
        return levelRepository.save(newLevel);
    }
}
