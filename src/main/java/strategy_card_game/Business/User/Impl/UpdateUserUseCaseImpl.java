package strategy_card_game.Business.User.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Card.Exception.InvalidCardException;
import strategy_card_game.Business.User.UpdateUserUseCase;
import strategy_card_game.Domain.User.UpdateUserRequest;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;
    @Override
    public void updateUser(UpdateUserRequest request) {
        Optional<UserEntity> userOptional = userRepository.findById(request.getId());
        if (userOptional.isEmpty()) {
            throw new InvalidCardException("USER_ID_INVALID");
        }

        UserEntity user = userOptional.get();
        updateFields(request, user);
    }

    private void updateFields(UpdateUserRequest request, UserEntity user) {
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setType(request.getType());

        userRepository.save(user);
    }
}
