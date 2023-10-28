package strategy_card_game.Business.Level.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Enemy.Impl.EnemyConverter;
import strategy_card_game.Business.Level.Exception.InvalidLevelException;
import strategy_card_game.Business.Level.UpdateLevelUseCase;
import strategy_card_game.Domain.Level.UpdateLevelRequest;
import strategy_card_game.Persistance.Entity.EnemyEntity;
import strategy_card_game.Persistance.Entity.LevelEntity;
import strategy_card_game.Persistance.LevelRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UpdateLevelUseCaseImpl implements UpdateLevelUseCase {
    private final LevelRepository levelRepository;

    @Override
    public void updateLevel(UpdateLevelRequest request) {
        Optional<LevelEntity> levelOptional = levelRepository.findById(request.getId());
        if (levelOptional.isEmpty()) {
            throw new InvalidLevelException("LEVEL_ID_INVALID");
        }

        LevelEntity level = levelOptional.get();
        updateFields(request, level);
    }

    private void updateFields(UpdateLevelRequest request, LevelEntity level) {
        level.setName(request.getName());
        List<EnemyEntity> enemyEntities = request.getEnemies().stream()
                .map(EnemyConverter::convertToEnemyEntity)
                .collect(Collectors.toList());
        level.setEnemies(enemyEntities);
        level.setLvlSprite(request.getLvlSprite());
        level.setBackgroundSprite(request.getBackgroundSprite());

        levelRepository.save(level);
    }
}
