package strategy_card_game.Persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import strategy_card_game.Persistance.Entity.GameEntity;

import java.util.List;

public interface GameRepository extends JpaRepository<GameEntity, Long> {
    boolean existsByName(String name);

    List<GameEntity>findByDeveloper_Id(Long Id);
}
