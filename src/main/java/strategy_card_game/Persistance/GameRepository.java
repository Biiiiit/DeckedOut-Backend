package strategy_card_game.Persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import strategy_card_game.Persistance.Entity.GameEntity;

public interface GameRepository extends JpaRepository<GameEntity, Long> {
    boolean existsByName(String name);
}
