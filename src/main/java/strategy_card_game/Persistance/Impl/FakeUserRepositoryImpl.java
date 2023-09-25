package strategy_card_game.Persistance.Impl;

import org.springframework.stereotype.Repository;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeUserRepositoryImpl implements UserRepository {

    private static long NEXT_ID = 1;
    private final List<UserEntity>savedUsers;
    public FakeUserRepositoryImpl() {this.savedUsers = new ArrayList<>();}
    @Override
    public boolean existsByName(String name) {
        return this.savedUsers
                .stream()
                .anyMatch(userEntity -> userEntity.getUsername().equals(name));
    }

    @Override
    public UserEntity save(UserEntity user) {
        if (user.getId() == null) {
            user.setId(NEXT_ID);
            NEXT_ID++;
            this.savedUsers.add(user);
        }
        return user;
    }

    @Override
    public void deleteById(long userId) {
        this.savedUsers.removeIf(userEntity -> userEntity.getId().equals(userId));
    }

    @Override
    public List<UserEntity> findAll() {
        return Collections.unmodifiableList(this.savedUsers);
    }

    @Override
    public Optional<UserEntity> findById(long userId) {
        return this.savedUsers.stream()
                .filter(userEntity -> userEntity.getId().equals(userId))
                .findFirst();
    }
}
