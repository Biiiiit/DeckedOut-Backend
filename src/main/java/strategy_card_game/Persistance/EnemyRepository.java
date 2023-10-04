package strategy_card_game.Persistance;

import strategy_card_game.Persistance.Entity.EnemyEntity;

import java.util.List;
import java.util.Optional;

public interface EnemyRepository {
    boolean existsByName(String name);

    EnemyEntity save(EnemyEntity enemy);

    void deleteById(long enemyId);

    List<EnemyEntity> findAll();

    Optional<EnemyEntity> findById(long enemyID);
}
