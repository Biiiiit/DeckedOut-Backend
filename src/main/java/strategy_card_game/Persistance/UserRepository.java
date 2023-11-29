package strategy_card_game.Persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import strategy_card_game.Persistance.Entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String name);

    Optional<UserEntity> findByUsername(String username);
}
