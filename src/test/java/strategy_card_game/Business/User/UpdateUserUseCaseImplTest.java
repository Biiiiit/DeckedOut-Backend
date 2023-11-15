package strategy_card_game.Business.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.User.CreateUserUseCase;
import strategy_card_game.Business.User.Exception.InvalidUserException;
import strategy_card_game.Business.User.Impl.UpdateUserUseCaseImpl;
import strategy_card_game.Domain.User.*;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateUserUseCaseImplTest {
    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CreateUserUseCase createUserUseCase;

    @BeforeEach
    public void setUp() {
        // No need to create the instance of updateUserUseCase here since Mockito takes care of it.
    }

    @Test
    public void testUpdateUser() {
        // Create a mock UserEntity to be returned by the repository
        UserEntity userEntity = new UserEntity(1L, "User1", "email", "password", TypeOfUser.admin);

        // Mock the behavior of userRepository.findById to return the UserEntity when called with 1L
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        // Create an UpdateUserRequest
        UpdateUserRequest updateRequest = new UpdateUserRequest(1L, "UpdatedUser", "email", "password", TypeOfUser.normal);

        // Call the updateUser method
        updateUserUseCase.updateUser(updateRequest);

        // Verify that the user has been updated
        verify(userRepository, Mockito.times(1)).save(Mockito.any(UserEntity.class));
        assertEquals("UpdatedUser", userEntity.getUsername());
        assertEquals("email", userEntity.getEmail());
        assertEquals("password", userEntity.getPassword());
        assertEquals(TypeOfUser.normal, userEntity.getType());
    }

    @Test
    public void testUpdateInvalidUser() {
        // Create an UpdateUserRequest for a non-existent user
        UpdateUserRequest updateRequest = new UpdateUserRequest(999L, "UpdatedUser", "email", "password", TypeOfUser.normal);

        // Mock the behavior of userRepository.findById to return an empty Optional
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        // Expect an InvalidUserException when attempting to update an invalid user
        assertThrows(InvalidUserException.class, () -> updateUserUseCase.updateUser(updateRequest));
    }
}