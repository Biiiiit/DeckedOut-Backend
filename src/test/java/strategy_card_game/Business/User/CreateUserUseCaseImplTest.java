package strategy_card_game.Business.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.User.Exception.UserAlreadyExistsException;
import strategy_card_game.Business.User.Impl.CreateUserUseCaseImpl;
import strategy_card_game.Domain.User.CreateUserRequest;
import strategy_card_game.Domain.User.CreateUserResponse;
import strategy_card_game.Domain.User.TypeOfUser;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.UserRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseImplTest {
    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCreateUserSuccess() {
        CreateUserRequest request = new CreateUserRequest(1L,"Username", "email", "password", "admin");

        // Mock the behavior of userRepository.save to return a UserEntity with an ID.
        UserEntity savedUser = new UserEntity(1L, "Username", "email", "password", TypeOfUser.admin);
        when(userRepository.save(savedUser)).thenReturn(savedUser);

        CreateUserResponse response = createUserUseCase.createUser(request);

        assertNotNull(response);
    }

    @Test
    public void testCreateUserFailureUserAlreadyExists() {
        // Mock the behavior of userRepository.findByUsername to return an existing user.
        //UserEntity existingUser = new UserEntity(1L, "ExistingUsername", "email", "password", TypeOfUser.admin);
        when(userRepository.existsByusername("ExistingUsername")).thenReturn(true);

        CreateUserRequest request = new CreateUserRequest(1L, "ExistingUsername", "email", "password", "admin");

        // Expect a UserAlreadyExistsException
        assertThrows(UserAlreadyExistsException.class, () -> createUserUseCase.createUser(request));
    }
}
