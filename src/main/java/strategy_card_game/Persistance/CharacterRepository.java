package strategy_card_game.Persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import strategy_card_game.Persistance.Entity.CharacterEntity;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
    boolean existsByName(String name);

    Object findByName(String characterName);
}
