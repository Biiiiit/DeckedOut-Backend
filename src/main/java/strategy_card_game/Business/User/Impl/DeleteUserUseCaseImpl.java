package strategy_card_game.Business.User.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.User.DeleteUserUseCase;
import strategy_card_game.Persistance.UserRepository;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;
    @Override
    public void deleteUser(long userId) {
        this.userRepository.deleteById(userId);
    }
}
