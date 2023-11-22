package strategy_card_game.Business.User.Impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.User.Exception.InvalidCredentialsException;
import strategy_card_game.Business.User.LoginUseCase;
import strategy_card_game.Domain.User.LoginRequest;
import strategy_card_game.Domain.User.LoginResponse;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.UserRepository;
import strategy_card_game.configuration.Security.Token.AccessTokenEncoder;
import strategy_card_game.configuration.Security.Token.Impl.AccessTokenImpl;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<UserEntity> user = userRepository.findByUsername(loginRequest.getUsername());
        if (user.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        UserEntity newUser = user.get();

        if (!matchesPassword(loginRequest.getPassword(), newUser.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(newUser);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {
        Long userId = user.getClass() != null ? user.getId() : null;
        String role = String.valueOf(user.getType());

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getUsername(), userId, Collections.singleton(role)));
    }
}
