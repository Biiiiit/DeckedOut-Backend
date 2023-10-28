package strategy_card_game.Persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import strategy_card_game.Persistance.Entity.CardEntity;

public interface CardRepository extends JpaRepository<CardEntity, Long> {

    boolean existsByName(String name);
}
