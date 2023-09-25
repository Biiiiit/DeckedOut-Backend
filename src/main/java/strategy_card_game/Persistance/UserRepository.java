package strategy_card_game.Persistance;

import strategy_card_game.Persistance.Entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    boolean existsByName(String name);

    UserEntity save(UserEntity user);

    void deleteById(long cardId);

    List<UserEntity> findAll();

    Optional<UserEntity> findById(long userID);
}
