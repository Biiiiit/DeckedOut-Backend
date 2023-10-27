package strategy_card_game.Persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import strategy_card_game.Persistance.Entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByusername(String name);

    Optional<UserEntity> findByusername(String username);
}
