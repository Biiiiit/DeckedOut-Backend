package strategy_card_game.Persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import strategy_card_game.Persistance.Entity.AreaEntity;

public interface AreaRepository extends JpaRepository<AreaEntity, Long> {
    boolean existsByName(String name);
}
