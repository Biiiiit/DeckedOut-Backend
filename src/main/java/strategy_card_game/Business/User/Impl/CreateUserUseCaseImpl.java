package strategy_card_game.Business.User.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.User.CreateUserUseCase;
import strategy_card_game.Business.User.Exception.UserAlreadyExistsException;
import strategy_card_game.Domain.User.CreateUserRequest;
import strategy_card_game.Domain.User.CreateUserResponse;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.UserRepository;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    @Override
    public CreateUserResponse createUser(CreateUserRequest request){
        if (userRepository.existsByName(request.getUsername())){
            throw new UserAlreadyExistsException();
        }

        UserEntity savedUser = saveNewUser(request);

        return CreateUserResponse.builder()
                .userId(savedUser.getId())
                .build();
    }
    private UserEntity saveNewUser(CreateUserRequest request) {

        UserEntity newUser = UserEntity.builder()
                .id(request.getId())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .type(request.getType())
                .build();
        return userRepository.save(newUser);
    }
}
