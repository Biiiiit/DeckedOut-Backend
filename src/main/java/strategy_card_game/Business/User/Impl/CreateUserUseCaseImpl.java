package strategy_card_game.Business.User.Impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.User.CreateUserUseCase;
import strategy_card_game.Business.User.Exception.UserAlreadyExistsException;
import strategy_card_game.Domain.User.CreateUserRequest;
import strategy_card_game.Domain.User.CreateUserResponse;
import strategy_card_game.Domain.User.TypeOfUser;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.UserRepository;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
            throw new UserAlreadyExistsException();
        }

        UserEntity savedUser = saveNewUser(request);

        return CreateUserResponse.builder()
                .userId(savedUser.getId())
                .build();
    }
    private UserEntity saveNewUser(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity newUser = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encodedPassword)
                .type(TypeOfUser.fromString(request.getType()))
                .avatar(request.getAvatar())
                .build();
        return userRepository.save(newUser);
    }
}
