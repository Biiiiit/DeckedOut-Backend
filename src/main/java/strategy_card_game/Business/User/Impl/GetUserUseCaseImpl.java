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
        Optional<User> user = userRepository.findByUsername(username).map(UserConverter::convert);
        if (user.isPresent()) {
            return getUser(user.get().getId());
        }
        return Optional.empty();
    }
}
