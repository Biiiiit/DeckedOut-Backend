package strategy_card_game.Business.User.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.User.GetUserUseCase;
import strategy_card_game.Domain.User.User;
import strategy_card_game.Persistance.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private UserRepository userRepository;

    @Override
    public Optional<User> getUser(long userID) {
        return userRepository.findById(userID).map(UserConverter::convert);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(userEntity -> {
            return new User(userEntity.getUsername(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getType(), userEntity.getAvatar());
        });
    }

}
