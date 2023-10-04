package strategy_card_game.Business.Enemy.Impl;

import strategy_card_game.Domain.Enemy.Enemy;
import strategy_card_game.Persistance.Entity.EnemyEntity;

public class EnemyConverter {
    private EnemyConverter() {
    }

    public static Enemy convert(EnemyEntity enemy) {
        return Enemy.builder()
                .id(enemy.getId())
                .name(enemy.getName())
                .health(enemy.getHealth())
                .atk(enemy.getAtk())
                .healing(enemy.getHealing())
                .shielding(enemy.getShielding())
                .pattern(enemy.getPattern())
                .sprite(enemy.getSprite())
                .build();
    }
}
