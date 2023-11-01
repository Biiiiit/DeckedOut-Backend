package strategy_card_game.Business.Level.Impl;

import strategy_card_game.Business.Enemy.Impl.EnemyConverter;
import strategy_card_game.Domain.Enemy.Enemy;
import strategy_card_game.Domain.Level.Level;
import strategy_card_game.Persistance.Entity.LevelEntity;

import java.util.List;
import java.util.stream.Collectors;

public class LevelConverter {
    public LevelConverter() {
    }

    public static Level convert(LevelEntity level) {
        List<Enemy> enemies = level.getEnemies()
                .stream()
                .map(EnemyConverter::convertToEnemy)
                .collect(Collectors.toList());

        return Level.builder()
                .id(level.getId())
                .name(level.getName())
                .enemies(enemies)
                .lvlSprite(level.getLvlSprite())
                .backgroundSprite(level.getBackgroundSprite())
                .build();
    }
}