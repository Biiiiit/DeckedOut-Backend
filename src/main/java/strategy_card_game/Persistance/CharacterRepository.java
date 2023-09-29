package strategy_card_game.Persistance;

import strategy_card_game.Persistance.Entity.CharacterEntity;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository {
    boolean existsByName(String name);

    CharacterEntity save(CharacterEntity character);

    void deleteById(long cardId);

    List<CharacterEntity> findAll();

    Optional<CharacterEntity> findById(long characterID);
}
