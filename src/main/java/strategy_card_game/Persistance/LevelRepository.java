package strategy_card_game.Persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import strategy_card_game.Persistance.Entity.LevelEntity;

public interface LevelRepository extends JpaRepository<LevelEntity, Long> {
    boolean existsByName(String name);
}
