package strategy_card_game.Persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import strategy_card_game.Persistance.Entity.EnemyEntity;

public interface EnemyRepository extends JpaRepository<EnemyEntity, Long> {
    boolean existsByName(String name);
}
