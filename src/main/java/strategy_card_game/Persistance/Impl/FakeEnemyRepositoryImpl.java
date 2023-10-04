package strategy_card_game.Persistance.Impl;

import org.springframework.stereotype.Repository;
import strategy_card_game.Persistance.EnemyRepository;
import strategy_card_game.Persistance.Entity.EnemyEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeEnemyRepositoryImpl implements EnemyRepository {
    private static long NEXT_ID = 1;
    private final List<EnemyEntity> savedEnemies;
    public FakeEnemyRepositoryImpl() {this.savedEnemies = new ArrayList<>();}

    @Override
    public boolean existsByName(String name) {
        return this.savedEnemies
                .stream()
                .anyMatch(enemyEntity -> enemyEntity.getName().equals(name));
    }

    @Override
    public EnemyEntity save(EnemyEntity enemy) {
        if (enemy.getId() == null) {
            enemy.setId(NEXT_ID);
            NEXT_ID++;
            this.savedEnemies.add(enemy);
        }
        return enemy;
    }

    @Override
    public void deleteById(long enemyId) {
        this.savedEnemies.removeIf(enemyEntity -> enemyEntity.getId().equals(enemyId));
    }

    @Override
    public List<EnemyEntity> findAll() {
        return Collections.unmodifiableList(this.savedEnemies);
    }

    @Override
    public Optional<EnemyEntity> findById(long enemyId) {
        return this.savedEnemies.stream()
                .filter(enemyEntity -> enemyEntity.getId().equals(enemyId))
                .findFirst();
    }
}
