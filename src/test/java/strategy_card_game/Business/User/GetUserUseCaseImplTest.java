package strategy_card_game.Business.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.User.Impl.GetUserUseCaseImpl;
import strategy_card_game.Domain.User.TypeOfUser;
import strategy_card_game.Domain.User.User;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserUseCaseImplTest {
    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // No need to create the instance of getUserUseCase here since Mockito takes care of it.
    }

    @Test
    public void testGetUser() {
        Byte[] avatar =  new Byte[0];
        // Create a mock UserEntity to be returned by the repository
        UserEntity userEntity = new UserEntity(1L, "User1", "email", "password", TypeOfUser.admin, avatar);

        // Mock the behavior of userRepository.findById to return the UserEntity when called with 1L
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        // Get the created user using getUserUseCase
        Optional<User> optionalUser = getUserUseCase.getUser(1L);

        // Verify that the user is present and has the correct details
        assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        assertEquals("User1", user.getUsername());
        assertEquals("email", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(TypeOfUser.admin, user.getType());
        assertEquals(avatar, user.getAvatar());
    }

    @Test
    public void testGetNonExistentUser() {
        // Attempt to get a user with an invalid ID
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<User> optionalUser = getUserUseCase.getUser(999L);

        // Verify that the optional is empty as the user doesn't exist
        assertTrue(optionalUser.isEmpty());
    }
}
