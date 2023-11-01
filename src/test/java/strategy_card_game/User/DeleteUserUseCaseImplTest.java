package strategy_card_game.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.User.Impl.CreateUserUseCaseImpl;
import strategy_card_game.Business.User.Impl.DeleteUserUseCaseImpl;
import strategy_card_game.Business.User.Impl.GetUserUseCaseImpl;
import strategy_card_game.Domain.User.CreateUserRequest;
import strategy_card_game.Domain.User.CreateUserResponse;
import strategy_card_game.Domain.User.TypeOfUser;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.UserRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteUserUseCaseImplTest {
    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testDeleteUser() {
        // Create a user using createUserUseCase
        CreateUserRequest userRequest = new CreateUserRequest(1L, "User1", "email", "password", "admin");

        // Mock the behavior of userRepository.save to return a UserEntity with an ID.
        UserEntity savedUser = new UserEntity(1L, "User1", "email", "password", TypeOfUser.admin);
        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(savedUser);

        CreateUserResponse createResponse = createUserUseCase.createUser(userRequest);

        // Verify that the user is initially present
        assertTrue(getUserUseCase.getUser(createResponse.getUserId()).isEmpty());

        // Delete the user
        deleteUserUseCase.deleteUser(createResponse.getUserId());

        // Verify that the user is no longer present after deletion
        assertTrue(getUserUseCase.getUser(createResponse.getUserId()).isEmpty());
    }
}
